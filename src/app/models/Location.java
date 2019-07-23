package app.models;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Location
 */
@XStreamAlias("location")
public class Location {
    @XStreamAsAttribute
    private String id;

    private String name;

    @XStreamAlias("appliances")
    private ArrayList<Appliance> applianceList = new ArrayList<Appliance>();

    @XStreamAlias("actuator_functionalities")
    private ActuatorFunctionalities actuatorFunctionalities;

    public Location(String id, String name) {
        this.id = id;
        this.name = name;

        this.actuatorFunctionalities = new ActuatorFunctionalities();
    }

    public void addAppiance(Appliance appliance) {
        this.applianceList.add(appliance);
    }
}