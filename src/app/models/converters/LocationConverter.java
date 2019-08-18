package app.models.converters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;

import app.models.Location;

/**
 * LocationConverter
 */
public class LocationConverter extends PlugwiseHAConverter<Location> {

    // private final Logger logger = LoggerFactory.getLogger(LocationConverter.class);

    // public LocationConverter(PlugwiseHAController controller) {
    //     super(controller);
    // }

    public LocationConverter() {
        super();
    }

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return clazz.equals(Location.class);
    }

    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        // Locations locations = (Locations) object;

        // for (Map.Entry<String, Location> location : locations.entrySet()) {
        // Location currentLocation = location.getValue();
        // try {
        // writer.startNode("location");
        // context.convertAnother(currentLocation);
        // writer.endNode();
        // } catch (StreamException e) {
        // System.out.println(e.toString());
        // }
        // }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String id = reader.getAttribute("id");
        Location location = new Location(id);
        Class<?> clazz = location.getClass();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            try {
                String fieldName = reader.getNodeName().toLowerCase();
                Field field = null;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    // Try fields with XStream alias annotation

                    if (this.aliasedFields.containsKey(fieldName)) {
                        field = (Field) this.aliasedFields.get(fieldName);
                    }
                }

                if (field != null) {
                    Class<?> parentClass = (Class<?>) field.getType();
                    Object value = context.convertAnother(reader, parentClass);

                    field.setAccessible(true);
                    field.set(location, value);
                    field.setAccessible(false);
                }
            } catch (Exception e) {
                e.toString();
            }
            reader.moveUp();
        }

        return location;
    }
}