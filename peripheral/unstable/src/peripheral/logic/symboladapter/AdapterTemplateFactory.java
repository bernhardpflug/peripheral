package peripheral.logic.symboladapter;

import java.util.ArrayList;
import peripheral.logic.action.PointWrapperAction;
import peripheral.logic.action.SymbolAction;
import peripheral.logic.action.SymbolSwapAction;
import peripheral.logic.action.SymbolTranslateAction;
import peripheral.logic.datatype.Interval;
import peripheral.logic.filter.PercentageFilter;
import peripheral.logic.filter.PositionFilter;
import peripheral.logic.filter.StringTemplateFilter;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.rule.Rule;
import peripheral.logic.rule.TrueCondition;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.SensorValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;
import peripheral.logic.value.VarValue;


public class AdapterTemplateFactory {

    private static AdapterTemplateFactory instance;

    private java.util.List<SymbolAdapter> templates;

    private AdapterTemplateFactory () {

        templates = new ArrayList<SymbolAdapter>();

        createTemplates();

        //TODO just for testing purposes; replace by real templates
        createDummyTemplates();
    }

    private void createDummyTemplates() {

        SymbolAdapter contSlider = new SymbolAdapter();
        contSlider.setName("continuousSlider");
        contSlider.setTool(new Point());
        contSlider.setDescription("A slider based on \na sensorvalue that continously \nmaps the sensorvalue\n onto a selected area");
        contSlider.getNeededUserInput().add(new UserInput("ui1","what the hell", new SensorValue(contSlider, "SensorValue",null)));
        contSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue(contSlider, "LocationX",new Integer(0))));
        contSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue(contSlider, "LocationY",new Integer(0))));

        contSlider.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, new Boolean(false));

        templates.add(contSlider);

        SymbolAdapter ruleSlider = new SymbolAdapter();
        ruleSlider.setName("ruleBasedSlider");
        ruleSlider.setTool(new Line());
        ruleSlider.setDescription("A slider were rules \ncan be defined for mapping \nseveral sensorvalues onto\n the position of the picture");
        ruleSlider.getNeededUserInput().add(new UserInput("ui1","what the hell", new ConstValue(ruleSlider, "EnableSmoothing",new Boolean(true))));
        ruleSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue(ruleSlider, "LocationX",new Integer(0))));
        ruleSlider.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue(ruleSlider, "LocationY",new Integer(0))));

        ruleSlider.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, new Boolean(true));

        templates.add(ruleSlider);
    }

    private void createTemplates (){
        /**
         * swapper adapter 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Swapper based on Conditions");
        adapter.setDescription("Swapper, bei dem mehrere Bedingungen angegeben werden. Dabei wird pro Bedingung festgelegt, welches Bild angezeigt werden soll.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, true);

        SymbolAction symbolAction = new SymbolSwapAction(adapter);
        PointWrapperAction wrapperAction = PointWrapperAction.getPointWrapperAction(adapter, symbolAction);
        adapter.setDefaultAction(wrapperAction);

        templates.add(adapter);

        /**
         * swapper adapter 2
         */
        adapter = new SymbolAdapter();

        adapter.setName("Swapper based on Sensorvalue and String-Template");
        adapter.setDescription("Swapper, bei dem ein StringTemplate verwendet wird. Als Eingabe für das Template dient ein Sensorwert.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", null);
        UserInput input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "filenameTemplate", "file_###VAL###.png");
        input = new UserInput("Dateinamen-Template", "", val);
        adapter.getNeededUserInput().add(input);

        StringTemplateFilter stf = new StringTemplateFilter(adapter);
        val = new VarValue(adapter, "sensorValue");
        stf.setInputVar((VarValue)val);
        val = new VarValue(adapter, "filenameTemplate");
        stf.setTemplate(val);
        stf.setOutputVarName("filename");
        adapter.getBeforeFilter().add(stf);

        Rule rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolSwapAction(adapter, new VarValue(adapter, "filename"));
        wrapperAction = PointWrapperAction.getPointWrapperAction(adapter, symbolAction);
        rule.addAction(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * slider 1
         */
        adapter = new SymbolAdapter();

        adapter.setName("Slider based on SensorValue");
        adapter.setDescription("Slider, bei dem ein Sensorwert, dessen Wert innerhalb eines bestimmten Intervalls liegt, auf eine Strecke gemappt wird.");

        adapter.setTool(new Line());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        val = new SensorValue(adapter, "sensorValue", null);
        input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        //wie greift man auf lower/upper bound des sensors zu?
        Interval interval = null;

        PercentageFilter pf = new PercentageFilter(adapter, (SensorValue)val);
        pf.setInputVar(new VarValue(adapter, "sensorValue"));
        pf.setOutputVarName("percentage");
        adapter.getBeforeFilter().add(pf);

        PositionFilter posf = new PositionFilter(adapter, (Line)adapter.getTool());
        posf.setInputVar(new VarValue(adapter, "percentage"));
        posf.setOutputVarName("position");
        adapter.getBeforeFilter().add(posf);

        rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolTranslateAction(adapter, new VarValue(adapter, "positionX"), new VarValue(adapter, "positionY"));
        wrapperAction = PointWrapperAction.getPointWrapperAction(adapter, symbolAction);
        rule.addAction(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);
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
        return template.createCopy();
    }

}

