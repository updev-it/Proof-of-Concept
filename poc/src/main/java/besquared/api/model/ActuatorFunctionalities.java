package besquared.api.model;

import java.util.Map;
import java.util.Optional;

/**
 * ActuatorFunctionalities
 */
public class ActuatorFunctionalities extends PlugwiseHACollection<ActuatorFunctionality> {

    private final String THERMOSTAT_FUNCTIONALITY = "thermostat";
    private final String RELAY_FUNCTIONALITY = "relay";

    public Optional<Boolean> getRelayLockState() {
        return Optional.ofNullable(this.getFunctionalityRelay().map(functionalityEntry -> {
            String state = functionalityEntry.getRelayLockState().orElse(null);
            return state != null ? Boolean.parseBoolean(state) : null;
        }).orElse(null));
    }

    public Optional<ActuatorFunctionality> getFunctionalityThermostat() {
        return Optional.ofNullable(this.get(THERMOSTAT_FUNCTIONALITY));
    }

    public Optional<ActuatorFunctionality> getFunctionalityRelay() {
        return Optional.ofNullable(this.get(RELAY_FUNCTIONALITY));
    }

    @Override
    public void merge(Map<String, ActuatorFunctionality> actuatorFunctionalities) {
        if (actuatorFunctionalities != null) {
            for (ActuatorFunctionality actuatorFunctionality : actuatorFunctionalities.values()) {
                String type = actuatorFunctionality.getType();
                ActuatorFunctionality originalActuatorFunctionality = this.get(type);

                if (originalActuatorFunctionality == null
                        || originalActuatorFunctionality.isOlderThan(actuatorFunctionality)) {
                    this.put(type, actuatorFunctionality);
                }
            }
        }
    }
}