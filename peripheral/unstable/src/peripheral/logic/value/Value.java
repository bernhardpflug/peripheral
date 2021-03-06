package peripheral.logic.value;

import java.io.Serializable;
import peripheral.logic.Logging;
import peripheral.logic.symboladapter.SymbolAdapter;

public abstract class Value implements Serializable {

    private Class valueType;
    protected SymbolAdapter adapter;
    protected String varName;

    /**
     * used for subclass VarValue. VarValue need not be added to
     * varpool, because it references a variable. otherwise a cycle
     * reference would occur.
     * 
     * @param adapter
     */
    protected Value(SymbolAdapter adapter) {
        this.adapter = adapter;
    }

    public Value(SymbolAdapter adapter, String name) {
        this(adapter);
        this.varName = name;

        if (this instanceof ConstValue || this instanceof SensorValue) {
            if (adapter.getVarpool().containsKey(varName)) {
                Logging.getLogger().warning("Varpool already contains a value named '" + varName + "'");
            }
            else{
                this.adapter.getVarpool().put(name, this);
            }
        }
        else{
            this.adapter.getVarpool().put(name, this);
        }
    }

    public Value(SymbolAdapter adapter, String name, Class valueType) {
        this(adapter, name);
        this.valueType = valueType;
    }

    public abstract Object getValue();

    public Class getValueType() {
        return valueType;
    }

    /*public void setValueType (Class val) {
    this.valueType = val;
    }*/
    public String getVarName() {
        return varName;
    }

    public SymbolAdapter getAdapter() {
        return adapter;
    }

    /**
     * @return whether the current Value of the Value object is valid or not
     */
    public abstract boolean isValid();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Value)) {
            return false;
        }

        return ((Value) obj).getVarName().equals(this.varName);
    }
}

