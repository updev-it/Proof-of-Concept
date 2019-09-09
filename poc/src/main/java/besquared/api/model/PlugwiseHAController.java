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

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.jetty.client.HttpClient;

import besquared.api.exception.PlugwiseHAException;
import besquared.api.xml.PlugwiseHAXStream;

/**
 * The {@link PlugwiseHAController} class provides the interface to the Plugwise
 * Home Automation API and stores/caches the object model for use by the various
 * ThingHandlers of this binding.
 * 
 * @author B. van Wetten - Initial contribution
 */
public class PlugwiseHAController {

    // Private member variables/constants

    private final HttpClient httpClient;
    private final PlugwiseHAXStream xStream;
    private Transformer transformer;

    private final String host;
    private final int port;
    private final String username;
    private final String smileId;


    public PlugwiseHAController(HttpClient httpClient, String host, int port, String username, String smileId) {
        this.httpClient = httpClient;
        this.host = host;
        this.port = port;
        this.username = username;
        this.smileId = smileId;

        this.xStream = new PlugwiseHAXStream();
        try {
            StreamSource xmlStylesheet = new StreamSource("./poc/src/main/java/besquared/api/xml/plugwiseha.xsl");
            this.transformer = TransformerFactory.newInstance().newTransformer(xmlStylesheet);
        } catch (TransformerConfigurationException e) {
            //throw new PlugwiseHAException(e);
        }
        
    }

    // Public methods

    public void start(Runnable callback) throws Exception {
        callback.run();
    }

    public void stop() throws Exception {
    }

    public void refresh() throws Exception {
        synchronized (this) {
        }
    }

    // Public API methods

    public Modules getModules() throws PlugwiseHAException {        
            PlugwiseHAControllerRequest<Modules> request = newRequest(Modules.class);            
    
            request.setPath("/core/modules");
    
            return executeRequest(request);
    }

    // Protected and private methods

    private <T> PlugwiseHAControllerRequest<T> newRequest(Class<T> responseType) {
        return new PlugwiseHAControllerRequest<T>(responseType, this.xStream, this.transformer, this.httpClient, this.host, this.port,
                this.username, this.smileId);
    }

    private <T> T executeRequest(PlugwiseHAControllerRequest<T> request) throws PlugwiseHAException {
        T result;
        result = request.execute();
        return result;
    }
}