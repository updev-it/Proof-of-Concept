package besquared.api.xml;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;

import besquared.api.converter.DateTimeConverter;
import besquared.api.model.ActuatorFunctionalities;
import besquared.api.model.ActuatorFunctionality;
import besquared.api.model.ActuatorFunctionalityRelay;
import besquared.api.model.ActuatorFunctionalityThermostat;
import besquared.api.model.Appliance;
import besquared.api.model.Appliances;
import besquared.api.model.Location;
import besquared.api.model.Locations;
import besquared.api.model.GatewayInfo;
import besquared.api.model.DomainObjects;
import besquared.api.model.GatewayEnvironment;
import besquared.api.model.Log;
import besquared.api.model.Logs;

/**
 * PlugwiseHAXStream
 */
public class PlugwiseHAXStream extends XStream {

    private static XmlFriendlyNameCoder customCoder = new XmlFriendlyNameCoder("_-", "_");

    public PlugwiseHAXStream() {
        super(new StaxDriver(PlugwiseHAXStream.customCoder));

        initialize();
    }

    // Protected methods

    @SuppressWarnings("rawtypes")
    protected void allowClass(Class clz) {
        this.processAnnotations(clz);
        this.allowTypeHierarchy(clz);
    }

    protected void initialize() {
        // Configure XStream
        this.ignoreUnknownElements();
        this.setClassLoader(getClass().getClassLoader());

        // Clear out existing permissions
        this.addPermission(NoTypePermission.NONE);
        this.addPermission(NullPermission.NULL);

        // Whitelist classes   
        this.allowClass(GatewayInfo.class);
        this.allowClass(GatewayEnvironment.class);        
        this.allowClass(Appliances.class);
        this.allowClass(Appliance.class);
        this.allowClass(Locations.class);
        this.allowClass(Location.class);
        this.allowClass(Logs.class);
        this.allowClass(Log.class);
        this.allowClass(ActuatorFunctionalities.class);
        this.allowClass(ActuatorFunctionality.class);
        this.allowClass(ActuatorFunctionalityThermostat.class);
        this.allowClass(ActuatorFunctionalityRelay.class);
        this.allowClass(DomainObjects.class);

        // Register custom converters
        this.registerConverter(new DateTimeConverter());
    }

    // Public methods

    public void prettyPrint(Object object) {
        BufferedOutputStream stdout = new BufferedOutputStream(System.out);
        prettyPrint(object, new OutputStreamWriter(stdout));
    }

    public void prettyPrint(Object object, OutputStreamWriter outputStreamWriter) {
        this.marshal(object, new PrettyPrintWriter(outputStreamWriter, PlugwiseHAXStream.customCoder));
    }
}