package peripheral.logic.symboladapter;

import java.util.ArrayList;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.SensorValue;
import peripheral.logic.value.UserInput;


public class AdapterTemplateFactory {

    private static AdapterTemplateFactory instance;

    private java.util.List<SymbolAdapter> templates;

    private AdapterTemplateFactory () {

        templates = new ArrayList<SymbolAdapter>();

        //TODO just for testing purposes; replace by real templates
        createDummyTemplates();
    }

    private void createDummyTemplates() {

        SymbolAdapter contSlider = new SymbolAdapter();
        contSlider.setName("continuousSlider");
        contSlider.setDescription("A slider based on \na sensorvalue that continously \nmaps the sensorvalue\n onto a selected area");
        contSlider.getNeededUserInput().add(new UserInput("ui1","what the hell", new SensorValue("SensorValue",null)));
        contSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue("LocationX",new Integer(0))));
        contSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue("LocationY",new Integer(0))));

        contSlider.getRequiredSteps().put(peripheral.designer.wizard.AddAnimationDialog.RULEPANEL, new Boolean(false));

        templates.add(contSlider);

        SymbolAdapter ruleSlider = new SymbolAdapter();
        ruleSlider.setName("ruleBasedSlider");
        ruleSlider.setDescription("A slider were rules \ncan be defined for mapping \nseveral sensorvalues onto\n the position of the picture");
        ruleSlider.getNeededUserInput().add(new UserInput("ui1","what the hell", new ConstValue("EnableSmoothing",new Boolean(true))));
        ruleSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue("LocationX",new Integer(0))));
        ruleSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue("LocationY",new Integer(0))));

        ruleSlider.getRequiredSteps().put(peripheral.designer.wizard.AddAnimationDialog.RULEPANEL, new Boolean(true));

        templates.add(ruleSlider);
    }

    public static AdapterTemplateFactory getInstance () {

        if (instance == null) {
            instance = new AdapterTemplateFactory();
        }

        return instance;
    }

    public java.util.List<SymbolAdapter> getTemplates () {
        return templates;
    }

    public SymbolAdapter createInstanceFor (SymbolAdapter template) {
        return null;
    }

}

