package app.models;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ActuatorFunctionality
 */
public abstract class ActuatorFunctionality {
    @XStreamAsAttribute
    private String id;

    private String updated_date;

    public ActuatorFunctionality(String id) {
        this.id = id;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updated_date = updatedDate;
    }
}