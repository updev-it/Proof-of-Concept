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
public class LogEntry {

    @XStreamOmitField
    @XStreamAsAttribute
    private String id;

    private String type;
    private Double temperature;

    @XStreamAlias("period")    
    @XStreamConverter(LogEntryPeriodConverter.class)
    private LogEntryPeriod period;

    @XStreamAlias("thermo_meter")
    private Thermometer thermometer;

    @XStreamAlias("updated_date")
    private String updatedDate;   

    public LogEntry(String id) {
        this.id = id;
    }

    public LogEntry(String id, String type, Double temperature) {
        this.id = id;
        this.type = type;
        this.temperature = temperature;
    }  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Thermometer getThermometer() {
        return thermometer;
    }

    public void setThermometer(Thermometer thermometer) {
        this.thermometer = thermometer;
    }

    public LogEntryPeriod getLogEntryPeriod() {
        return this.period;
    }

    public void setLogEntryPeriod(LogEntryPeriod logEntryPeriod) {
        this.period = logEntryPeriod;
    }
}