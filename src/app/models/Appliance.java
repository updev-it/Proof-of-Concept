package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Appliance
 */
@XStreamAlias("appliance")
public class Appliance {
    @XStreamAsAttribute
    private String id;

    private String name;

    public Appliance(String id) {
        this.id = id;
    }
}