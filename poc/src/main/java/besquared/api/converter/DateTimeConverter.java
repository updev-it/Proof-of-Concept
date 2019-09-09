package besquared.api.converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang.StringUtils;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * DateTimeConverter
 */
public class DateTimeConverter extends AbstractSingleValueConverter {

    private final static DateTimeFormatter FORMAT = DateTimeFormatter.ISO_OFFSET_DATE_TIME; // default Date format that will be used in conversion
    
    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        return ZonedDateTime.class.isAssignableFrom(type);
    }

    @Override
    public ZonedDateTime fromString(String dateTimeString) {
        if (StringUtils.isBlank(dateTimeString)) {
            return null;
        }

        try {            
            ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeString, DateTimeConverter.FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid datetime format in " + dateTimeString);
        }
    }

    public String toString(ZonedDateTime dateTime) {
        return dateTime.format(DateTimeConverter.FORMAT);
    }
}