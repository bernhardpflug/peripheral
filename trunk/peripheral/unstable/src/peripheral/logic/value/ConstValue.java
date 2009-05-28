package peripheral.logic.value;

import peripheral.logic.symboladapter.SymbolAdapter;


public class ConstValue extends Value {

    private Object value;

    public ConstValue (SymbolAdapter adapter, String varName, Object value) {
        this(adapter, varName, value, null);
    }

    public ConstValue (SymbolAdapter adapter, String varName, Object value, Class valueType) {
        super(adapter, varName, valueType);

        this.value = value;
    }

    public Object getValue () {
        return value;
    }

    public void setValue (Object val) {
        this.value = val;
    }

}

