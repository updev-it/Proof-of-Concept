package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ActuatorFunctionalityRelay
 */
@XStreamAlias("relay_functionality")
public class ActuatorFunctionalityRelay extends ActuatorFunctionality {

    private String state;
    private Boolean lock;

    public ActuatorFunctionalityRelay(String state) {
        this.state = state;
    }

    public ActuatorFunctionalityRelay(String state, Boolean lock) {
        this.state = state;
        this.lock = lock;
    }
}