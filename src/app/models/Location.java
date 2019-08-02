package app.models;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

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
    @XStreamConverter(AppliancesConverter.class)
    private ArrayList<String> applianceList = new ArrayList<String>();
        
    @XStreamAlias("actuator_functionalities")
    private ActuatorFunctionalities actuatorFunctionalities = new ActuatorFunctionalities();

    public Location(String id) {
        this.id = id;

        this.actuatorFunctionalities = new ActuatorFunctionalities();
    }

    public Location(String id, String name) {
        this.id = id;
        this.name = name;

        this.actuatorFunctionalities = new ActuatorFunctionalities();
    }

    public void addAppianceId(String applianceId) {
        this.applianceList.add(applianceId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addThermostatFunctionality(ThermostatFunctionality thermostatFunctionality) {
        this.actuatorFunctionalities.setThermostatFunctionality(thermostatFunctionality);
    }

    public void addRelayFunctionality(RelayFunctionality relayFunctionality) {
        this.actuatorFunctionalities.setRelayFunctionality(relayFunctionality);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getApplianceList() {
        return applianceList;
    }

    public void setApplianceList(ArrayList<String> applianceList) {
        this.applianceList = applianceList;
    }

    public ActuatorFunctionalities getActuatorFunctionalities() {
        return actuatorFunctionalities;
    }

    public void setActuatorFunctionalities(ActuatorFunctionalities actuatorFunctionalities) {
        this.actuatorFunctionalities = actuatorFunctionalities;
    }
}