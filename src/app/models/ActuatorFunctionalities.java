package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;

/**
 * ActuatorFunctionalities
 */
@XStreamAliasType("actuator_functionalities")
public class ActuatorFunctionalities {
    @XStreamAlias("thermostat_functionality")
    private ThermostatFunctionality thermostatFunctionality;
    @XStreamAlias("relay_functionality")
    private RelayFunctionality relayFunctionality;

    public ThermostatFunctionality getThermostatFunctionality() {
        return thermostatFunctionality;
    }

    public void setThermostatFunctionality(ThermostatFunctionality thermostatFunctionality) {
        this.thermostatFunctionality = thermostatFunctionality;
    }

    public RelayFunctionality getRelayFunctionality() {
        return relayFunctionality;
    }

    public void setRelayFunctionality(RelayFunctionality relayFunctionality) {
        this.relayFunctionality = relayFunctionality;
    }
}