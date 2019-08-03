package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Measurement
 */
@XStreamAlias("measurement")
public class Measurement {

    private Double value;

    @XStreamAlias("log_date")
    private String logDate;

    public Measurement(Double value) {
        this.value = value;
    }

    public Measurement(String value) {
        this.value = Double.parseDouble(value);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }
}