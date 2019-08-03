package app.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import app.models.converters.AppliancesConverter;
import app.models.converters.LogEntriesConverter;

/**
 * Location
 */
public class Location {
    @XStreamAsAttribute
    private String id;

    private String name;

    @XStreamAlias("appliances")
    @XStreamConverter(AppliancesConverter.class)
    private ArrayList<String> applianceList = new ArrayList<String>();
        
    @XStreamAlias("actuator_functionalities")
    private ActuatorFunctionalities actuatorFunctionalities = new ActuatorFunctionalities();

    @XStreamAlias("logs")
    @XStreamConverter(LogEntriesConverter.class)
    private Map<String, LogEntry> logEntries = new HashMap<String, LogEntry>();

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

    public void addLogEntry(LogEntry entry) {
        this.logEntries.put(entry.getId(), entry);
    }

    public LogEntry getLogEntry(String id) {
        return this.logEntries.get(id);
    }

    public Map<String, LogEntry> getLogEntries() {
        return this.logEntries;
    }

    public void setLogEntries(Map<String, LogEntry> logEntries) {
        this.logEntries = logEntries;
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