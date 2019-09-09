package besquared.api.xml;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.TraxSource;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;

import besquared.api.converter.ModulesConverter;
import besquared.api.converter.ServiceConverter;
import besquared.api.converter.ServicesConverter;
import besquared.api.model.Module;
import besquared.api.model.Modules;
import besquared.api.model.Service;
import besquared.api.model.Services;

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
        this.allowClass(Modules.class);
        this.allowClass(Module.class);
        this.allowClass(Services.class);
        this.allowClass(Service.class);

        // Register custom converters
        this.registerConverter(new ModulesConverter(this.getMapper()));
        this.registerConverter(new ServicesConverter(this.getMapper()));        
        // this.registerConverter(new ServiceConverter(this.getMapper()));
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