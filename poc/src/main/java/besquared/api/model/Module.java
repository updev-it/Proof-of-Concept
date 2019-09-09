package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Module
 */
@XStreamAlias("module")
public class Module {

    @XStreamAsAttribute
    String id;

    String vendor_name;

    String vendor_model;

    String hardware_version;

    String firmware_version;

    String created_date;

    String modified_date;

    String deleted_date;

    Services services;
}