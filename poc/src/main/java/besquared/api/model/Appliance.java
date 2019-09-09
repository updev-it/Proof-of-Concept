package besquared.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Appliance
 */
@XStreamAlias("appliance")
public class Appliance extends PlugwiseBaseModel implements PlugwiseComparableDate<Appliance> {

    private String name;
    private String description;
    private String type;
    private String location;

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

    public String getLocation() {
        return location;
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

    public Optional<Boolean> getRelayState() {
        return this.pointLogs.getRelayState();
    }

    public Optional<Boolean> getRelayLockState() {
        return this.actuatorFunctionalities.getRelayLockState();
    }

    @Override
    public int compareDateWith(Appliance hasModifiedDate) {
        return this.getModifiedDate().compareTo(hasModifiedDate.getModifiedDate());
    }

    @Override
    public boolean isNewerThan(Appliance hasModifiedDate) {
        return compareDateWith(hasModifiedDate) > 0;
    }

    @Override
    public boolean isOlderThan(Appliance hasModifiedDate) {
        return compareDateWith(hasModifiedDate) < 0;
    }
}