package besquared.api.converter;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import org.apache.commons.lang.NotImplementedException;

import besquared.api.model.Service;
import besquared.api.model.ServiceType;
import besquared.api.model.Services;

/**
 * ServicesConverter
 */
public class ServicesConverter extends BaseConverter<Services> {

    private final String attributeName;

    // Constructor

    public ServicesConverter(Mapper mapper) {
        super(mapper, Services.class);

        this.attributeName = "id";
    }

    // Overrides

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class cls) {
        return Services.class.isAssignableFrom(cls);
    }

    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new NotImplementedException();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Services map = new Services();

        populateMap(reader, context, map);

        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Services map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();

            ServiceType service = (ServiceType) context.convertAnother(reader, ServiceType.class);

            map.put(service.getType(), service);

            reader.moveUp();
        }
    }
}