package besquared.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * LogEntry
 */
@XStreamAlias("point_log")
public class LogEntry implements LogType {

    private String type;

    public String getType() {
        return this.type;
    }
}

