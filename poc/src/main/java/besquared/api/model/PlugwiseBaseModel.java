package besquared.api.model;

import java.time.ZonedDateTime;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * PlugwiseBaseModel
 */
public abstract class PlugwiseBaseModel {

    private String id;
    
    @XStreamAlias("created_date")
    private ZonedDateTime createdDate;

    @XStreamAlias("modified_date")
    private ZonedDateTime modifiedDate;

    @XStreamAlias("updated_date")
    private ZonedDateTime updateDate;

    @XStreamAlias("deleted_date")
    private ZonedDateTime deletedDate;

    public String getId() {
        return id;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updateDate;
    }

    public ZonedDateTime getDeletedDate() {
        return deletedDate;
    }
}