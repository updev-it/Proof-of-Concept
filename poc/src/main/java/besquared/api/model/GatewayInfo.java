package besquared.api.model;

import java.time.ZonedDateTime;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * GatewayInfo
 */
@XStreamAlias("gateway")
public class GatewayInfo extends PlugwiseBaseModel {

    private String name;
    private String description;
    private String hostname;
    private String timezone;
    private ZonedDateTime time;

    @XStreamAlias("gateway_environment")
    private GatewayEnvironment gatewayEnvironment;

    @XStreamAlias("vendor_name")
    private String vendorName;

    @XStreamAlias("vendor_model")
    private String vendorModel;

    @XStreamAlias("hardware_version")
    private String hardwareVersion;

    @XStreamAlias("firmware_version")
    private String firmwareVersion;

    @XStreamAlias("mac_address")
    private String macAddress;

    @XStreamAlias("lan_ip")
    private String lanIp;

    @XStreamAlias("wifi_ip")
    private String wifiIp;

    @XStreamAlias("last_reset_date")
    private ZonedDateTime lastResetDate;

    @XStreamAlias("last_boot_date")
    private ZonedDateTime lastBootDate;

    public ZonedDateTime getTime() {
        return time;
    }
}