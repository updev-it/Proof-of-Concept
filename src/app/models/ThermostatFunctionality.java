package app.models;

/**
 * ThermostatFunctionality
 */
public class ThermostatFunctionality extends ActuatorFunctionality {

    private String type;
    private Double setpoint;
    private Double lower_bound;
    private Double upper_bound;
    private Double resolution;

    public ThermostatFunctionality(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSetpoint() {
        return setpoint;
    }

    public void setSetpoint(Double setpoint) {
        this.setpoint = setpoint;
    }

    public Double getLowerBound() {
        return lower_bound;
    }

    public void setLowerBound(Double lower_bound) {
        this.lower_bound = lower_bound;
    }

    public Double getUpperBound() {
        return upper_bound;
    }

    public void setUpperBound(Double upper_bound) {
        this.upper_bound = upper_bound;
    }

    public Double getResolution() {
        return resolution;
    }

    public void setResolution(Double resolution) {
        this.resolution = resolution;
    }    
}