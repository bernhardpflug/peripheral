package peripheral.logic.value;

import peripheral.logic.symboladapter.SymbolAdapter;


public class VarValue extends Value {

    public VarValue (SymbolAdapter adapter, String varName) throws IllegalArgumentException {
        super(adapter);
        this.varName = varName;

        if (getReferencedValue() == null){
            throw new IllegalArgumentException("Cannot reference value '" + varName + "': no such value in varpool.");
        }
    }

    public Object getValue () {
        return getReferencedValue().getValue();
    }

    @Override
    public Class getValueType() {
        return getReferencedValue().getValueType();
    }

    protected Value getReferencedValue (){
        return adapter.getVarpool().get(varName);
    }
}

