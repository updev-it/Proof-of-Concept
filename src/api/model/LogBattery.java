package api.model;

/**
 * LogBattery
 */
public class LogBattery implements LogType {

    private String type;

    private String unit;
    
    private LogEntryPeriod period;

    public String getType() {
        return this.type;
    }
}

