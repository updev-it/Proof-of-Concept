package app.models;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;

import app.models.converters.AppliancesConverter;

/**
 * Location
 */
@XStreamAlias("location")
public class Location {
    @XStreamAsAttribute
    private String id;

    private String name;

    @XStreamAlias("appliances")
    private ApplianceList applianceList = new ApplianceList();
    // private ArrayList<Appliance> applianceList = new ArrayList<Appliance>();
    

    @XStreamAlias("actuator_functionalities")
    private ActuatorFunctionalities actuatorFunctionalities;

    public Location(String id, String name) {
        this.id = id;
        this.name = name;

        this.actuatorFunctionalities = new ActuatorFunctionalities();
    }

    public void addAppianceId(String applianceId) {
        this.applianceList.add(applianceId);
    }

    // public void addAppiance(Appliance appliance) {
    //     this.applianceList.add(appliance);
    // }
}