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

    public void setValue (Object val) throws IllegalArgumentException{
        if (this.getValueType().isAssignableFrom(val.getClass())){
            this.value = val;
        }
        else{
            //@todo: remove and always throw IllegalArgumentException
            //type checking has to be done in propertypanel
            if (getValueType().equals(Number.class)){
                this.value = Double.parseDouble(val.toString());
            }
            else
                throw new IllegalArgumentException("Accepted value must be of type '" + getValueType().getName() + "' but was '" + val.getClass().getName() + "'");
        }
    }

}

