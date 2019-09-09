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
 * ServiceConverter
 */
public class ServiceConverter extends BaseConverter<Service> {

    private final String attributeName;

    // Constructor

    public ServiceConverter(Mapper mapper) {
        super(mapper, Services.class);

        this.attributeName = "id";
    }

    // Overrides

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class cls) {
        return Service.class.isAssignableFrom(cls);
    }

    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new NotImplementedException();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

        while (reader.hasMoreChildren()) {
            reader.moveDown();

            reader.moveUp();
        }

        return null;
    }
}