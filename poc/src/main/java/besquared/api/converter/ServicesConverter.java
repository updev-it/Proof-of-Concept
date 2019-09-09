package besquared.api.converter;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import besquared.api.model.Service;
import besquared.api.model.Services;
import besquared.api.model.Service.ServiceIdType;

/**
 * ServicesConverter
 */
public class ServicesConverter extends BaseConverter<Service> {

    public ServicesConverter(Mapper mapper) {
        super(mapper, Services.class);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        return Services.class.isAssignableFrom(type);
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

            Service service = (Service) readBareItem(reader, context, map);
            String serviceId = service.getServiceId();

            map.put(serviceId, service);

            reader.moveUp();
        }
    }

}