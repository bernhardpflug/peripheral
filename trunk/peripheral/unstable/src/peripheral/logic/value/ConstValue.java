package peripheral.logic.value;

import peripheral.logic.symboladapter.SymbolAdapter;


public class ConstValue extends Value {

    private Object value;

    /*public ConstValue (SymbolAdapter adapter, String varName, Object value) {
        this(adapter, varName, value, null);
    }*/

    public ConstValue (SymbolAdapter adapter, String varName, Object value, Class valueType) {
        super(adapter, varName, valueType);

        this.value = value;
    }

    public Object getValue () {
        return value;
    }

    public void setValue (Object val) throws IllegalArgumentException{
        if (this.getValueType().isAssignableFrom(val.getClass())){
            this.value = val;
        }
        else{
            throw new IllegalArgumentException("Accepted value must be of type '" + getValueType().getName() + "' but was '" + val.getClass().getName() + "'");
        }
    }

    public boolean isValid() {

        //check whether set value is of (sub)class of valuetype
        return this.getValueType().isAssignableFrom(value.getClass());
    }

}

