package besquared;

import java.time.ZonedDateTime;
import java.util.List;

import org.eclipse.jetty.client.HttpClient;

import besquared.api.exception.PlugwiseHAException;
import besquared.api.model.Appliance;
import besquared.api.model.DomainObjects;
import besquared.api.model.Location;
import besquared.api.model.PlugwiseHAController;


public class App {

    public static void main(String[] args) throws Exception {
        PlugwiseHAController controller;
        HttpClient httpClient;
        String host = "adam";
        int port = 80;
        String username = "smile";
        String smileId = "dsttvzdm";

        httpClient = new HttpClient();
        httpClient.start();

        controller = new PlugwiseHAController(httpClient, host, port, username, smileId);

        try {
            // Appliances xmlAppliances = controller.getAppliances();      
            // Locations xmlLocations = controller.getLocations();
            // GatewayInfo xmlGatewayInfo = controller.getGatewayInfo();
            DomainObjects domainObjects = controller.getUpdatedDomainObjects();            
            Location location = domainObjects.getLocations().get("b23c6cd50ed843eca8706942c16e1085");
            Appliance appliance = domainObjects.getAppliances().get("3fbbc4e3513d4621a98bc9e28ab76d15");

            // controller.switchRelayOn(appliance);
            controller.switchRelayLockOff(appliance);


            // controller.setLocationThermostat(location, 10.0);
            domainObjects = controller.getUpdatedDomainObjects();
            // GatewayInfo xmlGateway = controller.getGatewayInfo();
            System.out.println(domainObjects.toString());

        } catch (PlugwiseHAException e) {
            System.out.println(e.getMessage());
        }
    }
}