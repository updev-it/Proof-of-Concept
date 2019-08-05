package app.models;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import app.models.converters.LocationsConverter;

/**
 * Locations
 */
@XStreamAlias("locations")
@XStreamConverter(LocationsConverter.class)
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