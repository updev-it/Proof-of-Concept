package besquared.api.converter;

import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import besquared.api.model.Log;
import besquared.api.model.Logs;

/**
 * LogsConverter
 */
public class LogsConverter extends BaseConverter<Log> {

    public LogsConverter(Mapper mapper) {
        super(mapper, Logs.class);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        return Logs.class.isAssignableFrom(type);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Logs map = new Logs();

        populateMap(reader, context, map);

        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Logs map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();

            // Log log = (Log) readBareItem(reader, context, map);            

            // map.put(log.getId(), log);

            reader.moveUp();
        }
    }

}