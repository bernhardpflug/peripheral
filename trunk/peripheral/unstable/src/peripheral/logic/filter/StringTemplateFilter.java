package peripheral.logic.filter;

import peripheral.logic.Logging;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

public class StringTemplateFilter extends Filter {

    //private Value template;
    public StringTemplateFilter(SymbolAdapter adapter, String outputVarName) {
        super(adapter, outputVarName);

        FilterInput fi = new FilterInput("inputValue", new Class[]{Number.class, String.class});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("template", new Class[]{String.class});
        filterInputs.put(fi.getName(), fi);

        outputValue = new ConstValue(adapter, outputVarName, "###VAL###", String.class);
    }

    @Override
    public void doFilter() {
        String template = (String) getFilterInputValue("template");
        Object inputValue = (Object) getFilterInputValue("inputValue");
        Logging.getLogger().finer("StringTemplateFilter, template=" + template);

        template = template.replaceAll("###VAL###", inputValue.toString());

        outputValue.setValue(template);

        Logging.getLogger().finer("StringTemplateFilter, templateAfterReplace=" + template);
    }

    /*public ArrayList<java.lang.Class> getAcceptedInputTypes() {
        return null;
    }

    public java.lang.Class getOutputType() {
        return null;
    }

    public static List<Class> getNeededInputValTypes() {
        List<Class> l = new ArrayList<Class>();

        l.add(String.class);

        return l;
    }*/

    /*public Value getTemplate () {
    return template;
    }*/

    /*public void setTemplate (Value val) {
    this.template = val;
    }*/
}

