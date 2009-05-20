package peripheral.logic.filter;

import java.util.ArrayList; 

public class Filter {

    private String outputVarName;

    public Filter () {
    }

    public Object doFilter (Object val) {
        return null;
    }

    public ArrayList<java.lang.Class> getAcceptedInputTypes () {
        return null;
    }

    public java.lang.Class getOutputType () {
        return null;
    }

    public String getOutputVarName () {
        return outputVarName;
    }

    public void setOutputVarName (String val) {
        this.outputVarName = val;
    }

}

