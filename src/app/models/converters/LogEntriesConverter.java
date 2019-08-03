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
            LogEntry currentLogEntry = logEntry.getValue();
            String currentLogEntryId = logEntry.getKey();
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

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map<String, LogEntry> logEntries = new HashMap<String, LogEntry>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            
            if ("point_log".equalsIgnoreCase(reader.getNodeName())) {
                // Note: Attributes will always have to be written and read first.
                // You work on a stream and accessing the value of a tag or its members will close the surrounding tag
                // (that is still active when the method is called).
                //
                // See: http://x-stream.github.io/converter-tutorial.html#ComplexConverters
                String id = reader.getAttribute("id");
                LogEntry entry = new LogEntry(id);
                while (reader.hasMoreChildren()) {
                    reader.moveDown();
                    switch (reader.getNodeName().toLowerCase()) {
                    case "type":
                        entry.setType((String) context.convertAnother(reader, String.class));
                        break;
                    case "temperature":
                        entry.setTemperature((Double) context.convertAnother(reader, Double.class));
                        break;
                    case "thermo_meter":
                        entry.setThermometer((Thermometer) context.convertAnother(reader, Thermometer.class));
                        break;
                    case "updated_date":
                        entry.setUpdatedDate((String) context.convertAnother(reader, String.class));
                        break;
                    case "period":
                        // entry.setLogEntryPeriod((LogEntryPeriod) new LogEntryPeriodConverter().unmarshal(reader, context));
                        entry.setLogEntryPeriod((LogEntryPeriod) context.convertAnother(reader, LogEntryPeriod.class, new LogEntryPeriodConverter()));
                        break;
                    }
                    reader.moveUp();
                }
                logEntries.put(entry.getId(), entry);
            }

            reader.moveUp();
        }
        return logEntries;
    }
}