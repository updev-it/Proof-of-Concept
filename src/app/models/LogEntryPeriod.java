package app.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * LogEntryPeriod
 */
@XStreamAlias("period")
public class LogEntryPeriod {

    @XStreamAsAttribute
    @XStreamAlias("start_date")
    private String startDate;

    @XStreamAsAttribute
    @XStreamAlias("end_date")
    private String endDate;

    private Measurement measurement;
    // private Map<String, Measurement> measurementList = new HashMap<String, Measurement>();
    
    public LogEntryPeriod(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    // public Map<String, Measurement> getMeasurements() {
    //     return this.measurementList;
    // }

    // public void setMeasurement(Map<String, Measurement> measurements) {
    //     this.measurementList = measurements;
    // }

    // public void addMeasurement(Measurement measurement) {
    //     this.measurementList.put(measurement.getLogDate(), measurement);
    // }
}