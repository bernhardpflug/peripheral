package peripheral.logic.symboladapter;


public class AdapterTemplateFactory {

    private java.util.List<SymbolAdapter> templates;

    private AdapterTemplateFactory () {
    }

    public static AdapterTemplateFactory getInstance () {
        return null;
    }

    public java.util.List<SymbolAdapter> getTemplates () {
        return templates;
    }

    public SymbolAdapter createInstanceFor (SymbolAdapter template) {
        return null;
    }

}

