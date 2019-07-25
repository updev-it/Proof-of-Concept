package app.models;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamConverters;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
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

    // @XStreamAlias("appliances")
    // private ApplianceList applianceList = new ApplianceList();

    @XStreamAlias("appliances")
    @XStreamConverter(AppliancesConverter.class)
    private ArrayList<String> applianceList = new ArrayList<String>();
        
    @XStreamAlias("actuator_functionalities")
    private ActuatorFunctionalities actuatorFunctionalities = new ActuatorFunctionalities();

    public Location(String id, String name) {
        this.id = id;
        this.name = name;

        ThermostatFunctionality tfOne = new ThermostatFunctionality("thermostatid_1");
        RelayFunctionality rfOne = new RelayFunctionality("relayid_1");

        tfOne.setUpdatedDate("2019-05-08T12:48:17.751+02:00");
        tfOne.setLowerBound("0");
        tfOne.setUpperBound("99.99");
        tfOne.setResolution("0.01");
        tfOne.setSetpoint("18.0");
        tfOne.setType("thermostat");
        
        rfOne.setUpdatedDate("");

        this.actuatorFunctionalities = new ActuatorFunctionalities();
        this.actuatorFunctionalities.setThermostatFunctionality(tfOne);
        this.actuatorFunctionalities.setRelayFunctionality(rfOne);
    }

    public void addAppianceId(String applianceId) {
        this.applianceList.add(applianceId);
    }

    // public void addAppiance(Appliance appliance) {
    //     this.applianceList.add(appliance);
    // }
}