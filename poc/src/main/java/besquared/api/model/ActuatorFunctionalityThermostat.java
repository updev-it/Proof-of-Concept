package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ActuatorFunctionalityThermostat
 */
@XStreamAlias("thermostat_functionality")
public class ActuatorFunctionalityThermostat extends ActuatorFunctionality {

    private Double setpoint;

    public ActuatorFunctionalityThermostat(Double temperature) {
        this.setpoint = temperature;
    }
}