package besquared.api.model;

import java.time.ZonedDateTime;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * GatewayInfo
 */
@XStreamAlias("gateway_environment")
public class GatewayEnvironment extends PlugwiseBaseModel {
    private String city;
    private String country;
    private String currency;
    private String latitude;
    private String longitude;
}