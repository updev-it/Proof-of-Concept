package api.converter;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import api.model.LogEntry;
import api.model.LogType;
import api.model.Logs;

/**
 * LogsConverter
 */
public class LogsConverter extends MapConverter implements Converter {

    // Constructor

    public LogsConverter(Mapper mapper) {
        super(mapper, Logs.class);
    }

    // Overrides

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class cls) {
        return Logs.class.isAssignableFrom(cls);
    }

    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        super.marshal(object, writer, context);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Logs map = new Logs();

        populateStringMap(reader, context, map);

        return map;
    }

    protected void populateStringMap(HierarchicalStreamReader reader, UnmarshallingContext context, Logs map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            LogType logEntry = (LogType) context.convertAnother(reader, LogEntry.class);

            if (logEntry != null) {
                map.put(logEntry.getType(), logEntry);
            }

            reader.moveUp();
        }
    }

}