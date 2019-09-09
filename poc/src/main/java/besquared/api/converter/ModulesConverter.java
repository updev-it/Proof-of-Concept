package besquared.api.converter;

import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import besquared.api.model.Module;
import besquared.api.model.Modules;

/**
 * ModulesConverter
 */
public class ModulesConverter extends BaseConverter<Module> {

    public ModulesConverter(Mapper mapper) {
        super(mapper, Modules.class);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        return Modules.class.isAssignableFrom(type);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Modules map = new Modules();

        populateMap(reader, context, map);

        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Modules map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();            
            Module module = (Module) readBareItem(reader, context, map);

            map.put(module.getId(), module);

            reader.moveUp();
        }
    }

}