package api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Location
 */
@XStreamAlias("location")
public class Location {
    
    @XStreamAsAttribute
    public String id;
    public String name;
    public Appliances appliances;
    public Logs logs;
}