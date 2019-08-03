package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ActuatorFunctionality
 */
public abstract class ActuatorFunctionality {
    @XStreamAsAttribute
    private String id;

    @XStreamAlias("updated_date")
    private String updatedDate;

    public ActuatorFunctionality(String id) {
        this.id = id;        
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}