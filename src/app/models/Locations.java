package app.models;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Locations
 */
@XStreamAlias("locations")
public class Locations {
    @XStreamImplicit
    private ArrayList<Location> locationList = new ArrayList<Location>();

    public void addLocation(Location location) {
        this.locationList.add(location);
    }
}