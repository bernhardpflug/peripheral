package peripheral.logic.value;

import java.util.List;


public class UserInput {

    private String name;

    private String description;

    private Value value;

    private List<Class> allowedValueTypes;

    public UserInput (String name, String description, Value value) {
    }

    public List<Class> getAllowedValueTypes () {
        return allowedValueTypes;
    }

    public void setAllowedValueTypes (List<Class> val) {
        this.allowedValueTypes = val;
    }

    public String getDescription () {
        return description;
    }

    public String getName () {
        return name;
    }

    public Value getValue () {
        return value;
    }

    public void setValue (Value val) {
        this.value = val;
    }

}
