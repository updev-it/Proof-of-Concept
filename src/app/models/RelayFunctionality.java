package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * RelayFunctionality
 */
@XStreamAlias("relay_functionality")
public class RelayFunctionality extends ActuatorFunctionality {

    public RelayFunctionality(String id) {
        super(id);
    }
}