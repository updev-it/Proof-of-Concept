package api.model;

import java.util.Optional;

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

    public Optional<Double> getTemperature() {
        String temperature = this.period.getMeasurement().map(measurement -> measurement).orElse(null);

        try {            
            return Optional.of(Double.parseDouble(temperature));
        } catch (NullPointerException | NumberFormatException e) {
            return Optional.empty();
        }        
    }
}

