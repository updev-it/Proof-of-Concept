package app.models.converters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.core.DefaultConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.mapper.Mapper;

import app.models.ActuatorFunctionalities;
import app.models.Appliance;
import app.models.Location;
import app.models.Locations;

/**
 * LocationsConverter
 */
public class LocationsConverter implements Converter {

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return clazz.equals(Locations.class);
    }

    @SuppressWarnings("unchecked")
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        Locations locations = (Locations) object;

        for (Map.Entry<String, Location> location : locations.getLocations().entrySet()) {
            Location currentLocation = location.getValue();
            String currentLocationId = location.getKey();
            try {
                writer.startNode("location");
                context.convertAnother(currentLocation);
                writer.endNode();
            } catch (StreamException e) {
                System.out.println(e.toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Locations locations = new Locations();

        while (reader.hasMoreChildren()) {
            reader.moveDown();

            if ("location".equalsIgnoreCase(reader.getNodeName())) {
                String id = reader.getAttribute("id");
                Location loc = new Location(id);
                while (reader.hasMoreChildren()) {
                    reader.moveDown();
                    switch (reader.getNodeName().toLowerCase()) {
                    case "name":
                        loc.setName((String) context.convertAnother(reader, String.class));
                        break;
                    case "appliances":
                        ArrayList<String> applianceList = (ArrayList<String>)context.convertAnother(reader, ArrayList.class);
                        loc.setApplianceList(applianceList);                    
                        break;
                    case "actuator_functionalities":
                    ActuatorFunctionalities actuatorFunctionalities = (ActuatorFunctionalities)context.convertAnother(reader, ActuatorFunctionalities.class);
                    loc.setActuatorFunctionalities(actuatorFunctionalities);                  
                    break;
                    }
                    reader.moveUp();
                }
                locations.addLocation(loc);
            }

            reader.moveUp();
        }
        return locations;
    }
}