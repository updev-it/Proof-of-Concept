package api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Appliance
 */
@XStreamAlias("appliance")
public class Appliance {

    @XStreamAsAttribute
    private String id;

    private String name;

    private String description;

    private String type;

    private Logs logs;

    @XStreamAlias("actuator_functionalities")
    private ActuatorFunctionalities actuatorFunctionalities;

    @XStreamAlias("created_date")
    private String createdDate;

    @XStreamAlias("modified_date")
    private String modifiedDate;

    @XStreamAlias("deleted_date")
    private String deletedDate;
}