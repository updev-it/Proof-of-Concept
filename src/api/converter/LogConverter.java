package api.converter;

import java.lang.reflect.Field;
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
import api.model.LogEntryPeriod;
import api.model.LogType;
import api.model.LogTemperature;
import api.model.Logs;

/**
 * LogConverter
 */
public class LogConverter extends BaseConverter<LogEntry> {

    private final String attributeName;

    public LogConverter(Mapper mapper) {
        this(mapper, null);
    }

    public LogConverter(Mapper mapper, String attributeName) {
        super(mapper);

        this.attributeName = attributeName;
    }

    @Override
    public boolean canConvert(Class cls) {
        return LogEntry.class.isAssignableFrom(cls);
    }

    @Override
    public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
        // super.marshal(arg0, arg1, arg2);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return populateStringMap(reader, context);
    }

    protected LogType populateStringMap(HierarchicalStreamReader reader, UnmarshallingContext context) {

        LogType logEntry = null;
        LogEntryPeriod logEntryPeriod = null;
        Map<String, String> rawLogEntry = new HashMap<String, String>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();

            String nodeName = reader.getNodeName().toLowerCase();
            if (nodeName.equals("period")) {
                logEntryPeriod = (LogEntryPeriod) context.convertAnother(reader, LogEntryPeriod.class);
            } else {
                rawLogEntry.put(nodeName, (String) context.convertAnother(reader, String.class));
            }
            reader.moveUp();
        }

        String type = rawLogEntry.get("type");
        if (type != null && !type.isEmpty()) {
            switch (type) {
            case "battery":
                // logEntry = new LogEntryBattery(id);
                // this.assignClass(LogEntryBattery.class);
                // // logEntry.setUpdatedDate(rawLogEntry.get("updated_date"));
                // // logEntry.setLogEntryPeriod(logEntryPeriod);
                // //
                // logEntry.setLastConsecutiveLogDate(rawLogEntry.get("last_consecutive_log_date"));
            case "thermostat":
            case "uncorrected_temperature":
            case "temperature":
            case "temperature_offset":
            case "temperature_difference":
                this.assignClass(LogTemperature.class);
                logEntry = new LogTemperature();
                break;
            default:
                logEntry = new LogEntry();
                break;
            }
        }

        if (logEntry != null) {

            Field periodField = null;

            try {
                try {
                    periodField = logEntry.getClass().getDeclaredField("period");
                } catch (NoSuchFieldException e) {
                    // Try fields with XStream alias annotation
                    if (this.aliasedFields.containsKey("period")) {
                        periodField = (Field) this.aliasedFields.get("period");
                    }
                }

                if (periodField != null) {
                    periodField.setAccessible(true);
                    periodField.set(logEntry, logEntryPeriod);
                    periodField.setAccessible(false);
                }
            } catch (Exception e) {
                // Fail semi-silent
                // this.logger.warn("Unmarshalling of class {} threw exception: {}",
                // this.clazz.toString(),
                // e.getMessage());
            }

            for (Map.Entry<String, String> entry : rawLogEntry.entrySet()) {
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue();

                try {
                    Field field = null;

                    try {
                        field = logEntry.getClass().getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e) {
                        // Try fields with XStream alias annotation
                        if (this.aliasedFields.containsKey(fieldName)) {
                            field = (Field) this.aliasedFields.get(fieldName);
                        }
                    }

                    if (field != null) {
                        // if (logger.isTraceEnabled()) {
                        // this.logger.trace(
                        // "Trying to set property '{}' to value '{}' with type '{}' on logEntry
                        // object",
                        // fieldName, fieldValue.toString(), this.clazz.toString());
                        // }

                        field.setAccessible(true);
                        field.set(logEntry, fieldValue);
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    // Fail semi-silent
                    // this.logger.warn("Unmarshalling of class {} threw exception: {}",
                    // this.clazz.toString(),
                    // e.getMessage());
                }
            }
        }
        return logEntry;
    }
}