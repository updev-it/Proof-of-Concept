package app.models;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Thermometer
 */
public class Thermometer {

    @XStreamAsAttribute
    private String id;

    public Thermometer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}