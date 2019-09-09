package besquared.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Location
 */
@XStreamAlias("location")
public class Location extends PlugwiseBaseModel implements PlugwiseComparableDate<Location> {

    private String name;
    private String description;
    private String type;
    private String preset;

    @XStreamImplicit(itemFieldName = "appliance")
    private List<String> locationAppliances = new ArrayList<String>();

    @XStreamImplicit(itemFieldName = "point_log", keyFieldName = "type")
    private Logs pointLogs = new Logs();

    @XStreamImplicit(itemFieldName = "actuator_functionality", keyFieldName = "type")
    private ActuatorFunctionalities actuatorFunctionalities = new ActuatorFunctionalities();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getPreset() {
        return preset;
    }

    public List<String> getLocationAppliances() {
        return locationAppliances;
    }

    public Logs getPointLogs() {
        return pointLogs;
    }

    public ActuatorFunctionalities getActuatorFunctionalities() {
        return actuatorFunctionalities;
    }

    public Optional<Double> getSetpointTemperature() {
        return this.pointLogs.getThermostatTemperature();
    }

    public int compareDateWith(Location hasModifiedDate) {
        return this.getModifiedDate().compareTo(hasModifiedDate.getModifiedDate());
    }

    public boolean isOlderThan(Location hasModifiedDate) {
        return compareDateWith(hasModifiedDate) < 0;
    }

    public boolean isNewerThan(Location hasModifiedDate) {
        return this.compareDateWith(hasModifiedDate) > 0;
    }

    // public void setThermostat(Double temperature) throws PlugwiseHAException {
    //     this.actuatorFunctionalities.getThermostatFunctionality().ifPresent(thermostat -> {
    //         this.
    //     });
    //     if (this.actuatorFunctionalities != null) {
    //         ActuatorFunctionalityThermostat thermostat = (ActuatorFunctionalityThermostat) this.actuatorFunctionalities
    //                 .get("thermostat");

    //         if (thermostat != null) {
    //             this.controller.setThermostatTemperature(temperature, this, thermostat);
    //         }
    //     } else {
    //         throw new PlugwiseHAException(String.format("Location %s has no thermostat functionality: unable to change ThermostatTemperature", this.getName()));
    //     }
    // }
}