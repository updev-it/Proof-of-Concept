package besquared.api.converter;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import besquared.api.model.Location;
import besquared.api.model.Locations;

/**
 * LocationsConverter
 */
public class LocationsConverter extends BaseConverter<Locations> {

    private final String attributeName;

    // Constructor

    public LocationsConverter(Mapper mapper, String attributeName) {
        super(mapper, Locations.class);

        this.attributeName = attributeName;
    }

    // Overrides

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class cls) {
        return Locations.class.isAssignableFrom(cls);
    }

    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        super.marshal(object, writer, context);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Locations map = new Locations();

        populateMap(reader, context, map);

        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Locations map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String key = reader.getAttribute(this.attributeName);
            Location location = (Location) readItem(reader, context, map);

            if (location != null) {
                map.put(key, location);
            }

            reader.moveUp();
        }
    }
}