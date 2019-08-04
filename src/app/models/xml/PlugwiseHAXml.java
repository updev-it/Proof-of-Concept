package app.models.xml;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import app.models.Locations;
import app.models.converters.AppliancesConverter;
import app.models.converters.LocationsConverter;
import app.models.converters.LogEntriesConverter;

/**
 * PlugwiseHAXml
 */
public class PlugwiseHAXml extends XStream {

    public static XmlFriendlyNameCoder customCoder = new XmlFriendlyNameCoder("_-", "_");

    public PlugwiseHAXml() {
        super(new StaxDriver(PlugwiseHAXml.customCoder));

        initialize();
    }

    // Protected methods
    
    protected void initialize() {
        // Configure XStream
        this.ignoreUnknownElements();

        // Register custom converters
        this.registerConverter(new AppliancesConverter());
        this.registerConverter(new LocationsConverter());
        this.registerConverter(new LogEntriesConverter());

        // Process annotations
        this.processAnnotations(Locations.class);
    }

    // Public methods

    public void prettyPrint(Object object) {
        BufferedOutputStream stdout = new BufferedOutputStream(System.out);
        prettyPrint(object, new OutputStreamWriter(stdout));
    }

    public void prettyPrint(Object object, OutputStreamWriter outputStreamWriter) {
        this.marshal(object, new PrettyPrintWriter(outputStreamWriter, PlugwiseHAXml.customCoder));
    }
}