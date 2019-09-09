package besquared.api.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import besquared.api.model.ActuatorFunctionalities;
import besquared.api.model.ActuatorFunctionalityOffset;
import besquared.api.model.ActuatorFunctionalityRelay;
import besquared.api.model.ActuatorFunctionalityThermostat;
import besquared.api.model.ActuatorFunctionalityType;

/**
 * ActuatorFunctionalitiesConverter
 */
public class ActuatorFunctionalitiesConverter extends MapConverter implements Converter {

    private final String attributeName;

    public ActuatorFunctionalitiesConverter(Mapper mapper) {
        this(mapper, null);
    }

    public ActuatorFunctionalitiesConverter(Mapper mapper, String attributeName) {
        super(mapper, ActuatorFunctionalities.class);

        this.attributeName = attributeName;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class cls) {
        return ActuatorFunctionalities.class.isAssignableFrom(cls);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        ActuatorFunctionalities map = new ActuatorFunctionalities();

        populateStringMap(reader, context, map);

        return map;
    }

    protected void populateStringMap(HierarchicalStreamReader reader, UnmarshallingContext context,
            ActuatorFunctionalities map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String key = reader.getAttribute(this.attributeName);
            String type = reader.getNodeName();
            ActuatorFunctionalityType actuatorFunctionalityType = null;

            switch (type) {
            case "thermostat_functionality":
                actuatorFunctionalityType = (ActuatorFunctionalityThermostat) readItem(reader, context, map);
                break;
            case "relay_functionality":
                actuatorFunctionalityType = (ActuatorFunctionalityRelay) readItem(reader, context, map);
                break;
            case "offset_functionality":
                actuatorFunctionalityType = (ActuatorFunctionalityOffset) readItem(reader, context, map);
                break;
            default:
                // value = (ActuatorFunctionalityType) readItem(reader, context, map);
                break;
            }

            if (actuatorFunctionalityType != null) {
                map.put(key, actuatorFunctionalityType);
            }

            reader.moveUp();
        }
    }
}