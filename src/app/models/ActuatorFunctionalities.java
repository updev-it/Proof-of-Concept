package app.models;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * ActuatorFunctionalities
 */
@XStreamAlias("actuator_functionalities")
public class ActuatorFunctionalities {
    @XStreamImplicit
    private ArrayList<ActuatorFunctionality> list = new ArrayList<ActuatorFunctionality>();
    
    public ActuatorFunctionalities() {
        ThermostatFunctionality tFunc = new ThermostatFunctionality("thermostat1_id");
        tFunc.setUpdatedDate("2019-05-08T12:48:17.751+02:00");
        tFunc.setType("thermostat");
        tFunc.setSetpoint("18");
        tFunc.setLowerBound("0");
        tFunc.setUpperBound("99.99");
        tFunc.setResolution("0.01");

        RelayFunctionality rFunc = new RelayFunctionality("relay1_id");
        rFunc.setUpdatedDate("");
        
        this.list.add(tFunc);
        this.list.add(rFunc);
    }
}