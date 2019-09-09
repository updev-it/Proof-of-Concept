package besquared.api.model;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Modules
 */
@XStreamAlias("modules")
public class Modules extends PlugwiseHACollection<Module> {
    
    @Override
    public void merge(Map<String, Module> modules) {

    }
}