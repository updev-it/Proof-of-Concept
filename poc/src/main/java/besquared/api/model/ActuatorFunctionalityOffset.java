package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ActuatorFunctionalityOffset
 */
@XStreamAlias("offset_functionality")
public class ActuatorFunctionalityOffset implements ActuatorFunctionalityType {

    @XStreamAsAttribute
    private String id;

    private String type;

    private String offset;

    @XStreamAlias("updated_date")
    private String updatedDate;

    public String getType() {
        return this.type;
    }
}

