package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import app.models.converters.LogEntryPeriodConverter;

/**
 * LogEntry
 */
@XStreamAlias("point_log")
public  class LogEntryTemperature extends LogEntry {
 
    public LogEntryTemperature(String id) {
        super(id, "temperature");
    }

}