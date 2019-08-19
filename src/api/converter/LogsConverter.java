package api.converter;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import api.model.LogEntry;
import api.model.LogType;
import api.model.Logs;

/**
 * LogsConverter
 */
public class LogsConverter extends MapConverter implements Converter {

    private final String attributeName;

    public LogsConverter(Mapper mapper) {
        this(mapper, null);
    }

    public LogsConverter(Mapper mapper, String attributeName) {
        super(mapper, Logs.class);

        this.attributeName = attributeName;
    }

    @Override
    public boolean canConvert(Class cls) {
        return Logs.class.isAssignableFrom(cls);
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
            if ("point_log".equalsIgnoreCase(reader.getNodeName())) {
                LogType logEntry = (LogType) context.convertAnother(reader, LogEntry.class);
                map.put(logEntry.getType(), logEntry);
            }
            reader.moveUp();
        }
    }
}