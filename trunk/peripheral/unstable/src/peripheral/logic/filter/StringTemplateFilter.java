package peripheral.logic.filter;

import java.util.ArrayList; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.Value; 

public class StringTemplateFilter extends Filter {

    private Value template;

    public StringTemplateFilter (SymbolAdapter adapter) {
        super(adapter);
    }

    @Override
    public Object doFilter () {
        String template = this.template.getValue().toString();
        System.out.println("StringTemplateFilter, template=" + template);

        template = template.replaceAll("###VAL###", this.getInputValue().toString());

        new ConstValue(adapter, this.getOutputVarName(), template);

        System.out.println("StringTemplateFilter, templateAfterReplace=" + template);

        return template;
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

