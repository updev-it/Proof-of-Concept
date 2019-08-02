package app;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import app.models.Location;
import app.models.Locations;
import app.models.RelayFunctionality;
import app.models.ThermostatFunctionality;
import app.models.converters.AppliancesConverter;
import app.models.converters.LocationsConverter;

public class App {

    public static void main(String[] args) throws Exception {
        XStream xstream;
        XmlFriendlyNameCoder customCoder = new XmlFriendlyNameCoder("_-", "_");

        xstream = new XStream(new StaxDriver(customCoder));
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(Locations.class);
        // xstream.processAnnotations(ThermostatFunctionality.class);
        // xstream.processAnnotations(RelayFunctionality.class);
        xstream.registerConverter(new AppliancesConverter());
        // xstream.registerConverter(new LocationConverter());
        //xstream.registerConverter(new LocationsConverter());
        xstream.registerConverter(new LocationsConverter());
        // xstream.registerConverter(new ActuatorFunctionalitiesConverter());
        // xstream.registerConverter(new SelfClosingTagConverter(xstream.getMapper()));

        Locations locations1 = new Locations();
        Location location1 = new Location("location1_id", "woonkamer");
        Location location2 = new Location("location2_id", "badkamer");
        location1.addAppianceId("appliance1_id");
        location1.addAppianceId("appliance2_id");
        // location1.addAppiance(appliance1);
        // location1.addAppiance(appliance2);
        locations1.addLocation(location1);
        locations1.addLocation(location2);

        ThermostatFunctionality tfOne = new ThermostatFunctionality("thermostatid_1");
        RelayFunctionality rfOne = new RelayFunctionality("relayid_1");

        tfOne.setUpdatedDate("2019-05-08T12:48:17.751+02:00");
        tfOne.setLowerBound("0");
        tfOne.setUpperBound("99.99");
        tfOne.setResolution("0.01");
        tfOne.setSetpoint("18.0");
        tfOne.setType("thermostat");
        
        rfOne.setUpdatedDate("");

        
        location1.addThermostatFunctionality(tfOne);
        location1.addRelayFunctionality(rfOne);

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

        System.out.println(xml1);

        BufferedOutputStream stdout = new BufferedOutputStream(System.out);
        xstream.marshal(locations1r, new PrettyPrintWriter(new OutputStreamWriter(stdout), customCoder));
    }
}