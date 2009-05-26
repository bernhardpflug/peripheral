package peripheral.logic.value;

import peripheral.logic.symboladapter.SymbolAdapter;


public class ConstValue extends Value {

    private Object value;

    public ConstValue (SymbolAdapter adapter, String varName, Object value) {
        super(adapter, varName);

        this.value = value;
    }

    public Object getValue () {
        return value;
    }

    public void setValue (Object val) {
        this.value = val;
    }

}

