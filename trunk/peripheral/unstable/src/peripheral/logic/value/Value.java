package peripheral.logic.value;

import java.io.Serializable;
import peripheral.logic.symboladapter.SymbolAdapter;


public abstract class Value implements Serializable {

    private Class valueType;

    protected SymbolAdapter adapter;

    protected String varName;

    public Value (){}

    public Value (SymbolAdapter adapter, String name) {
        this.adapter = adapter;
        this.varName = name;

        this.adapter.getVarpool().put(name, this);
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

