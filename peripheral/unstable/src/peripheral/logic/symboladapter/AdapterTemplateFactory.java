package peripheral.logic.symboladapter;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.action.ListShowAction;
import peripheral.logic.action.PointWrapperAction;
import peripheral.logic.action.SymbolAction;
import peripheral.logic.action.SymbolRotateAction;
import peripheral.logic.action.SymbolScaleAction;
import peripheral.logic.action.SymbolSwapAction;
import peripheral.logic.action.SymbolTranslateAction;
import peripheral.logic.filter.MultiplyFilter;
import peripheral.logic.filter.PercentageFilter;
import peripheral.logic.filter.PositionFilter;
import peripheral.logic.filter.RandomPositioningToolPickerFilter;
import peripheral.logic.filter.StringTemplateFilter;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.ToolList;
import peripheral.logic.rule.Rule;
import peripheral.logic.rule.TrueCondition;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.SensorIntervalVarValue;
import peripheral.logic.value.SensorValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;
import peripheral.logic.value.VarValue;

public class AdapterTemplateFactory {

    private static AdapterTemplateFactory instance;
    private java.util.List<SymbolAdapter> templates;

    private AdapterTemplateFactory() {

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
        contSlider.getNeededUserInput().add(new UserInput("ui1", "what the hell", new SensorValue(contSlider, "SensorValue", Number.class)));
        contSlider.getNeededUserInput().add(new UserInput("ui2", "what the hell", new ConstValue(contSlider, "LocationX", new Integer(0), Integer.class)));
        contSlider.getNeededUserInput().add(new UserInput("ui2", "what the hell", new ConstValue(contSlider, "LocationY", new Integer(0), Integer.class)));

        contSlider.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, new Boolean(false));

        templates.add(contSlider);

        SymbolAdapter ruleSlider = new SymbolAdapter();
        ruleSlider.setName("ruleBasedSlider");
        ruleSlider.setTool(new Line());
        ruleSlider.setDescription("A slider were rules \ncan be defined for mapping \nseveral sensorvalues onto\n the position of the picture");
        ruleSlider.getNeededUserInput().add(new UserInput("ui1", "what the hell", new ConstValue(ruleSlider, "EnableSmoothing", new Boolean(true), Boolean.class)));
        ruleSlider.getNeededUserInput().add(new UserInput("ui2", "what the hell", new ConstValue(ruleSlider, "LocationX", new Integer(0), Integer.class)));
        ruleSlider.getNeededUserInput().add(new UserInput("ui2", "what the hell", new ConstValue(ruleSlider, "LocationY", new Integer(0), Integer.class)));

        ruleSlider.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, new Boolean(true));

        templates.add(ruleSlider);
    }

    private void createTemplates() {
        /**
         * swapper adapter 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Swapper based on Conditions");
        adapter.setDescription("Swapper, bei dem mehrere Bedingungen angegeben werden. Dabei wird pro Bedingung festgelegt, welches Bild angezeigt werden soll.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, true);

        SymbolAction symbolAction = new SymbolSwapAction(adapter);
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
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

        Value val = new SensorValue(adapter, "sensorValue", Number.class);
        UserInput input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "filenameTemplate", "file_###VAL###.png", String.class);
        input = new UserInput("Dateinamen-Template", "", val);
        adapter.getNeededUserInput().add(input);

        StringTemplateFilter stf = new StringTemplateFilter(adapter, "filename");
        VarValue varVal = new VarValue(adapter, "sensorValue");
        stf.putFilterInputValue("inputValue", varVal);
        //stf.setInputVar((VarValue)val);
        varVal = new VarValue(adapter, "filenameTemplate");
        //stf.setTemplate(val);
        stf.putFilterInputValue("template", varVal);
        //stf.setOutputVarName("filename");
        adapter.getBeforeFilter().add(stf);

        Rule rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolSwapAction(adapter, new VarValue(adapter, "filename"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
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

        val = new SensorValue(adapter, "sensorValue", Number.class);
        input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        //PercentageFilter pf = new PercentageFilter(adapter, (SensorValue)val);
        PercentageFilter pf = new PercentageFilter(adapter, "percentage");
        //pf.setInputVar(new VarValue(adapter, "sensorValue"));
        pf.putFilterInputValue("inputValue", new VarValue(adapter, "sensorValue"));
        pf.putFilterInputValue("interval", new SensorIntervalVarValue(adapter, "sensorValue"));
        //pf.setOutputVarName("percentage");
        adapter.getBeforeFilter().add(pf);

        //PositionFilter posf = new PositionFilter(adapter, (Line)adapter.getTool());
        PositionFilter posf = new PositionFilter(adapter, "position");
        //posf.setInputVar(new VarValue(adapter, "percentage"));
        posf.putFilterInputValue("percentage", new VarValue(adapter, "percentage"));
        new ConstValue(adapter, "line", (Line) adapter.getTool(), Line.class);
        posf.putFilterInputValue("line", new VarValue(adapter, "line"));
        //posf.setOutputVarName("position");
        adapter.getBeforeFilter().add(posf);

        rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolTranslateAction(adapter, new VarValue(adapter, "position"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * scaler 1
         */
        adapter = new SymbolAdapter();

        adapter.setName("Vertical scaler based on sensor value");
        adapter.setDescription("Scaler, bei dem der vertikale Skalierungsfaktor durch einen Sensorwert festgelegt wird.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        val = new SensorValue(adapter, "sensorValue", Number.class);
        input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "multiplyFactor", 1.0, Number.class);
        input = new UserInput("MultiplyFactor", "Faktor, mit dem der eingehende Sensorwert multipliziert wird. Skalierungsfaktor ergibt sich aus Sensorwert*MultiplyFactor", val);
        adapter.getNeededUserInput().add(input);

        MultiplyFilter mf = new MultiplyFilter(adapter, "factorY");
        mf.putFilterInputValue("factor1", new VarValue(adapter, "sensorValue"));
        mf.putFilterInputValue("factor2", new VarValue(adapter, "multiplyFactor"));
        adapter.getBeforeFilter().add(mf);

        rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolScaleAction(adapter, new ConstValue(adapter, "factorX", 1.0, Float.class), new VarValue(adapter, "factorY"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * rotor 1
         */
        adapter = new SymbolAdapter();

        adapter.setName("Rotor based on SensorValue");
        adapter.setDescription("Rotor, bei dem ein Sensorwert als Rotationswinkel verwendet wird.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        val = new SensorValue(adapter, "sensorValue", Number.class);
        input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolRotateAction(adapter, new VarValue(adapter, "sensorValue"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

         /**
         * overlay position populator 1
         */
        adapter = new SymbolAdapter();

        adapter.setName("OverlayPositionPopulator");
        adapter.setDescription("1st Populator...");

        adapter.setTool(new ToolList(Point.class));
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        val = new SensorValue(adapter, "sensorValue", Integer.class);
        input = new UserInput("Sensorwert", "Wert vom Sensor, der die Anzahl der anzuzeigenden Punkte angibt.", val);
        adapter.getNeededUserInput().add(input);

        RandomPositioningToolPickerFilter rpf = new RandomPositioningToolPickerFilter(adapter, "pickedValues");
        List<PositioningTool> posTools = adapter.getTool().getElements();
        new ConstValue(adapter, "valueList", posTools, posTools.getClass());
        rpf.putFilterInputValue("valueList", new VarValue(adapter, "valueList"));
        rpf.putFilterInputValue("nrToPick", new VarValue(adapter, "sensorValue"));
        adapter.getBeforeFilter().add(rpf);

        rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        ListShowAction listShowAction = new ListShowAction(adapter, new VarValue(adapter, "pickedValues"), new ConstValue(adapter, "hideOthers", true, Boolean.class));
        rule.getActions().add(listShowAction);
        adapter.getRules().add(rule);

        templates.add(adapter);
    }

    public static AdapterTemplateFactory getInstance() {

        if (instance == null) {
            instance = new AdapterTemplateFactory();
        }

        return instance;
    }

    public java.util.List<SymbolAdapter> getTemplates() {
        return templates;
    }

    public SymbolAdapter createInstanceFor(SymbolAdapter template) {
        return template.createCopy();
    }
}

