package app.models.converters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

/**
 * ElementConverter
 */
public class SelfClosingTagConverter implements Converter {
    private Mapper mapper;

    public SelfClosingTagConverter(Mapper mapper) {
        this.mapper = mapper;
    }

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        // We can convert any sub-class of actuatorFunctionality.
        return SelfClosingTag.class.isAssignableFrom(type);
        // return type.equals(SelfClosingTag.class);
    }

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        String element = ((SelfClosingTag) source).getValue();
        if (element != null) {
            writer.setValue(element);
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }
}
