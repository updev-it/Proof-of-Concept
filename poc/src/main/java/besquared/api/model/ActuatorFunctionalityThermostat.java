package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ActuatorFunctionalityThermostat
 */
@XStreamAlias("thermostat_functionality")
public class ActuatorFunctionalityThermostat implements ActuatorFunctionalityType {

    @XStreamAsAttribute
    private String id;
    
    private String type;

    private String setpoint;

    private String lower_bound;

    private String upper_bound;

    private String resolution;

    @XStreamAlias("updated_date")
    private String updatedDate;

    public String getType() {
        return this.type;
    }
}

