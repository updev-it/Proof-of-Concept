package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ActuatorFunctionalityRelay
 */
@XStreamAlias("relay_functionality")
public class ActuatorFunctionalityRelay implements ActuatorFunctionalityType {

    @XStreamAsAttribute
    private String id;

    @XStreamAlias("updated_date")
    
    private String updatedDate;

    public String getType() {
        return "relay";
    }
}

