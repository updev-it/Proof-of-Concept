package app.models.converters;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;

import app.models.Measurement;

/**
 * MeasurementConverter
 */
public class MeasurementConverter implements Converter {

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return clazz.equals(HashMap.class);
    }

    @Override    
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        Measurement measurement = (Measurement) object;

        try {
            writer.startNode("measurement");
            writer.addAttribute("log_date", measurement.getLogDate());
            writer.setValue(measurement.getValue().toString());
            writer.endNode();
        } catch (StreamException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }

}