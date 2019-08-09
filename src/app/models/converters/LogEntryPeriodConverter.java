package app.models.converters;

import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;

import app.models.LogEntryPeriod;
import app.models.Measurement;

/**
 * LogEntryPeriodConverter
 */
public class LogEntryPeriodConverter implements Converter {

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return clazz.equals(LogEntryPeriod.class);
    }

    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        LogEntryPeriod logEntryPeriod = (LogEntryPeriod) object;

        try {
            Measurement measurement = logEntryPeriod.getMeasurement();
            writer.addAttribute("start_date", logEntryPeriod.getStartDate());
            writer.addAttribute("end_date", logEntryPeriod.getEndDate());

            if (measurement != null) {
                writer.startNode("measurement");
                writer.addAttribute("log_date", measurement.getLogDate());
                writer.setValue(measurement.getValue().toString());
                writer.endNode();
            }
        } catch (StreamException e) {
            System.out.println(e.toString());
        }

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        LogEntryPeriod logEntryPeriod = new LogEntryPeriod(reader.getAttribute("start_date"),
                reader.getAttribute("end_date"));

        while (reader.hasMoreChildren()) {
            reader.moveDown();

            if ("measurement".equalsIgnoreCase(reader.getNodeName())) {
                // Note: Attributes will always have to be written and read first.
                // You work on a stream and accessing the value of a tag or its members will
                // close the surrounding tag
                // (that is still active when the method is called).
                //
                // See: http://x-stream.github.io/converter-tutorial.html#ComplexConverter
                String logDate = reader.getAttribute("log_date");
                String value = reader.getValue();

                Measurement measurement = new Measurement(value);
                measurement.setLogDate(logDate);

                logEntryPeriod.setMeasurement(measurement);
            }

            reader.moveUp();
        }

        return logEntryPeriod;
    }

}