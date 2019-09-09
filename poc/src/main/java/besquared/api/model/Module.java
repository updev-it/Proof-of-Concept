package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Module
 */
@XStreamAlias("module")
public class Module {

    @XStreamAlias("id")
    private String id;

    @XStreamAlias("vendor_name")
    private String vendorName;

    @XStreamAlias("vendor_model")
    private String vendorModel;

    @XStreamAlias("hardware_version")
    private String hardwareVersion;

    @XStreamAlias("firmware_version")
    private String firmwareVersion;

    @XStreamAlias("created_date")
    private String createdDate;

    @XStreamAlias("modified_date")
    private String modifiedDate;

    @XStreamAlias("deleted_date")
    private String deletedDate;

    @XStreamAlias("services")
    private Services services;

    public String getId() {
        return this.id;
    }
}