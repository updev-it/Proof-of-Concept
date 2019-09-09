package besquared.api.model;

import java.util.Map;

/**
 * Locations
 */
public class Locations extends PlugwiseHACollection<Location> {

    @Override
    public void merge(Map<String, Location> locations) {
        if (locations != null) {
            for (Location updatedLocation : locations.values()) {
                String id = updatedLocation.getId();                
                Location originalLocation = this.get(id);                

                if (originalLocation != null && originalLocation.isOlderThan(updatedLocation)) {
                    Logs updatedPointLogs = updatedLocation.getPointLogs();                    
                    ActuatorFunctionalities updatedActuatorFunctionalities = updatedLocation.getActuatorFunctionalities();

                    if (updatedPointLogs != null) {
                        updatedPointLogs.merge(originalLocation.getPointLogs());                        
                    }

                    if (updatedActuatorFunctionalities != null) {
                        updatedActuatorFunctionalities.merge(originalLocation.getActuatorFunctionalities());
                    }

                    this.put(id, updatedLocation);
                }                
            }
        }
    }
}