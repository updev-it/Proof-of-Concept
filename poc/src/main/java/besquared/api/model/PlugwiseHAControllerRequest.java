/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package besquared.api.model;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;

import com.thoughtworks.xstream.XStream;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.util.B64Code;
import org.eclipse.jetty.util.StringUtil;

import besquared.api.exception.PlugwiseHAException;

/**
 * The {@link PlugwiseHAControllerRequest} class is a utility class to create
 * API requests to the Plugwise Home Automation controller and to deserialize
 * incoming XML into the appropriate model objects to be used by the
 * {@link PlugwiseHAController}.
 * 
 * @author B. van Wetten - Initial contribution
 */
public class PlugwiseHAControllerRequest<T> {

    private static final String CONTENT_TYPE_TEXT_XML = MimeTypes.Type.TEXT_XML_8859_1.toString();
    private static final long TIMEOUT_SECONDS = 5;

    private final XStream xStream;
    private final HttpClient httpClient;
    private final String host;
    private final int port;
    private final Class<T> resultType;
    private final Transformer transformer;

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> queryParameters = new HashMap<>();
    private Object bodyParameter = null;
    private String path = "/";

    private String serverDateTime;

    // Constructor

    <X extends XStream> PlugwiseHAControllerRequest(Class<T> resultType, X xStream, Transformer transformer,
            HttpClient httpClient, String host, int port, String username, String password) {
        this.resultType = resultType;
        this.xStream = xStream;
        this.transformer = transformer;
        this.httpClient = httpClient;
        this.host = host;
        this.port = port;

        setHeader(HttpHeader.ACCEPT.toString(), CONTENT_TYPE_TEXT_XML);

        // Create Basic Auth header if username and password are supplied
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            setHeader(HttpHeader.AUTHORIZATION.toString(),
                    "Basic " + B64Code.encode(String.format("%s:%s", username, password), StringUtil.__ISO_8859_1));
        }
    }

    // Public methods

    public void setPath(String path) {
        this.setPath(path, null);
    }

    public void setPath(String path, HashMap<String, String> pathParameters) {
        this.path = path;

        if (pathParameters != null) {
            this.path += pathParameters.entrySet().stream().map(Object::toString).collect(Collectors.joining(";"));
        }
    }

    public void setHeader(String key, Object value) {
        this.headers.put(key, String.valueOf(value));
    }

    public void addPathParameter(String key) {
        this.path += String.format(";%s", key);
    }

    public void addPathParameter(String key, Object value) {
        this.path += String.format(";%s=%s", key, value);
    }

    public void addPathFilter(String key, String operator, Object value) {
        this.path += String.format(";%s:%s:%s", key, operator, value);
    }

    public void setQueryParameter(String key, Object value) {
        this.queryParameters.put(key, String.valueOf(value));
    }

    public void setBodyParameter(Object body) {
        this.bodyParameter = body;
    }

    public String getServerDateTime() {
        return this.serverDateTime;
    }

    @SuppressWarnings("unchecked")
    public T execute() throws PlugwiseHAException {
        T result = null;
        String xml = getContent();

        if (String.class.equals(resultType)) {
            if (this.transformer != null) {
                result = (T) this.transformXML(xml);
            } else {
                result = (T) xml;
            }
        } else if (!Void.class.equals(resultType)) {
            if (this.transformer != null) {
                result = (T) this.xStream.fromXML(this.transformXML(xml));
            } else {
                result = (T) this.xStream.fromXML(xml);
            }
        }
        return result;
    }

    // Protected and private methods

    private String transformXML(String xml) throws PlugwiseHAException {
        try {
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader(xml);
            this.transformer.transform(new javax.xml.transform.stream.StreamSource(reader),
                    new javax.xml.transform.stream.StreamResult(writer));
            return writer.toString();
        } catch (TransformerException e) {
            throw new PlugwiseHAException("Could not apply XML stylesheet", e);
        }
    }

    private String getContent() throws PlugwiseHAException {
        String content;
        ContentResponse response = getContentResponse();
        int status = response.getStatus();
        switch (status) {
        case HttpStatus.OK_200:
        case HttpStatus.ACCEPTED_202:
            content = response.getContentAsString();
            System.out.println(content);
            break;
        case HttpStatus.BAD_REQUEST_400:
            throw new PlugwiseHAException("Bad request");
        case HttpStatus.UNAUTHORIZED_401:
            throw new PlugwiseHAException("Unauthorized");
        case HttpStatus.FORBIDDEN_403:
            throw new PlugwiseHAException("Forbidden");
        default:
            throw new PlugwiseHAException("Unknown HTTP status code " + status + " returned by the controller");
        }

        this.serverDateTime = response.getHeaders().get("Date");

        return content;
    }

    private ContentResponse getContentResponse() throws PlugwiseHAException {
        Request request = newRequest();
        ContentResponse response;

        try {
            response = request.send();
        } catch (TimeoutException | InterruptedException e) {
            throw new PlugwiseHAException(e);
        } catch (ExecutionException e) {
            // Unwrap the cause and try to cleanly handle it
            Throwable cause = e.getCause();
            if (cause instanceof UnknownHostException) {
                // Invalid hostname
                throw new PlugwiseHAException(cause);
            } else if (cause instanceof ConnectException) {
                // Cannot connect
                throw new PlugwiseHAException(cause);
            } else {
                // Catch all
                throw new PlugwiseHAException(cause);
            }
        }
        return response;
    }

    private Request newRequest() {
        HttpMethod method = bodyParameter == null ? HttpMethod.GET : HttpMethod.PUT;
        HttpURI uri = new HttpURI(HttpScheme.HTTP.asString(), this.host, this.port, this.path);
        Request request = httpClient.newRequest(uri.toString()).timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .method(method);

        for (Entry<String, String> entry : this.headers.entrySet()) {
            request.header(entry.getKey(), entry.getValue());
        }

        for (Entry<String, String> entry : this.queryParameters.entrySet()) {
            request.param(entry.getKey(), entry.getValue());
        }

        if (this.bodyParameter != null) {
            String xmlBody = getRequestBodyAsXml();
            ContentProvider content = new StringContentProvider(CONTENT_TYPE_TEXT_XML, xmlBody, StandardCharsets.UTF_8);
            request = request.content(content);
        }
        return request;
    }

    private String getRequestBodyAsXml() {
        return this.xStream.toXML(this.bodyParameter);
    }
}