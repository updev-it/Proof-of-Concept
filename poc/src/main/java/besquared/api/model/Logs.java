package besquared.api.model;

import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Logs
 */
@XStreamAlias("logs")
public class Logs extends CustomCollection<LogType> {

    private static String LOG_TEMPERATURE = "temperature";
    private static String LOG_THERMOSTAT = "thermostat";

    public Optional<LogTemperature> getLogTemperature() {
        return Optional.ofNullable((LogTemperature) this.get(Logs.LOG_TEMPERATURE));
    }

    public Optional<LogTemperature> getLogThermostat() {
        return Optional.ofNullable((LogTemperature) this.get(Logs.LOG_THERMOSTAT));
    }

    public Optional<Double> getTemperature() {
        return this.getLogTemperature().map(logTemperature -> logTemperature.getTemperature()).orElse(Optional.empty());
    }

    public Optional<Double> getThermostatTemperature() {
        return this.getLogThermostat().map(logTemperature -> logTemperature.getTemperature()).orElse(Optional.empty());
    }
}