package app;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import app.models.Location;
import app.models.Locations;
import app.models.LogEntry;
import app.models.LogEntryPeriod;
import app.models.Measurement;
import app.models.Thermometer;
import app.models.RelayFunctionality;
import app.models.ThermostatFunctionality;
import app.models.converters.AppliancesConverter;
import app.models.converters.LocationsConverter;
import app.models.converters.LogEntriesConverter;

public class App {

    public static void main(String[] args) throws Exception {
        XStream xstream;
        XmlFriendlyNameCoder customCoder = new XmlFriendlyNameCoder("_-", "_");

        xstream = new XStream(new StaxDriver(customCoder));
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(Locations.class);
        xstream.registerConverter(new AppliancesConverter());
        xstream.registerConverter(new LocationsConverter());
        xstream.registerConverter(new LogEntriesConverter());

        // Create locations object
        Locations locations = new Locations();

        // Create 2 locations (woonkamer & badkamer)
        Location location1 = new Location("location1_id", "woonkamer");
        Location location2 = new Location("location2_id", "badkamer");

        // Add 2 appliances to location woonkamer
        location1.addAppianceId("appliance1_id");
        location1.addAppianceId("appliance2_id");

        // Add 1 appliance to location badkamer
        location2.addAppianceId("appliance3_id");

        // Create 2 ActuatorFunctionalities (i.e. ThermostatFunctionality and
        // RelayFunctionality)
        ThermostatFunctionality tfOne = new ThermostatFunctionality("thermostatid_1");
        RelayFunctionality rfOne = new RelayFunctionality("relayid_1");

        // Configure ThermostatFunctionality
        tfOne.setUpdatedDate("2019-05-08T12:48:17.751+02:00");
        tfOne.setLowerBound("0");
        tfOne.setUpperBound("99.99");
        tfOne.setResolution("0.01");
        tfOne.setSetpoint("18.0");
        tfOne.setType("thermostat");

        // Configure RelayFunctionality
        rfOne.setUpdatedDate("");

        // Add the ActuatorFunctionalities to woonkamer and badkamer
        location1.addThermostatFunctionality(tfOne);
        location2.addRelayFunctionality(rfOne);

        // Add log entries to woonkamer
        LogEntry logEntry1 = new LogEntry("2a4be3ab8f464f529e24a9c8aff110e1", "C", new Double("6.67"));
        LogEntryPeriod logEntryPeriod1 = new LogEntryPeriod("2019-08-03T11:30:46.147+02:00",
                "2019-08-03T11:30:46.147+02:00");
        Measurement measurement1 = new Measurement("24.8");
        measurement1.setLogDate("2019-08-03T11:30:46.147+02:00");
        logEntryPeriod1.addMeasurement(measurement1);
        logEntry1.setUpdatedDate("2019-08-03T11:30:46.147+02:00");
        logEntry1.setThermometer(new Thermometer("ee8de50451f1441583388626a044bc1f"));
        logEntry1.setLogEntryPeriod(logEntryPeriod1);
        location1.addLogEntry(logEntry1);

        // Add log entries to badkamer
        LogEntry logEntry2 = new LogEntry("b65a260dbfba4081b1c35ed3b699370b", "F", new Double("103.5"));
        logEntry2.setUpdatedDate("2019-08-03T11:30:46.147+02:00");
        logEntry2.setThermometer(new Thermometer("ee8de50451f1441583388626a044bc1f"));
        logEntry2.setLogEntryPeriod(
                new LogEntryPeriod("2019-08-03T11:30:46.147+02:00", "2019-08-03T11:30:46.147+02:00"));
        location2.addLogEntry(logEntry2);

        // Add both locations to the root locations object
        locations.addLocation(location1);
        locations.addLocation(location2);

        String xml1 = xstream.toXML(locations);
        Locations locations_r = (Locations) xstream.fromXML(xml1);
        String xml1r = xstream.toXML(locations_r);

        // System.out.println(xml1);

        BufferedOutputStream stdout = new BufferedOutputStream(System.out);
        xstream.marshal(locations_r, new PrettyPrintWriter(new OutputStreamWriter(stdout), customCoder));

        System.out.println();
        System.out.println("===============================");

        if (xml1.intern() == xml1r.intern()) {
            System.out.println("XML DATA MATCHES");
        } else {
            System.out.println("XML DATA DOES NOT MATCH");
        }

        System.out.println();
    }
}