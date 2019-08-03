package app.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Logs
 */
public class Logs {

    private Map<String, LogEntry> logEntries = new HashMap<String, LogEntry>();

    public void addLogEntry(LogEntry entry) {
        this.logEntries.put(entry.getId(), entry);
    }

    public LogEntry getLogEntry(String id) {
        return this.logEntries.get(id);
    }

    public Map<String, LogEntry> getLogEntries() {
        return this.logEntries;
    }
}