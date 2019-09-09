package besquared.api.model;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Services
 */
@XStreamAlias("Services")
public class Services extends PlugwiseHACollection<Service> {

    @Override
    public void merge(Map<String, Service> services) {
        // TODO Auto-generated method stub

    }

}