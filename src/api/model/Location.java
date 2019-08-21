package api.model;

import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Location
 */
@XStreamAlias("location")
public class Location {

    @XStreamAsAttribute
    private String id;

    private String name;

    private Appliances appliances;

    private Logs logs;

    @XStreamAlias("actuator_functionalities")
    private ActuatorFunctionalities actuatorFunctionalities;

    public Optional<Double> getTemperature() {                
        return this.logs.getTemperature();
    }

    public Optional<Double> getThermostatTemperature() {                
        return this.logs.getThermostatTemperature();
    }
}