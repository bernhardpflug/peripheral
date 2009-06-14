package peripheral.logic.symboladapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import peripheral.logic.action.ListHideAction;
import peripheral.logic.action.ListShowAction;
import peripheral.logic.action.PointWrapperAction;
import peripheral.logic.action.RegionAddNewAction;
import peripheral.logic.action.SymbolAction;
import peripheral.logic.action.SymbolBrightnessAction;
import peripheral.logic.action.SymbolContrastAction;
import peripheral.logic.action.SymbolRotateAction;
import peripheral.logic.action.SymbolScaleAction;
import peripheral.logic.action.SymbolShowAction;
import peripheral.logic.action.SymbolSwapAction;
import peripheral.logic.action.SymbolTranslateAction;
import peripheral.logic.animation.Mover;
import peripheral.logic.datatype.Directory;
import peripheral.logic.datatype.Interval;
import peripheral.logic.datatype.SymbolList;
import peripheral.logic.filter.MultiplyFilter;
import peripheral.logic.filter.IntervalValueToPercentageFilter;
import peripheral.logic.filter.PercentageToIntervalValueFilter;
import peripheral.logic.filter.PositionFilter;
import peripheral.logic.filter.RandomPositioningToolPickerFilter;
import peripheral.logic.filter.RandomSymbolPickerFilter;
import peripheral.logic.filter.RandomValuePickerFilter;
import peripheral.logic.filter.SensorValueDurationFilter;
import peripheral.logic.filter.SensorValueFilter;
import peripheral.logic.filter.SensorValueThresholdFilter;
import peripheral.logic.filter.StringTemplateFilter;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.positioningtool.ToolList;
import peripheral.logic.rule.Rule;
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
        createColorizers();
        createSliders();
        createScalers();
        createRotors();
        createPopulators();
    }

    private void createStaticSymbolAdapter() {
        /**
         * static symbol adapter 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Static Symbol");
        adapter.setDescription("Adapter, which allows you to add a static symbol to the theme.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Preselect, false);

        //adapter.setAllowOrientedSymbols(true);

        SymbolAction symbolAction = new SymbolShowAction(adapter);
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getInitActions().add(wrapperAction);

        templates.add(adapter);
    }

    private void createSwappers() {
        /**
         * swapper adapter 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Swapper based on Conditions");
        adapter.setDescription("Swapper, for which you have to specify several conditions. For each condition a swapping image has to be specified.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, true);
        adapter.setMinAllowedNumberOfSymbols(0);
        adapter.setMaxAllowedNumberOfSymbols(0);

        SymbolAction symbolAction = new SymbolSwapAction(adapter);
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.setDefaultAction(wrapperAction);

        symbolAction = new SymbolSwapAction(adapter, new ConstValue(adapter, "nullFile", null, File.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        //adapter.getInitActions().add(wrapperAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        templates.add(adapter);

        /**
         * swapper adapter 2
         */
        adapter = new SymbolAdapter();

        adapter.setName("Swapper based on Sensorvalue and Filename-Template");
        adapter.setDescription("Swapper, for which a 'Filename-Template' is used. Input for the template is a sensor value.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        adapter.setMinAllowedNumberOfSymbols(0);
        adapter.setMaxAllowedNumberOfSymbols(0);

        Value val = new SensorValue(adapter, "sensorValue", Integer.class);
        UserInput input = new UserInput("Sensor value", "", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "filenameTemplate", "file_###VAL###.png", String.class);
        input = new UserInput("Filename-Template", "", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "fileFolder", null, Directory.class);
        input = new UserInput("Image folder", "", val);
        adapter.getNeededUserInput().add(input);

        StringTemplateFilter stf = new StringTemplateFilter(adapter, "filename");
        VarValue varVal = new VarValue(adapter, "sensorValue");
        stf.putFilterInputValue("inputValue", varVal);
        varVal = new VarValue(adapter, "filenameTemplate");
        stf.putFilterInputValue("template", varVal);
        adapter.getBeforeFilter().add(stf);

        Rule rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolSwapAction(adapter, new VarValue(adapter, "filename"), new VarValue(adapter, "fileFolder"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        symbolAction = new SymbolSwapAction(adapter, new ConstValue(adapter, "nullFile", null, File.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        templates.add(adapter);

    /**
     * @todo: swapper adapter 3: wie swapper 2 + mapping of continuous sensor value to discrete value
     */
    }

    private void createColorizers() {
        /**
         * colorizer adapter 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Colorizer (Brightness&Contrast) based on sensor values");
        adapter.setDescription("A colorizer, which lets you specify brightness and contrast of an image based on sensor values.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValueBrightness", Integer.class);
        UserInput input = new UserInput("Brightness", "Value of sensor, which is used to calculate brightness.", val);
        adapter.getNeededUserInput().add(input);

        IntervalValueToPercentageFilter ipf = new IntervalValueToPercentageFilter(adapter, "percentageBrightness");
        ipf.putFilterInputValue("inputValue", new VarValue(adapter, "sensorValueBrightness"));
        ipf.putFilterInputValue("interval", new SensorIntervalVarValue(adapter, "sensorValueBrightness"));
        adapter.getBeforeFilter().add(ipf);

        val = new SensorValue(adapter, "sensorValueContrast", Integer.class);
        input = new UserInput("Contrast", "Value of sensor, which is used to calculate contrast.", val);
        adapter.getNeededUserInput().add(input);

        ipf = new IntervalValueToPercentageFilter(adapter, "percentageContrast");
        ipf.putFilterInputValue("inputValue", new VarValue(adapter, "sensorValueContrast"));
        ipf.putFilterInputValue("interval", new SensorIntervalVarValue(adapter, "sensorValueContrast"));
        adapter.getBeforeFilter().add(ipf);

        Rule rule = new Rule(adapter);
        SymbolAction symbolAction = new SymbolBrightnessAction(adapter, new VarValue(adapter, "percentageBrightness"));
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        symbolAction = new SymbolContrastAction(adapter, new VarValue(adapter, "percentageContrast"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        symbolAction = new SymbolBrightnessAction(adapter, new ConstValue(adapter, "sensorFailureBrightness", 0.0f, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);
        symbolAction = new SymbolContrastAction(adapter, new ConstValue(adapter, "sensorFailureContrast", 1.0f, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        templates.add(adapter);

        SymbolAdapter adapterBrightness = adapter.createCopy();

        adapterBrightness.setName("Colorizer (Brightness only) based on sensor value");
        adapterBrightness.setDescription("A colorizer, which lets you specify brightness of an image based on a sensor value.");

        adapterBrightness.getNeededUserInput().remove(1);
        adapterBrightness.getBeforeFilter().remove(1);
        adapterBrightness.getRules().get(0).getActions().remove(1);
        adapterBrightness.getSensorFailureActions().remove(1);

        templates.add(adapterBrightness);

        SymbolAdapter adapterContrast = adapter.createCopy();

        adapterContrast.setName("Colorizer (Contrast only) based on sensor value");
        adapterContrast.setDescription("A colorizer, which lets you specify contrast of an image based on a sensor value.");

        adapterContrast.getNeededUserInput().remove(0);
        adapterContrast.getBeforeFilter().remove(0);
        adapterContrast.getRules().get(0).getActions().remove(0);
        adapterContrast.getSensorFailureActions().remove(0);

        templates.add(adapterContrast);
    }

    private void createSliders() {
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

        adapter.setName("Slider based on sensor value");
        adapter.setDescription("Slider, bei dem ein Sensorwert, dessen Wert innerhalb eines bestimmten Intervalls liegt, auf eine Strecke gemappt wird.");
        adapter.setDescription("Slider, for which a sensor value, whose value lies within a certain interval, is mapped on a specified line.");

        adapter.setTool(new Line());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", Float.class);
        UserInput input = new UserInput("Sensor value", "Value from sensor", val);
        adapter.getNeededUserInput().add(input);

        IntervalValueToPercentageFilter pf = new IntervalValueToPercentageFilter(adapter, "percentage");
        pf.putFilterInputValue("inputValue", new VarValue(adapter, "sensorValue"));
        pf.putFilterInputValue("interval", new SensorIntervalVarValue(adapter, "sensorValue"));
        adapter.getBeforeFilter().add(pf);

        PositionFilter posf = new PositionFilter(adapter, "position");
        posf.putFilterInputValue("percentage", new VarValue(adapter, "percentage"));
        new ConstValue(adapter, "line", (Line) adapter.getTool(), Line.class);
        posf.putFilterInputValue("line", new VarValue(adapter, "line"));
        adapter.getBeforeFilter().add(posf);

        Rule rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        SymbolAction symbolAction = new SymbolTranslateAction(adapter, new VarValue(adapter, "position"));
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        symbolAction = new SymbolTranslateAction(adapter, new ConstValue(adapter, "startPos", ((Line) adapter.getTool()).getStartPoint(), java.awt.Point.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        this.addDefaultSensorValueFilters(adapter);

        templates.add(adapter);

    /**
     * @todo: slider 3: custom mapping from sensorvalues to percentage --> requires mapping component
     */
    }

    private void createScalers() {
        /**
         * scaler 1: vertical scaler
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Vertical scaler based on sensor value");
        adapter.setDescription("Scaler, for which the vertical scaling factor is given by a specified sensor value.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", Float.class);
        UserInput input = new UserInput("Sensor value", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "multiplyFactor", 1.0f, Float.class);
        input = new UserInput("MultiplyFactor", "Faktor, mit dem der eingehende Sensorwert multipliziert wird. Skalierungsfaktor ergibt sich aus Sensorwert*MultiplyFactor", val);
        adapter.getNeededUserInput().add(input);

        MultiplyFilter mf = new MultiplyFilter(adapter, "factor");
        mf.putFilterInputValue("factor1", new VarValue(adapter, "sensorValue"));
        mf.putFilterInputValue("factor2", new VarValue(adapter, "multiplyFactor"));
        adapter.getBeforeFilter().add(mf);

        Rule rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        SymbolAction symbolAction = new SymbolScaleAction(adapter, new ConstValue(adapter, "factorX", 1.0, Float.class), new VarValue(adapter, "factor"));
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        symbolAction = new SymbolScaleAction(adapter, new ConstValue(adapter, "failureFactorX", 1.0, Float.class), new ConstValue(adapter, "failureFactorY", 1.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        addDefaultSensorValueFilters(adapter);

        templates.add(adapter);

        /**
         * scaler 2: horizontal scaler
         */
        adapter = adapter.createCopy();

        adapter.setName("Horizontal scaler based on sensor value");
        adapter.setDescription("Scaler, for which the horizontal scaling factor is given by a specified sensor value.");

        symbolAction = new SymbolScaleAction(adapter, new VarValue(adapter, "factor"), new ConstValue(adapter, "factorY", 1.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getRules().get(0).getActions().set(0, wrapperAction);

        templates.add(adapter);

        /**
         * scaler 3: scaler - both directions
         */
        adapter = new SymbolAdapter();

        adapter.setName("Horiztontal and vertical scaler based on sensor values");
        adapter.setDescription("Scaler, for which the scaling factors for both directions is given by specified sensor values.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        val = new SensorValue(adapter, "sensorValueX", Float.class);
        input = new UserInput("Sensor value horizontal", "Wert vom Sensor, der für den horizontalen Skalierungsfaktor herangezogen wird.", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "multiplyFactorX", 1.0, Float.class);
        input = new UserInput("MultiplyFactor horizontal", "Faktor, mit dem der eingehende Sensorwert multipliziert wird. Skalierungsfaktor ergibt sich aus (Sensorwert horizontal)*(MultiplyFactor horizontal)", val);
        adapter.getNeededUserInput().add(input);

        val = new SensorValue(adapter, "sensorValueY", Float.class);
        input = new UserInput("Sensor value vertical", "Wert vom Sensor, der für den vertikalen Skalierungsfaktor herangezogen wird.", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "multiplyFactorY", 1.0, Float.class);
        input = new UserInput("MultiplyFactor vertical", "Faktor, mit dem der eingehende Sensorwert multipliziert wird. Skalierungsfaktor ergibt sich aus (Sensorwert vertikal)*(MultiplyFactor vertikal)", val);
        adapter.getNeededUserInput().add(input);

        mf = new MultiplyFilter(adapter, "factorX");
        mf.putFilterInputValue("factor1", new VarValue(adapter, "sensorValueX"));
        mf.putFilterInputValue("factor2", new VarValue(adapter, "multiplyFactorX"));
        adapter.getBeforeFilter().add(mf);

        mf = new MultiplyFilter(adapter, "factorY");
        mf.putFilterInputValue("factor1", new VarValue(adapter, "sensorValueY"));
        mf.putFilterInputValue("factor2", new VarValue(adapter, "multiplyFactorY"));
        adapter.getBeforeFilter().add(mf);

        rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolScaleAction(adapter, new VarValue(adapter, "factorX"), new VarValue(adapter, "factorY"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        symbolAction = new SymbolScaleAction(adapter, new ConstValue(adapter, "failureFactorX", 1.0, Float.class), new ConstValue(adapter, "failureFactorY", 1.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        addDefaultSensorValueFilters(adapter);

        templates.add(adapter);

        /**
         * scaler 4: rule based scaler
         */
        adapter = new SymbolAdapter();

        adapter.setName("Rule-based scaler");
        adapter.setDescription("Scaler, for which the scaling factors are specified explicitly, per rule.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, true);

        rule = new Rule(adapter);
        symbolAction = new SymbolScaleAction(adapter);
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.setDefaultAction(wrapperAction);

        symbolAction = new SymbolScaleAction(adapter, new ConstValue(adapter, "failureFactorX", 1.0, Float.class), new ConstValue(adapter, "failureFactorY", 1.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        addDefaultSensorValueFilters(adapter);

        templates.add(adapter);
    }

    private void createRotors() {
        /**
         * rotor 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Rotor based on sensor value");
        adapter.setDescription("Rotor, for which a sensor value is used as rotation angle.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", Float.class);
        UserInput input = new UserInput("Sensor value", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        Rule rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        SymbolAction symbolAction = new SymbolRotateAction(adapter, new VarValue(adapter, "sensorValue"));
        PointWrapperAction wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        symbolAction = new SymbolRotateAction(adapter, new ConstValue(adapter, "failureAngle", 0.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        addDefaultSensorValueFilters(adapter);

        templates.add(adapter);

        /**
         * rotor 2
         */
        adapter = new SymbolAdapter();

        adapter.setName("Rotor based on SensorValue");
        adapter.setDescription("Rotor, for which a sensor value, which is within a certain interval, is converted into percentage. From the percentage value the rotation angle is derivated, e.g.: 25% --> 90°, 50% --> 180°, ...");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        val = new SensorValue(adapter, "sensorValue", Double.class);
        input = new UserInput("Sensor value", "Wert vom Sensor", val);
        adapter.getNeededUserInput().add(input);

        IntervalValueToPercentageFilter ipf = new IntervalValueToPercentageFilter(adapter, "percentage");
        ipf.putFilterInputValue("inputValue", new VarValue(adapter, "sensorValue"));
        ipf.putFilterInputValue("interval", new SensorIntervalVarValue(adapter, "sensorValue"));
        adapter.getBeforeFilter().add(ipf);

        PercentageToIntervalValueFilter pif = new PercentageToIntervalValueFilter(adapter, "angle");
        pif.putFilterInputValue("percentage", new VarValue(adapter, "percentage"));
        try {
            new ConstValue(adapter, "angleInterval", new Interval(0, 360), Interval.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pif.putFilterInputValue("interval", new VarValue(adapter, "angleInterval"));
        adapter.getBeforeFilter().add(pif);

        rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        symbolAction = new SymbolRotateAction(adapter, new VarValue(adapter, "angle"));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        rule.getActions().add(wrapperAction);
        adapter.getRules().add(rule);

        symbolAction = new SymbolRotateAction(adapter, new ConstValue(adapter, "failureAngle", 0.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        addDefaultSensorValueFilters(adapter);

        templates.add(adapter);

        /**
         * rotor 3: rule-based rotor
         */
        adapter = new SymbolAdapter();

        adapter.setName("Rule-based rotor");
        adapter.setDescription("Rotor, for which the rotation angle is specified explicitly, per rule.");

        adapter.setTool(new Point());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, true);

        rule = new Rule(adapter);
        symbolAction = new SymbolRotateAction(adapter);
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.setDefaultAction(wrapperAction);

        symbolAction = new SymbolRotateAction(adapter, new ConstValue(adapter, "failureAngle", 0.0, Float.class));
        wrapperAction = new PointWrapperAction(adapter, symbolAction);
        adapter.getSensorFailureActions().add(wrapperAction);

        addDefaultSensorValueFilters(adapter);

        templates.add(adapter);
    }

    private void createPopulators() {
        /**
         * overlay position populator 1
         */
        SymbolAdapter adapter = new SymbolAdapter();

        adapter.setName("Position-Populator");
        adapter.setDescription("Populator, for which a number of points and a sensor value have to be specified. The value of the sensor specifies, how many points are shown at a time.");

        adapter.setTool(new ToolList(Point.class));
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);

        Value val = new SensorValue(adapter, "sensorValue", Integer.class);
        UserInput input = new UserInput("Sensor value", "Wert vom Sensor, der die Anzahl der anzuzeigenden Punkte angibt.", val);
        adapter.getNeededUserInput().add(input);

        RandomValuePickerFilter rpf = new RandomPositioningToolPickerFilter(adapter, "pickedValues");
        List<PositioningTool> posTools = adapter.getTool().getElements();
        new ConstValue(adapter, "valueList", posTools, posTools.getClass());
        rpf.putFilterInputValue("valueList", new VarValue(adapter, "valueList"));
        rpf.putFilterInputValue("nrToPick", new VarValue(adapter, "sensorValue"));
        adapter.getBeforeFilter().add(rpf);

        Rule rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        ListShowAction listShowAction = new ListShowAction(adapter, new VarValue(adapter, "pickedValues"), new ConstValue(adapter, "hideOthers", true, Boolean.class));
        rule.getActions().add(listShowAction);
        //rule.getActions().add(new ListHideAction(adapter, new VarValue(adapter, "pickedValues")));
        //rule.getActions().add(listShowAction);
        adapter.getRules().add(rule);

        posTools = adapter.getTool().getElements();
        ListHideAction listHideAction = new ListHideAction(adapter, new ConstValue(adapter, "elementsToHide", posTools, posTools.getClass()));
        adapter.getInitActions().add(listHideAction);
        adapter.getSensorFailureActions().add(listHideAction);

        templates.add(adapter);

        /**
         * static area populator 1
         */
        adapter = new SymbolAdapter();

        adapter.setName("Static area-populator");
        adapter.setDescription("Populator, for which a region and a sensor value have to be specified. The value of the sensor specifies, how many symbols are shown at a time.");

        adapter.setTool(new Region());
        adapter.getRequiredSteps().put(SymbolAdapter.RequiredStep.Rules, false);
        adapter.setMaxAllowedNumberOfSymbols(-1);

        val = new SensorValue(adapter, "sensorValue", Integer.class);
        input = new UserInput("Sensor value", "Wert vom Sensor, der die Anzahl der anzuzeigenden Symbole angibt.", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "repeat", true, Boolean.class);
        input = new UserInput("Symbol-Repeat", "True: Ein Symbol kann mehrmals angzeigt werden, False: Ein Symbol kann nur höchstens einmal angezeigt werden.", val);
        adapter.getNeededUserInput().add(input);

        rpf = new RandomSymbolPickerFilter(adapter, "pickedSymbols");
        List<Symbol> symbolList = ((Region) adapter.getTool()).getSymbols();
        new ConstValue(adapter, "symbolList", symbolList, symbolList.getClass());
        rpf.putFilterInputValue("symbolList", new VarValue(adapter, "symbolList"));
        rpf.putFilterInputValue("nrToPick", new VarValue(adapter, "sensorValue"));
        rpf.putFilterInputValue("repeat", new VarValue(adapter, "repeat"));
        adapter.getBeforeFilter().add(rpf);

        rule = new Rule(adapter);
        //rule.getConditions().add(new TrueCondition());
        RegionAddNewAction regionAddNewAction = new RegionAddNewAction(adapter, new VarValue(adapter, "pickedSymbols"));
        rule.getActions().add(regionAddNewAction);
        adapter.getRules().add(rule);

        regionAddNewAction = new RegionAddNewAction(adapter, new ConstValue(adapter, "initSymbols", new SymbolList(), SymbolList.class));
        adapter.getInitActions().add(regionAddNewAction);
        adapter.getSensorFailureActions().add(regionAddNewAction);

        templates.add(adapter);

        /**
         * Dynamic Area Populator
         */
        adapter = adapter.createCopy();

        adapter.setName("Dynamic area-populator");
        adapter.setDescription("Populator, for which a region and a sensor value have to be specified. The value of the sensor specifies, how many symbols are shown at a time. Additionally the dynamic animation behavior of the symbols can be specified.");

        adapter.setAnimator(new Mover(adapter));
        adapter.setAllowOrientedSymbols(true);

        templates.add(adapter);
    }

    private void addDefaultSensorValueFilters(SymbolAdapter adapter) {
        Value val = new ConstValue(adapter, "applyThresholdFilter", true, Boolean.class);
        UserInput input = new UserInput("Use threshold", "", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "threshold", 0.0f, Float.class);
        input = new UserInput("Threshold", "Amount of change, which has to occur, before the change is propagated to the visualization.", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "applyDurationFilter", true, Boolean.class);
        input = new UserInput("Use duration filter", "", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "duration", 0.0f, Float.class);
        input = new UserInput("Duration", "Time in seconds, for which a sensor value has to stay within the threshold specified below, before the change is propagated to the visualization.", val);
        adapter.getNeededUserInput().add(input);

        val = new ConstValue(adapter, "thresholdDuration", 0.001f, Float.class);
        input = new UserInput("Duration-Threshold", "Amount of change, which is allowed, so that the sensor value is treated as if no change would had occured.", val);
        adapter.getNeededUserInput().add(input);

        SensorValueFilter sensorValueFilter;
        for (Value value : (new ArrayList<Value>(adapter.getVarpool().values()))) {
            if (value instanceof SensorValue) {
                try {
                    sensorValueFilter = new SensorValueThresholdFilter(adapter, (SensorValue) value);
                    sensorValueFilter.putFilterInputValue("applyFilter", new VarValue(adapter, "applyThresholdFilter"));
                    sensorValueFilter.putFilterInputValue("threshold", new VarValue(adapter, "threshold"));
                    adapter.getSensorValueFilter().add(sensorValueFilter);

                    sensorValueFilter = new SensorValueDurationFilter(adapter, (SensorValue) value);
                    sensorValueFilter.putFilterInputValue("applyFilter", new VarValue(adapter, "applyDurationFilter"));
                    sensorValueFilter.putFilterInputValue("duration", new VarValue(adapter, "duration"));
                    sensorValueFilter.putFilterInputValue("threshold", new VarValue(adapter, "thresholdDuration"));
                    adapter.getSensorValueFilter().add(sensorValueFilter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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

