package app.models.converters;

import java.util.ArrayList;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * AppliancesConverter
 */
public class AppliancesConverter implements Converter {

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return clazz.equals(ArrayList.class);
    }

    @SuppressWarnings("unchecked")
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        ArrayList<String> applianceList = (ArrayList<String>) object;

        for (String appliance : applianceList) {
            writer.startNode("appliance");
            writer.addAttribute("id", appliance);
            writer.endNode();
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        ArrayList<String> applianceList = new ArrayList<String>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            if ("appliance".equalsIgnoreCase(nodeName)) {
                // Note: Attributes will always have to be written and read first.
                // You work on a stream and accessing the value of a tag or its members will close the surrounding tag
                // (that is still active when the method is called).
                //
                // See: http://x-stream.github.io/converter-tutorial.html#ComplexConverter
                applianceList.add(reader.getAttribute("id"));
            }

            reader.moveUp();
        }
        return applianceList;
    }
}