package app.models;

import java.util.HashMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Locations
 */
@XStreamAlias("locations")
public class Locations extends HashMap<String, Location> {

    private static final long serialVersionUID = 1L;

    // @XStreamAlias("location")
    // private Map<String, Location> locationList = new HashMap<String, Location>();

    // public void addLocation(Location location) {
    //     this.locationList.put(location.getId(), location);
    // }

    // public Map<String, Location> getLocations() {
    //     return this.locationList;
    // }
}