package besquared.api.model;

/**
 * ServiceType
 */
public class ServiceType implements IServiceType {

    private String id;

    private String log_type;

    public String getType() {
        return this.log_type;
    }    
}