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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private final Transformer domainObjectsTransformer;

    private final String host;
    private final int port;
    private final String username;
    private final String smileId;

    private final static DateTimeFormatter FORMAT = DateTimeFormatter.RFC_1123_DATE_TIME; // default Date format that
                                                                                          // will be used in conversion

    private ZonedDateTime gatewayUpdateDateTime;
    private DomainObjects domainObjects;

    public PlugwiseHAController(HttpClient httpClient, String host, int port, String username, String smileId) {
        this.httpClient = httpClient;
        this.host = host;
        this.port = port;
        this.username = username;
        this.smileId = smileId;

        this.xStream = new PlugwiseHAXStream();
        this.domainObjectsTransformer = PlugwiseHAController
                .setXSLT(new StreamSource("./poc/src/main/java/besquared/api/xml/domain_objects.xslt"));
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

    public GatewayInfo getGatewayInfo() throws PlugwiseHAException {
        PlugwiseHAControllerRequest<DomainObjects> request;

        request = newRequest(DomainObjects.class, this.domainObjectsTransformer);

        request.setPath("/cache/domain_objects");
        request.addPathParameter("class", "Gateway");

        DomainObjects domainObjects = executeRequest(request);

        return domainObjects.getGatewayInfo();
    }

    public Appliances getAppliances() throws PlugwiseHAException {
        PlugwiseHAControllerRequest<DomainObjects> request;

        request = newRequest(DomainObjects.class, this.domainObjectsTransformer);

        request.setPath("/core/domain_objects");
        request.addPathParameter("class", "Appliance");

        DomainObjects domainObjects = executeRequest(request);

        return domainObjects.getAppliances();
    }

    public Locations getLocations() throws PlugwiseHAException {
        PlugwiseHAControllerRequest<DomainObjects> request;

        request = newRequest(DomainObjects.class, this.domainObjectsTransformer);

        request.setPath("/core/domain_objects");
        request.addPathParameter("class", "Location");

        DomainObjects domainObjects = executeRequest(request);

        return domainObjects.getLocations();
    }

    public DomainObjects getDomainObjects() throws PlugwiseHAException {
        PlugwiseHAControllerRequest<DomainObjects> request;

        request = newRequest(DomainObjects.class, this.domainObjectsTransformer);

        request.setPath("/core/domain_objects");
        request.addPathParameter("@locale", "en-US");

        DomainObjects domainObjects = executeRequest(request);
        this.gatewayUpdateDateTime = ZonedDateTime.parse(request.getServerDateTime(), PlugwiseHAController.FORMAT);
        
        return mergeDomainObjects(domainObjects);
    }

    public DomainObjects getUpdatedDomainObjects() throws PlugwiseHAException {
        if (this.gatewayUpdateDateTime == null
                || this.gatewayUpdateDateTime.isBefore(ZonedDateTime.now().minusMinutes(10))) {
            return getDomainObjects();
        } else {
            return getUpdatedDomainObjects(this.gatewayUpdateDateTime);
        }
    }

    public DomainObjects getUpdatedDomainObjects(ZonedDateTime since) throws PlugwiseHAException {
        return getUpdatedDomainObjects(since.toEpochSecond());
    }

    public DomainObjects getUpdatedDomainObjects(Long since) throws PlugwiseHAException {
        PlugwiseHAControllerRequest<DomainObjects> request;

        request = newRequest(DomainObjects.class, this.domainObjectsTransformer);

        request.setPath("/core/domain_objects");
        request.addPathFilter("modified_date", "ge", since);
        request.addPathFilter("deleted_date", "ge", "0");
        request.addPathParameter("@memberModifiedDate", since);
        request.addPathParameter("@locale", "en-US");

        DomainObjects domainObjects = executeRequest(request);
        this.gatewayUpdateDateTime = ZonedDateTime.parse(request.getServerDateTime(), PlugwiseHAController.FORMAT);

        return mergeDomainObjects(domainObjects);
    }

    public void setLocationThermostat(Location location, Double temperature) throws PlugwiseHAException {
        PlugwiseHAControllerRequest<Void> request = newRequest(Void.class);
        Optional<ActuatorFunctionality> thermostat = location.getActuatorFunctionalities().getFunctionalityThermostat();

        if (thermostat.isPresent()) {
            request.setPath("/core/locations");

            request.addPathParameter("id", String.format("%s/thermostat", location.getId()));
            request.addPathParameter("id", String.format("%s", thermostat.get().getId()));    
            request.setBodyParameter(new ActuatorFunctionalityThermostat(temperature));

            executeRequest(request);
        }
    }

    public void setThermostat(Appliance appliance, Double temperature) throws PlugwiseHAException {
        PlugwiseHAControllerRequest<Void> request = newRequest(Void.class);
        Optional<ActuatorFunctionality> thermostat = appliance.getActuatorFunctionalities().getFunctionalityThermostat();

        if (thermostat.isPresent()) {
            request.setPath("/core/appliances");

            request.addPathParameter("id", String.format("%s/thermostat", appliance.getId()));
            request.addPathParameter("id", String.format("%s", thermostat.get().getId()));    
            request.setBodyParameter(new ActuatorFunctionalityThermostat(temperature));

            executeRequest(request);
        }
    }

    public void switchRelay(Appliance appliance, String state) throws PlugwiseHAException {
        List<String> allowStates = Arrays.asList("on", "off");
        if (allowStates.contains(state.toLowerCase())) {
            if (state.toLowerCase().equals("on")) {
                switchRelayOn(appliance);
            } else {
                switchRelayOff(appliance);
            }
        }
    }

    public void switchRelayOn(Appliance appliance) throws PlugwiseHAException {
        PlugwiseHAControllerRequest<Void> request = newRequest(Void.class);
        Boolean relayLockState = appliance.getRelayLockState().orElse(null);

        request.setPath("/core/appliances");
        request.addPathParameter("id", String.format("%s/relay", appliance.getId()));
        request.setBodyParameter(new ActuatorFunctionalityRelay("on", relayLockState));

        executeRequest(request);
    }

    public void switchRelayOff(Appliance appliance) throws PlugwiseHAException {
        PlugwiseHAControllerRequest<Void> request = newRequest(Void.class);
        Boolean relayLockState = appliance.getRelayLockState().orElse(null);

        request.setPath("/core/appliances");
        request.addPathParameter("id", String.format("%s/relay", appliance.getId()));
        request.setBodyParameter(new ActuatorFunctionalityRelay("off", relayLockState));

        executeRequest(request);
    }

    public void switchRelayLock(Appliance appliance, String state) throws PlugwiseHAException {
        List<String> allowStates = Arrays.asList("on", "off");
        if (allowStates.contains(state.toLowerCase())) {
            if (state.toLowerCase().equals("on")) {
                switchRelayLockOn(appliance);
            } else {
                switchRelayLockOff(appliance);
            }
        }
    }

    public void switchRelayLockOff(Appliance appliance) throws PlugwiseHAException {
        PlugwiseHAControllerRequest<Void> request = newRequest(Void.class);

        request.setPath("/core/appliances");
        request.addPathParameter("id", String.format("%s/relay", appliance.getId()));
        request.setBodyParameter(new ActuatorFunctionalityRelay(null, false));

        executeRequest(request);
    }

    public void switchRelayLockOn(Appliance appliance) throws PlugwiseHAException {
        PlugwiseHAControllerRequest<Void> request = newRequest(Void.class);

        request.setPath("/core/appliances");
        request.addPathParameter("id", String.format("%s/relay", appliance.getId()));
        request.setBodyParameter(new ActuatorFunctionalityRelay(null, true));

        executeRequest(request);
    }

    public ZonedDateTime ping() {
        PlugwiseHAControllerRequest<Void> request;

        request = newRequest(Void.class, null);

        request.setPath("/cache/gateways");
        request.addPathParameter("ping");

        executeRequest(request);

        return ZonedDateTime.parse(request.getServerDateTime(), PlugwiseHAController.FORMAT);
    }

    // Protected and private methods

    private static Transformer setXSLT(StreamSource xsltSource) throws PlugwiseHAException {
        try {
            return TransformerFactory.newInstance().newTransformer(xsltSource);
        } catch (TransformerConfigurationException e) {
            throw new PlugwiseHAException("Could not create XML transformer", e);
        }
    }

    private <T> PlugwiseHAControllerRequest<T> newRequest(Class<T> responseType, Transformer transformer) {
        return new PlugwiseHAControllerRequest<T>(responseType, this.xStream, transformer, this.httpClient, this.host,
                this.port, this.username, this.smileId);
    }

    private <T> PlugwiseHAControllerRequest<T> newRequest(Class<T> responseType) {
        return new PlugwiseHAControllerRequest<T>(responseType, this.xStream, null, this.httpClient, this.host,
                this.port, this.username, this.smileId);
    }

    private <T> T executeRequest(PlugwiseHAControllerRequest<T> request) throws PlugwiseHAException {
        T result;
        result = request.execute();
        return result;
    }

    private DomainObjects mergeDomainObjects(DomainObjects domainObjects) {
        if (this.domainObjects != null) {
            this.domainObjects.mergeAppliances(domainObjects.getAppliances());
            this.domainObjects.mergeLocations(domainObjects.getLocations());
        } else {
            this.domainObjects = domainObjects;
        }

        return this.domainObjects;
    }
}