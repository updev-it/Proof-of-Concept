package app.models;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ApplianceList
 */
@XStreamAliasType("appliance")
public class ApplianceList extends ArrayList<String> {

    private static final long serialVersionUID = 1L;

    public ApplianceList() {
        super();
    }
}