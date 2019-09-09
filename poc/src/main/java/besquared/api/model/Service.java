package besquared.api.model;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Service
 */
@XStreamAlias("service")
public class Service {

    @XStreamAlias("service_id")
    private String serviceId;
    
    @XStreamAlias("service_type")
    private String serviceType;

    @XStreamAlias("logs")
    private Logs logs;

    public String getServiceId() {
        return this.serviceId;
    }

    public enum ServiceIdType {
        OPEN_THERM_POWER_MODE("open_therm_power_mode"), OVERRIDE_MODE("override_mode"),
        UNCORRECTED_TEMPERATURE("uncorrected_temperature"), TEMPERATURE_DIFFERENCE("temperature_difference"),
        TEMPERATURE_OFFSET("temperature_offset"), TEMPERATURE("temperature"), THERMOSTAT("thermostat"),
        MSP_STATUS_CODE("msp_status_code"), VALVE_POSITION("valve_position");

        private String serviceIdType;

        ServiceIdType(String serviceIdTypeString) {
            this.serviceIdType = serviceIdTypeString;
        }

        public String getServiceIdType() {
            return this.serviceIdType;
        }

        // Lookup table
        private static final Map<String, ServiceIdType> lookup = new HashMap<>();

        // Populate the lookup table on loading time
        static {
            for (ServiceIdType serviceIdType : ServiceIdType.values()) {
                lookup.put(serviceIdType.getServiceIdType(), serviceIdType);
            }
        }

        // This method can be used for reverse lookup purpose
        public static ServiceIdType get(String serviceIdTypeString) {
            return lookup.get(serviceIdTypeString);
        }
    }
}