package besquared.api.converter;

import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import besquared.api.model.Appliance;
import besquared.api.model.Appliances;

/**
 * AppliancesConverter
 */
public class AppliancesConverter extends BaseConverter<Appliances> { // extends MapConverter implements Converter {

    public AppliancesConverter(Mapper mapper) {
        super(mapper, Appliances.class);
    }

    @Override
    public boolean canConvert(Class cls) {
        return Appliances.class.isAssignableFrom(cls);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Appliances map = new Appliances();

        populateMap(reader, context, map);

        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Appliances map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String key = reader.getAttribute(this.attributeName);
            Appliance appliance = (Appliance) readBareItem(reader, context, map);

            if (appliance != null) {
                map.put(key, appliance);
            }

            reader.moveUp();
        }
    }
}