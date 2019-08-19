package api;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import api.converter.AppliancesConverter;
import api.converter.LocationsConverter;
import api.converter.LogConverter;
import api.converter.LogsConverter;
import api.model.Appliances;
import api.model.Locations;
import api.model.LogEntryPeriod;


/**
 * PlugwiseHAXml
 */
public class PlugwiseHAXml extends XStream {

    private static XmlFriendlyNameCoder customCoder = new XmlFriendlyNameCoder("_-", "_");

    public PlugwiseHAXml() {
        super(new StaxDriver(PlugwiseHAXml.customCoder));

        initialize();
    }

    // Protected methods
    
    protected void initialize() {
        // Configure XStream
        this.ignoreUnknownElements();
        this.setClassLoader(getClass().getClassLoader());

        // Register custom converters
        this.registerConverter(new LocationsConverter(this.getMapper(), "id"));
        this.registerConverter(new AppliancesConverter(this.getMapper(), "id"));
        this.registerConverter(new LogsConverter(this.getMapper()));
        this.registerConverter(new LogConverter(this.getMapper()));

        // Process annotationsLocations
        this.processAnnotations(Locations.class);
        this.processAnnotations(LogEntryPeriod.class);
        // this.processAnnotations(Appliances.class);        
    }

    // Public methods

    public Object fromXML(String xml) {
        return super.fromXML(xml);
    }

    @SuppressWarnings("rawtypes")
    public Object fromXML(String xml, Class outClass) {        
        try {
            Class.forName(outClass.getName());
            super.processAnnotations(outClass);
            return fromXML(xml);
        } catch (ClassNotFoundException e) {
            return null;
        }        
    }

    public String toXML(Object object) {
        return super.toXML(object);
    }

    @SuppressWarnings("rawtypes")
    public String toXML(Object object, Class outClass) {        
        try {
            Class.forName(outClass.getName());
            super.processAnnotations(outClass);
            return toXML(object);
        } catch (Exception e) {
            return null;
        }        
    }

    public void prettyPrint(Object object) {
        BufferedOutputStream stdout = new BufferedOutputStream(System.out);
        prettyPrint(object, new OutputStreamWriter(stdout));
    }

    public void prettyPrint(Object object, OutputStreamWriter outputStreamWriter) {
        this.marshal(object, new PrettyPrintWriter(outputStreamWriter, PlugwiseHAXml.customCoder));
    }
}