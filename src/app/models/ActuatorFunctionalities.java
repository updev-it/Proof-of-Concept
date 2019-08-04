package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ActuatorFunctionalities
 */
public class ActuatorFunctionalities {
    
    @XStreamAlias("thermostat_functionality")
    private ThermostatFunctionality thermostatFunctionality;

    @XStreamAlias("relay_functionality")
    private RelayFunctionality relayFunctionality;

    @XStreamAlias("offset_functionality")
    private OffsetFunctionality offsetFunctionality;

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

    public OffsetFunctionality getOffsetFunctionality() {
        return offsetFunctionality;
    }

    public void setOffsetFunctionality(OffsetFunctionality offsetFunctionality) {
        this.offsetFunctionality = offsetFunctionality;
    }
}