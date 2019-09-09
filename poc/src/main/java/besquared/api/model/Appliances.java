package besquared.api.model;

import java.util.Map;

/**
 * Appliances
 */
public class Appliances extends PlugwiseHACollection<Appliance> {
    
    @Override
    public void merge(Map<String, Appliance> appliances) {
        if (appliances != null) {
            for (Appliance updatedAppliance : appliances.values()) {
                String id = updatedAppliance.getId();
                Appliance originalAppliance = this.get(id);
    
                if (originalAppliance != null && originalAppliance.isOlderThan(updatedAppliance)) {                    
                    Logs updatedPointLogs = updatedAppliance.getPointLogs();                    
                    ActuatorFunctionalities updatedActuatorFunctionalities = updatedAppliance.getActuatorFunctionalities();
    
                    if (updatedPointLogs != null) {
                        updatedPointLogs.merge(originalAppliance.getPointLogs());
                    }

                    if (updatedActuatorFunctionalities != null) {
                        updatedActuatorFunctionalities.merge(originalAppliance.getActuatorFunctionalities());
                    }

                    this.put(id, updatedAppliance);
                }
            }
        }
    }
}