package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * DomainObjects
 */
@XStreamAlias("domain_objects")
public class DomainObjects {

    @XStreamAlias("gateway")
    private GatewayInfo gatewayInfo;

    @XStreamImplicit(itemFieldName = "appliance", keyFieldName = "id")
    private Appliances appliances = new Appliances();

    @XStreamImplicit(itemFieldName = "location", keyFieldName = "id")
    private Locations locations = new Locations();

    public GatewayInfo getGatewayInfo() {
        return gatewayInfo;
    }

    public Appliances getAppliances() {
        return appliances;
    }

    public Locations getLocations() {
        return locations;
    }

    public Appliances mergeAppliances(Appliances appliances) {
        if (appliances != null) {
            this.appliances.merge(appliances);
        }

        return this.appliances;        
    }

    public Locations mergeLocations(Locations locations) {
        if (locations != null) {
            this.locations.merge(locations);
        }

        return this.locations;   
    }
}