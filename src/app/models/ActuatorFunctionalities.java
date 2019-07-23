package app.models;

import com.thoughtworks.xstream.annotations.XStreamAliasType;

/**
 * ActuatorFunctionalities
 */
@XStreamAliasType("actuator_functionalities")
public class ActuatorFunctionalities {
    private ThermostatFunctionality thermostatFunctionality;
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