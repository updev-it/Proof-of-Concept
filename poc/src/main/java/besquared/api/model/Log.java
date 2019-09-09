package besquared.api.model;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Log
 */
@XStreamAlias("point_log")
public class Log extends PlugwiseBaseModel implements PlugwiseComparableDate<Log> {

    private String type;

    private String unit;

    private String measurement;

    @XStreamAlias("measurement_date")
    private ZonedDateTime measurementDate;

    @XStreamAlias("updated_date")
    private ZonedDateTime updatedDate;

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public Optional<String> getMeasurement() {
        return Optional.ofNullable(measurement);
    }

    public Optional<Double> getMeasurementAsDouble() {
        try {
            if (measurement != null) {
                return Optional.of(Double.parseDouble(measurement));
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public ZonedDateTime getMeasurementDate() {
        return measurementDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public int compareDateWith(Log hasMeasurementDate) {
        return this.measurementDate.compareTo(hasMeasurementDate.getMeasurementDate());
    }

    public boolean isOlderThan(Log hasMeasurementDate) {
        return this.compareDateWith(hasMeasurementDate) < 0;
    }

    public boolean isNewerThan(Log hasMeasurementDate) {
        return this.compareDateWith(hasMeasurementDate) > 0;
    }
}