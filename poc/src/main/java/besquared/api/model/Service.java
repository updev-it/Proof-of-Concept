package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Service
 */
public class Service {

    @XStreamAsAttribute
    public String id;

    @XStreamAsAttribute
    public String log_type;

    public String power_mode;
}