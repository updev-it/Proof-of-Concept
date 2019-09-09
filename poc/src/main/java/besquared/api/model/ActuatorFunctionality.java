package besquared.api.model;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ActuatorFunctionality
 */
@XStreamAlias("actuator_functionality")
public class ActuatorFunctionality extends PlugwiseBaseModel implements PlugwiseComparableDate<ActuatorFunctionality> {

    private String type;
    private String setpoint;
    private String resolution;
    private String lock;

    @XStreamAlias("lower_bound")
    private String lowerBound;

    @XStreamAlias("upper_bound")
    private String upperBound;

    @XStreamAlias("updated_date")
    private ZonedDateTime updatedDate;

    public String getType() {
        return type;
    }

    public String getSetpoint() {
        return setpoint;
    }

    public String getResolution() {
        return resolution;
    }

    public String getLowerBound() {
        return lowerBound;
    }

    public String getUpperBound() {
        return upperBound;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public Optional<String> getRelayLockState() {
        return Optional.ofNullable(lock);
    }

    @Override
    public int compareDateWith(ActuatorFunctionality hasUpdatedDate) {
        return this.getUpdatedDate().compareTo(hasUpdatedDate.getUpdatedDate());
    }

    @Override
    public boolean isOlderThan(ActuatorFunctionality hasUpdatedDate) {
        return this.compareDateWith(hasUpdatedDate) < 0;
    }

    public boolean isNewerThan(ActuatorFunctionality hasUpdatedDate) {
        return this.compareDateWith(hasUpdatedDate) > 0;
    }
}