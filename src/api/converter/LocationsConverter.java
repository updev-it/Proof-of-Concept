package api.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import api.model.Location;
import api.model.Locations;

/**
 * LocationsConverter
 */
public class LocationsConverter extends MapConverter implements Converter {

    private final String attributeName;

    public LocationsConverter(Mapper mapper, String attributeName) {
        super(mapper, Locations.class);

        this.attributeName = attributeName;
    }

    @Override
    public boolean canConvert(Class cls) {
        return Locations.class.isAssignableFrom(cls);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Locations map = new Locations();

        populateStringMap(reader, context, map);

        return map;
    }

	protected void populateStringMap(HierarchicalStreamReader reader, UnmarshallingContext context, Locations map) {
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			String key = reader.getAttribute(this.attributeName);
			Location value = (Location) readItem(reader, context, map);
			reader.moveUp();
			map.put(key, value);
		}
	}
}