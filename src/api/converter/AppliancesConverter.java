package api.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import api.model.Appliance;
import api.model.Appliances;

/**
 * AppliancesConverter
 */
public class AppliancesConverter extends MapConverter implements Converter {

    private final String attributeName;

    public AppliancesConverter(Mapper mapper, String attributeName) {
        super(mapper, Appliances.class);

        this.attributeName = attributeName;
    }

    @Override
    public boolean canConvert(Class cls) {
        return Appliances.class.isAssignableFrom(cls);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Appliances map = new Appliances();

        populateStringMap(reader, context, map);

        return map;
    }

    protected void populateStringMap(HierarchicalStreamReader reader, UnmarshallingContext context, Appliances map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String key = reader.getAttribute(this.attributeName);
            Appliance value = (Appliance) readItem(reader, context, map);
            reader.moveUp();
            map.put(key, value);
        }
    }
}