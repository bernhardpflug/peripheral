package peripheral.logic.filter;

import java.io.Serializable;
import java.util.List;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.VarValue;

public abstract class Filter implements Serializable {

    private String outputVarName;
    private VarValue inputVar;
    protected SymbolAdapter adapter;

    public VarValue getInputVar() {
        return inputVar;
    }

    public void setInputVar(VarValue inputVar) {
        this.inputVar = inputVar;
    }

    public Object getInputValue (){
        return inputVar.getValue();
    }

    public Filter (SymbolAdapter adapter) {
        this.adapter = adapter;
    }

    public Object doFilter () {
        return null;
    }

    public abstract List<java.lang.Class> getAcceptedInputTypes ();

    public abstract java.lang.Class getOutputType ();

    public String getOutputVarName () {
        return outputVarName;
    }

    public void setOutputVarName (String val) {
        this.outputVarName = val;
    }

}

