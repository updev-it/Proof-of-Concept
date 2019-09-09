package besquared.api.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Logs
 */
public class Logs extends PlugwiseHACollection<Log> {

    private final String THERMOSTAT = "thermostat";
    private final String RELAY = "relay";

    public Optional<Double> getThermostatTemperature() {
        return this.getLogThermostat().map(logEntry -> logEntry.getMeasurementAsDouble()).orElse(Optional.empty());
    }

    public Optional<Boolean> getRelayState() {
        return Optional.ofNullable(this.getLogRelay().map(logEntry -> {
            String state = logEntry.getMeasurement().orElse("");
            switch (state.toLowerCase()) {
                case "on": {
                    return true;              
                }
                case "off": {
                    return false;                 
                }
                default: {
                    return null;
                }
            }
        }).orElse(null));
    }

    public Optional<Log> getLogThermostat() {
        return Optional.ofNullable(this.get(THERMOSTAT));
    }

    public Optional<Log> getLogRelay() {
        return Optional.ofNullable(this.get(RELAY));
    }

    @Override
    public void merge(Map<String, Log> logs) {
        if (logs != null) {
            for (Log log : logs.values()) {
                String type = log.getType();
                Log updatedLog = this.get(type);

                if (updatedLog == null || updatedLog.isOlderThan(log)) {
                    this.put(type, log);
                }               
            }
        }
    }
}