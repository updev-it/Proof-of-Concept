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
public abstract class LogEntry {

    @XStreamOmitField
    @XStreamAsAttribute
    private String id;

    private String type;
    private String unit;
    
    @XStreamAlias("last_consecutive_log_date")
    private String lastConsecutiveLogDate;
    @XStreamAlias("updated_date")
    private String updatedDate;   

    @XStreamAlias("period")    
    @XStreamConverter(LogEntryPeriodConverter.class)
    private LogEntryPeriod period;
 
    public LogEntry(String id) {
        this.id = id;
    }

    public LogEntry(String id, String type) {
        this.id = id;
        this.type = type;        
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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LogEntryPeriod getLogEntryPeriod() {
        return this.period;
    }

    public void setLogEntryPeriod(LogEntryPeriod logEntryPeriod) {
        this.period = logEntryPeriod;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLastConsecutiveLogDate() {
        return lastConsecutiveLogDate;
    }

    public void setLastConsecutiveLogDate(String lastConsecutiveLogDate) {
        this.lastConsecutiveLogDate = lastConsecutiveLogDate;
    }

    public LogEntryPeriod getPeriod() {
        return period;
    }

    public void setPeriod(LogEntryPeriod period) {
        this.period = period;
    }
}