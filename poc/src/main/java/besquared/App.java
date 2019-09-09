package besquared;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.jetty.client.HttpClient;

import besquared.api.exception.PlugwiseHAException;
import besquared.api.model.Modules;
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
            Modules xmlModules = controller.getModules();          
            xmlModules.toString();

        } catch (PlugwiseHAException e) {
            System.out.println(e.getMessage());
        }
    }
}