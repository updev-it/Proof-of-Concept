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
 * MeasurementsConverter
 */
public class MeasurementsConverter implements Converter {

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return clazz.equals(HashMap.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        Map<String, Measurement> measurementList = (Map<String, Measurement>) object;

        for (Map.Entry<String, Measurement> measurement : measurementList.entrySet()) {
            Measurement currentMeasurement = measurement.getValue();
            String currentMeasurementLogDate = measurement.getKey();
            try {
                writer.startNode("measurement");
                writer.addAttribute("log_date", currentMeasurementLogDate);
                writer.setValue(currentMeasurement.getValue().toString());                
                writer.endNode();
            } catch (StreamException e) {
                System.out.println(e.toString());
            }
        }

        // try {
        //     // writer.startNode("measurement");
        //     writer.addAttribute("log_date", measurement.getLogDate());
        //     writer.setValue(measurement.getValue().toString());            
        //     // writer.endNode();
        // } catch (StreamException e) {
        //     System.out.println(e.toString());
        // }

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }

}