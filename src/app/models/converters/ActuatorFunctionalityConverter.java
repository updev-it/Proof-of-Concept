package app.models.converters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * ActuatorFunctionalityConverter
 */
public class ActuatorFunctionalityConverter implements Converter {

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return true;
    }

    public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {

    }
  
    public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {
        return null;
    }
}