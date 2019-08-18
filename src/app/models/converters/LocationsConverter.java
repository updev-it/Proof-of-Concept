package app.models.converters;

import java.util.ArrayList;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.mapper.Mapper;

import app.models.ActuatorFunctionalities;
import app.models.Location;
import app.models.Locations;
import app.models.LogEntry;

/**
 * LocationsConverter
 */
public class LocationsConverter extends PlugwiseHAConverter<Locations> {

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(Locations.class);
    }

    @Override
    public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Locations locations = new Locations();

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if ("location".equalsIgnoreCase(reader.getNodeName())) {
                Location location = (Location) context.convertAnother(reader, Location.class);
                locations.put(location.getId(), location);
            }
            reader.moveUp();
        }

        return locations;
    }




}