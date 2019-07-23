package app.models;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ActuatorFunctionality
 */
public abstract class ActuatorFunctionality {
    @XStreamAsAttribute
    private String id;

    private String testString;

    private String updated_date;

    public ActuatorFunctionality(String id) {
        this.id = id;
        this.testString = "test";
    }

    public void setUpdatedDate(String updatedDate) {
        this.updated_date = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}