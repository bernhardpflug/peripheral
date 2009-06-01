package peripheral.logic.symboladapter;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.action.ListHideAction;
import peripheral.logic.action.ListShowAction;
import peripheral.logic.action.PointWrapperAction;
import peripheral.logic.action.RegionAddNewAction;
import peripheral.logic.action.SymbolAction;
import peripheral.logic.action.SymbolRotateAction;
import peripheral.logic.action.SymbolScaleAction;
import peripheral.logic.action.SymbolShowAction;
import peripheral.logic.action.SymbolSwapAction;
import peripheral.logic.action.SymbolTranslateAction;
import peripheral.logic.animation.Mover;
import peripheral.logic.filter.MultiplyFilter;
import peripheral.logic.filter.PercentageFilter;
import peripheral.logic.filter.PositionFilter;
import peripheral.logic.filter.RandomPositioningToolPickerFilter;
import peripheral.logic.filter.RandomSymbolPickerFilter;
import peripheral.logic.filter.RandomValuePickerFilter;
import peripheral.logic.filter.StringTemplateFilter;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
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
    //createDummyTemplates();
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
        createStaticSymbolAdapter();
        createSwappers();
        createSliders();
        createScalers();
        createRotors();
        createPopulators();
    }

    private void createStaticSymbolAdapter () {
        /**
         * static symbol adapter 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Static Symbol");
        adapter.setDescription("Adapter, mit dessen Hilfe ein statisches Symbol in die Szene eingefügt werden kann.");

        adapter.setTool(new ToolList(Point.class));
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        SymbolAction symbolAction = new SymbolShowAction(adapter);
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getInitActions().add(wrapperAction);

        templates.add(adapter);
    }

    private void createSwappers () {
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

        Value val = new SensorValue(adapter, "sensorValue", Integer.class);
        UserInput input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "filenameTemplate", "file_###VAL###.png", String.class);
        input = new UserInput("Dateinamen-Template", "", val);
        adapter.getNeededUserInput().add(input);

        StringTemplateFilter stf = new StringTemplateFilter(adapter, "filename");
        VarValue varVal = new VarValue(adapter, "sensorValue");
        stf.putFilterInputValue("inputValue", varVal);
        varVal = new VarValue(adapter, "filenameTemplate");
        stf.putFilterInputValue("template", varVal);
        adapter.getBeforeFilter().add(stf);

        Rule rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolSwapAction(adapter, new VarValue(adapter, "filename"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * @todo: swapper adapter 3: wie swapper 2 + mapping of continuous sensor value to discrete value
         */
    }

    private void createSliders (){
        /**
         * slider 1
         * @todo: option 'distribute evenly' or user specifies position per rule explicitly (option to pick points of line!?)
         */
        /*adapter = new SymbolAdapter();

        adapter.setName("Rule-based slider");
        adapter.setDescription("Slider, bei dem die einzelnen Zustände explizit über Regeln festgelegt werden.");

        adapter.setTool(new Line());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, true);

        rule = new Rule(adapter);
        symbolAction = new SymbolTranslateAction(adapter);
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);*/
        /**
         * slider 2
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Slider based on SensorValue");
        adapter.setDescription("Slider, bei dem ein Sensorwert, dessen Wert innerhalb eines bestimmten Intervalls liegt, auf eine Strecke gemappt wird.");

        adapter.setTool(new Line());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", Float.class);
        UserInput input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        PercentageFilter pf = new PercentageFilter(adapter, "percentage");
        pf.putFilterInputValue("inputValue", new VarValue(adapter, "sensorValue"));
        pf.putFilterInputValue("interval", new SensorIntervalVarValue(adapter, "sensorValue"));
        adapter.getBeforeFilter().add(pf);

        PositionFilter posf = new PositionFilter(adapter, "position");
        posf.putFilterInputValue("percentage", new VarValue(adapter, "percentage"));
        new ConstValue(adapter, "line", (Line) adapter.getTool(), Line.class);
        posf.putFilterInputValue("line", new VarValue(adapter, "line"));
        adapter.getBeforeFilter().add(posf);

        Rule rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        SymbolAction symbolAction = new SymbolTranslateAction(adapter, new VarValue(adapter, "position"));
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * @todo: slider 3: custom mapping from sensorvalues to percentage --> requires mapping component
         */
    }

    private void createScalers (){
        /**
         * scaler 1: vertical scaler
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Vertical scaler based on sensor value");
        adapter.setDescription("Scaler, bei dem der vertikale Skalierungsfaktor durch einen Sensorwert festgelegt wird.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", Float.class);
        UserInput input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "multiplyFactor", 1.0, Float.class);
        input = new UserInput("MultiplyFactor", "Faktor, mit dem der eingehende Sensorwert multipliziert wird. Skalierungsfaktor ergibt sich aus Sensorwert*MultiplyFactor", val);
        adapter.getNeededUserInput().add(input);

        MultiplyFilter mf = new MultiplyFilter(adapter, "factor");
        mf.putFilterInputValue("factor1", new VarValue(adapter, "sensorValue"));
        mf.putFilterInputValue("factor2", new VarValue(adapter, "multiplyFactor"));
        adapter.getBeforeFilter().add(mf);

        Rule rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        SymbolAction symbolAction = new SymbolScaleAction(adapter, new ConstValue(adapter, "factorX", 1.0, Float.class), new VarValue(adapter, "factor"));
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * scaler 2: horizontal scaler
         */
        adapter = adapter.createCopy();

        adapter.setName("Horizontal scaler based on sensor value");
        adapter.setDescription("Scaler, bei dem der horizontale Skalierungsfaktor durch einen Sensorwert festgelegt wird.");

        symbolAction = new SymbolScaleAction(adapter, new VarValue(adapter, "factor"), new ConstValue(adapter, "factorY", 1.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getRules().get(0).getActions().set(0, wrapperAction);

        templates.add(adapter);
    }

    private void createRotors (){
        /**
         * rotor 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Rotor based on SensorValue");
        adapter.setDescription("Rotor, bei dem ein Sensorwert als Rotationswinkel verwendet wird.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", Float.class);
        UserInput input = new UserInput("Sensorwert", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        Rule rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        SymbolAction symbolAction = new SymbolRotateAction(adapter, new VarValue(adapter, "sensorValue"));
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        templates.add(adapter);
    }

    private void createPopulators (){
        /**
         * overlay position populator 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("OverlayPositionPopulator");
        adapter.setDescription("1st Populator...");

        adapter.setTool(new ToolList(Point.class));
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        List<PositioningTool> posTools = adapter.getTool().getElements();
        ListHideAction listHideAction = new ListHideAction(adapter, new ConstValue(adapter, "elementsToHide", posTools, posTools.getClass()));
        adapter.getInitActions().add(listHideAction);

        Value val = new SensorValue(adapter, "sensorValue", Integer.class);
        UserInput input = new UserInput("Sensorwert", "Wert vom Sensor, der die Anzahl der anzuzeigenden Punkte angibt.", val);
        adapter.getNeededUserInput().add(input);

        RandomValuePickerFilter rpf = new RandomPositioningToolPickerFilter(adapter, "pickedValues");
        posTools = adapter.getTool().getElements();
        new ConstValue(adapter, "valueList", posTools, posTools.getClass());
        rpf.putFilterInputValue("valueList", new VarValue(adapter, "valueList"));
        rpf.putFilterInputValue("nrToPick", new VarValue(adapter, "sensorValue"));
        adapter.getBeforeFilter().add(rpf);

        Rule rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        ListShowAction listShowAction = new ListShowAction(adapter, new VarValue(adapter, "pickedValues"), new ConstValue(adapter, "hideOthers", true, Boolean.class));
        rule.getActions().add(listShowAction);
        //rule.getActions().add(new ListHideAction(adapter, new VarValue(adapter, "pickedValues")));
        //rule.getActions().add(listShowAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * static area populator 1
         */
        adapter = new SymbolAdapter();

        adapter.setName("StaticAreaPopulator");
        adapter.setDescription("2nd Populator...");

        adapter.setTool(new Region());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        val = new SensorValue(adapter, "sensorValue", Integer.class);
        input = new UserInput("Sensorwert", "Wert vom Sensor, der die Anzahl der anzuzeigenden Symbole angibt.", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "repeat", true, Boolean.class);
        input = new UserInput("Repeat", "True: Ein Symbol kann mehrmals angzeigt werden, False: Ein Symbol kann nur höchstens einmal angezeigt werden.", val);
        adapter.getNeededUserInput().add(input);

        rpf = new RandomSymbolPickerFilter(adapter, "pickedSymbols");
        List<Symbol> symbolList = ((Region) adapter.getTool()).getSymbols();
        new ConstValue(adapter, "symbolList", symbolList, symbolList.getClass());
        rpf.putFilterInputValue("symbolList", new VarValue(adapter, "symbolList"));
        rpf.putFilterInputValue("nrToPick", new VarValue(adapter, "sensorValue"));
        rpf.putFilterInputValue("repeat", new VarValue(adapter, "repeat"));
        adapter.getBeforeFilter().add(rpf);

        rule = new Rule(adapter);
        rule.getConditions().add(new TrueCondition());
        RegionAddNewAction regionAddNewAction = new RegionAddNewAction(adapter, new VarValue(adapter, "pickedSymbols"));
        rule.getActions().add(regionAddNewAction);
        adapter.getRules().add(rule);

        templates.add(adapter);

        /**
         * Dynamic Area Populator
         */
        adapter = adapter.createCopy();

        adapter.setName("DynamicAreaPopulator");
        adapter.setDescription("3rd Populator...");

        adapter.setAnimator(new Mover(adapter));

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

