package api.model;

/**
 * LogTemperature
 */
public class LogTemperature implements LogType {

    private String type;
    private String unit;
    private LogEntryPeriod period;

    public String getType() {
        return this.type;
    }
}

