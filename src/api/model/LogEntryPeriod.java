package api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * LogEntryPeriod
 */
@XStreamAlias("period")
public class LogEntryPeriod {

    @XStreamAlias("start_date")
    @XStreamAsAttribute
    private String startDate;

    @XStreamAlias("end_date")
    @XStreamAsAttribute
    private String endDate;

    private String measurement;
}