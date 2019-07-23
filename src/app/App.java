package app;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import app.models.Appliance;
import app.models.Location;
import app.models.Locations;
import app.models.RelayFunctionality;
import app.models.ThermostatFunctionality;
import app.models.converters.SelfClosingTagConverter;

public class App {

    public static void main(String[] args) throws Exception {
        XStream xstream;
        XmlFriendlyNameCoder customCoder = new XmlFriendlyNameCoder("_-", "_");

        xstream = new XStream(new StaxDriver(customCoder));
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(Locations.class);
        xstream.processAnnotations(ThermostatFunctionality.class);
        xstream.processAnnotations(RelayFunctionality.class);
        xstream.registerConverter(new SelfClosingTagConverter(xstream.getMapper()));

        Locations locations1 = new Locations();
        Location location1 = new Location("location1_id", "woonkamer");
        Appliance appliance1 = new Appliance("appliance1_id");
        Appliance appliance2 = new Appliance("appliance2_id");
        location1.addAppiance(appliance1);
        location1.addAppiance(appliance2);
        locations1.addLocation(location1);

        String xml1 = xstream.toXML(locations1);
        Locations locations1r = (Locations) xstream.fromXML(xml1);
        String xml1r = xstream.toXML(locations1r);

        System.out.println();
        System.out.println("===============================");

        if (xml1.intern() == xml1r.intern()) {
            System.out.println("XML DATA MATCHES");
        } else {
            System.out.println("XML DATA DOES NOT MATCH");
        }

        System.out.println();

        BufferedOutputStream stdout = new BufferedOutputStream(System.out);
        xstream.marshal(locations1, new PrettyPrintWriter(new OutputStreamWriter(stdout), customCoder));
    }
}