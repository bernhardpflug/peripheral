package peripheral.logic.filter;

import java.util.ArrayList; 
import peripheral.logic.value.Value; 

public class StringTemplateFilter extends Filter {

    private Value template;

    public StringTemplateFilter () {
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

    public Value getTemplate () {
        return template;
    }

    public void setTemplate (Value val) {
        this.template = val;
    }

}

