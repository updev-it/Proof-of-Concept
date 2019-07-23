package app.models.converters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import app.models.ApplianceList;

/**
 * AppliancesConverter
 */
public class AppliancesConverter implements Converter {

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return ApplianceList.class == clazz;
    }

    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        ApplianceList applianceList = (ApplianceList) object;
        for (String appliance : applianceList) {
            writer.startNode("appliance");
            writer.addAttribute("id", appliance);
            writer.endNode();
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        ApplianceList applianceList = new ApplianceList();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            if ("appliance".equalsIgnoreCase(nodeName)) {                
                applianceList.add(reader.getAttribute("id"));
            }

            reader.moveUp();
        }
        return applianceList;

    }
}