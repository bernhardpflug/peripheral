package peripheral.logic.value;


public abstract class Value {

    private Class valueType;

    private String varName;

    public Value (String name) {
    }

    public Object getValue () {
        return null;
    }

    public Class getValueType () {
        return valueType;
    }

    public void setValueType (Class val) {
        this.valueType = val;
    }

    public String getVarName () {
        return varName;
    }

}

