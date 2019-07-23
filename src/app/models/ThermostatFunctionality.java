package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ThermostatFunctionality
 */
@XStreamAlias("thermostat_functionality")
public class ThermostatFunctionality extends ActuatorFunctionality {

    private String type;
    private String setpoint;
    private String lower_bound;
    private String upper_bound;
    private String resolution;

    public ThermostatFunctionality(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetpoint() {
        return setpoint;
    }

    public void setSetpoint(String setpoint) {
        this.setpoint = setpoint;
    }

    public String getLowerBound() {
        return lower_bound;
    }

    public void setLowerBound(String lower_bound) {
        this.lower_bound = lower_bound;
    }

    public String getUpperBound() {
        return upper_bound;
    }

    public void setUpperBound(String upper_bound) {
        this.upper_bound = upper_bound;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }    
}