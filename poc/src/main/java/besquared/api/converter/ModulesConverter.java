package besquared.api.converter;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import org.apache.commons.lang.NotImplementedException;

import besquared.api.model.Module;
import besquared.api.model.Modules;

/**
 * ModulesConverter
 */
public class ModulesConverter extends BaseConverter<Modules> {

    private final String attributeName;

    // Constructor

    public ModulesConverter(Mapper mapper) {
        super(mapper, Modules.class);

        this.attributeName = "id";
    }

    // Overrides

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class cls) {
        return Modules.class.isAssignableFrom(cls);
    }

    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new NotImplementedException();
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
            String key = reader.getAttribute(this.attributeName);
            Module module = (Module) readBareItem(reader, context, map);

            map.put(key, module);

            reader.moveUp();
        }
    }
}