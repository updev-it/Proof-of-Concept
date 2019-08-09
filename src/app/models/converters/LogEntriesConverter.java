package app.models.converters;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;

import app.models.LogEntry;
import app.models.LogEntryPeriod;
import app.models.LogEntryTemperature;
import app.models.Thermometer;

/**
 * LogEntriesConverter
 */
public class LogEntriesConverter implements Converter {

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return clazz.equals(HashMap.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        Map<String, LogEntry> logEntries = (Map<String, LogEntry>) object;

        for (Map.Entry<String, LogEntry> logEntry : logEntries.entrySet()) {
            String type = logEntry.getKey();
            String currentLogEntryId = logEntry.getValue().getId();
            LogEntry currentLogEntry = null;

            switch (type) {
            case "temperature":
                currentLogEntry = (LogEntryTemperature) logEntry.getValue();
                break;
            default:
                break;
            }

            if (currentLogEntry != null) {
                try {
                    writer.startNode("point_log");
                    writer.addAttribute("id", currentLogEntryId);
                    context.convertAnother(currentLogEntry);
                    writer.endNode();
                } catch (StreamException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map<String, LogEntry> logEntries = new HashMap<String, LogEntry>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();

            if ("point_log".equalsIgnoreCase(reader.getNodeName())) {
                // Note: Attributes will always have to be written and read first.
                // You work on a stream and accessing the value of a tag or its members will
                // close the surrounding tag
                // (that is still active when the method is called).
                //
                // See: http://x-stream.github.io/converter-tutorial.html#ComplexConverters
                String id = reader.getAttribute("id");
                LogEntry logEntry = null;
                LogEntryPeriod logEntryPeriod = null;
                Map<String, String> rawLogEntry = new HashMap<String, String>();

                while (reader.hasMoreChildren()) {
                    reader.moveDown();

                    String nodeName = reader.getNodeName().toLowerCase();
                    if (nodeName.equals("period")) {
                        logEntryPeriod = (LogEntryPeriod) context.convertAnother(rawLogEntry.get("period"),
                                LogEntryPeriod.class, new LogEntryPeriodConverter());
                    } else {
                        rawLogEntry.put(nodeName, (String) context.convertAnother(reader, String.class));
                    }
                    reader.moveUp();
                }

                String type = rawLogEntry.get("type");
                if (type != null && !type.isEmpty()) {
                    switch (type) {
                    case "temperature":
                        logEntry = new LogEntryTemperature(id);
                        logEntry.setUpdatedDate(rawLogEntry.get("updated_date"));
                        logEntry.setLogEntryPeriod(logEntryPeriod);
                        break;
                    default:
                        break;
                    }
                }
                if (logEntry != null) {
                    logEntries.put(logEntry.getType(), logEntry);
                }
            }
            reader.moveUp();
        }
        return logEntries;
    }
}