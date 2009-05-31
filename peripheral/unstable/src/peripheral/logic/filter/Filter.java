package peripheral.logic.filter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.VarValue;

public abstract class Filter implements Serializable {

    protected String outputVarName;
    protected Map<String, FilterInput> filterInputs;
    protected SymbolAdapter adapter;
    protected ConstValue outputValue;

    /*public List<VarValue> getInputVar() {
    return inputValues;
    }*/

    /*public Object getInputValue (){
    return inputVar.getValue();
    }*/
    public Filter(SymbolAdapter adapter, String outputVarName) {
        this.adapter = adapter;
        this.outputVarName = outputVarName;

        filterInputs = new HashMap<String, FilterInput>();
    }

    public abstract void doFilter();

    //public abstract List<java.lang.Class> getAcceptedInputTypes ();
    //public abstract java.lang.Class getOutputType();

    /*public String getOutputVarName() {
    return outputVarName;
    }*/
    /*public void setOutputVarName(String val) {
    this.outputVarName = val;
    }*/
    public void putFilterInputValue(String name, VarValue value) throws IllegalArgumentException {
        FilterInput fi = filterInputs.get(name);

        if (fi == null) {
            throw new IllegalArgumentException("Filter " + this.getClass().getName() + " doesn't have a filter input named '" + name + "'");
        }

        for (Class allowedType : fi.getAllowedTypes()) {
            if (allowedType.isAssignableFrom(value.getValueType())) {
                fi.setValue(value);
                return;
            }
        }

        throw new IllegalArgumentException("Filter input '" + name + "' for filter " + this.getClass().getName() + " requires one of type '" + createAllowedTypesString(fi.getAllowedTypes()) + "' but was '" + value.getValueType().getName() + "'");
    }

    public FilterInput[] getFilterInputs() {
        return filterInputs.values().toArray(new FilterInput[0]);
    }

    private String createAllowedTypesString(Class[] allowedTypes) {
        StringBuffer sb = new StringBuffer();
        for (Class allowedType : allowedTypes) {
            sb.append(allowedType.getName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    protected Object getFilterInputValue(String filterInputName) {
        return filterInputs.get(filterInputName).getValue().getValue();
    }

    /*protected List<Class> getNumberTypes() {
    List<Class> acc = new ArrayList<Class>();

    acc.add(int.class);
    acc.add(long.class);
    acc.add(float.class);
    acc.add(double.class);

    return acc;
    }*/
}

