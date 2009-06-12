package peripheral.logic.symboladapter;

import peripheral.logic.animation.SymbolAnimator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import peripheral.logic.action.ActionToolAction;
import peripheral.logic.filter.Filter;
import peripheral.logic.filter.SensorValueFilter;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.rule.Condition;
import peripheral.logic.rule.DefaultRule;
import peripheral.logic.rule.Rule;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.value.SensorValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class SymbolAdapter implements Serializable {

    public enum RequiredStep implements Serializable {

        Preselect,
        Rules
    }
    private ActionTool tool;
    private java.util.List<Filter> beforeFilter;
    private java.util.List<Filter> afterFilter;
    private java.util.List<SensorValueFilter> sensorValueFilter;
    private java.util.List<Rule> rules;
    private java.util.Map<String, Value> varpool;
    private java.util.List<UserInput> neededUserInput;
    private String name;
    private String description;
    private SymbolAnimator animator;
    private java.util.ArrayList<Sensor> preselectedSensors;
    private Map<RequiredStep, Boolean> requiredSteps;
    private ActionToolAction defaultAction;
    private List<ActionToolAction> initActions;
    private List<ActionToolAction> sensorFailureActions;
    //define as -1 for infinite
    private int maxAllowedNumberOfSymbols;
    private int minAllowedNumberOfSymbols;
    private boolean sensorFailure = false;

    //defines whether symbols of the positioningtools of this adapter
    //may add a second file for a symbol if the symbol changes its direction
    //in the animation
    private boolean allowOrientedSymbols;

    public SymbolAdapter() {

        beforeFilter = new ArrayList<Filter>();
        afterFilter = new ArrayList<Filter>();
        sensorValueFilter = new ArrayList<SensorValueFilter>();

        rules = new ArrayList<Rule>();

        varpool = new java.util.HashMap<String, Value>();

        neededUserInput = new ArrayList<UserInput>();

        preselectedSensors = new java.util.ArrayList<Sensor>();

        requiredSteps = new java.util.HashMap<RequiredStep, Boolean>();

        initActions = new ArrayList<ActionToolAction>();

        sensorFailureActions = new ArrayList<ActionToolAction>();

        allowOrientedSymbols = false;

        maxAllowedNumberOfSymbols = 1;
        minAllowedNumberOfSymbols = 1;

        //set default values for required steps
        requiredSteps.put(RequiredStep.Preselect, true);
        requiredSteps.put(RequiredStep.Rules, false);
    }

    public java.util.List<Filter> getAfterFilter() {
        return afterFilter;
    }

    public SymbolAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(SymbolAnimator val) {
        if (animator != null) {
            this.neededUserInput.removeAll(animator.getUserInput());
        }
        this.neededUserInput.addAll(val.getUserInput());

        this.animator = val;
    }

    public java.util.List<Filter> getBeforeFilter() {
        return beforeFilter;
    }

    public List<SensorValueFilter> getSensorValueFilter() {
        return sensorValueFilter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String val) {
        this.description = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String val) {
        this.name = val;
    }

    public java.util.List<UserInput> getNeededUserInput() {
        return neededUserInput;
    }

    public java.util.List<Rule> getRules() {
        return rules;
    }

    public ActionTool getTool() {
        return tool;
    }

    /**
     *  <p style="margin-top: 0">
     *        test doc
     *      </p>
     */
    public void setTool(ActionTool val) {
        this.tool = val;
    }

    public java.util.Map<String, Value> getVarpool() {
        return varpool;
    }

    public void setAllowOrientedSymbols(boolean value) {
        this.allowOrientedSymbols = value;
    }

    public boolean areOrientedSymbolsAllowed() {
        return this.allowOrientedSymbols;
    }

    public int getMaxAllowedNumberOfSymbols() {
        return maxAllowedNumberOfSymbols;
    }

    public void setMaxAllowedNumberOfSymbols(int maxAllowedNumberOfSymbols) {
        this.maxAllowedNumberOfSymbols = maxAllowedNumberOfSymbols;
    }

    public int getMinAllowedNumberOfSymbols() {
        return minAllowedNumberOfSymbols;
    }

    public void setMinAllowedNumberOfSymbols(int minAllowedNumberOfSymbols) {
        if (minAllowedNumberOfSymbols <= this.maxAllowedNumberOfSymbols || this.maxAllowedNumberOfSymbols == -1) {
            this.minAllowedNumberOfSymbols = minAllowedNumberOfSymbols;
        } else {
            peripheral.logic.Logging.getLogger().finer(minAllowedNumberOfSymbols +
                    " for minNumberOfSymbols is higher as defined maxvalue " + this.maxAllowedNumberOfSymbols + "\n" +
                    "minvalue not updated");
        }
    }

    public SymbolAdapter createCopy() {
        SymbolAdapter copy = null;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);

            os.writeObject(this);
            os.flush();

            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream is = new ObjectInputStream(in);

            copy = (SymbolAdapter) is.readObject();

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return copy;
    }

    /**
     * Determines whether given sensor has dependencies in UserInputs
     * @param sensor
     * @return
     */
    public boolean isUsed(Sensor sensor) {

        for (UserInput userInput : neededUserInput) {

            //iterate through all user inputs with sensorvalues
            if (userInput.getValue() instanceof SensorValue) {
                SensorValue sensorValue = (SensorValue) userInput.getValue();

                //iterate through all sensorchannels of this sensor

                for (SensorChannel channel : sensor.getSensorChannels()) {

                    if (sensorValue.getSensorChannel().equals(channel)) {

                        return true;
                    }
                }

            }
        }

        //iterate through rules and check for sensor uage
        for (Rule rule : rules) {

            for (Condition condition : rule.getConditions()) {

                for (SensorChannel channel : sensor.getSensorChannels()) {

                    if (condition.getLeftSideOp().getSensorChannel().equals(channel)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    /**
     * @return list of all sensors that are preselected but no longer available
     * in sensor server list of displayconfiguration
     */
    public ArrayList<Sensor> getInvalidSensors() {
        ArrayList<Sensor> result = new ArrayList<Sensor>();

        ArrayList<SensorServer> servers = (ArrayList<SensorServer>) peripheral.logic.DisplayConfiguration.getInstance().getSensorServer();

        for (Sensor sensor : this.preselectedSensors) {

            boolean foundFlag = false;

            for (SensorServer server : servers) {
                if (server.getSensorList().contains(sensor)) {
                    foundFlag = true;
                }
            }

            if (!foundFlag) {
                result.add(sensor);
            }
        }

        return result;
    }

    /**
     *
     * @param sensor
     * @return whether given sensor (that should be one of the preselected sensors) is invalid
     */
    public boolean isSensorInvalid(Sensor sensor) {
        return getInvalidSensors().contains(sensor);
    }

    /**
     * Method that removes given sensors from preselection list and resets all
     * affected user inputs
     * @param noLongerAvailableSensors list with no longer available sensors
     */
    public void removeSensorsWithDependencies(ArrayList<Sensor> sensorList) {

        //first remove corrupt sensors from preselection list
        for (Sensor sensor : sensorList) {
            this.preselectedSensors.remove(sensor);
        }

        //second reset all affected userinputs to dummy value
        resetCorruptUserInputs(sensorList);

        //third reset all affected conditions in rules
        this.resetCorruptConditions(sensorList);
    }

    /**
     * Takes a list of sensors that are no longer available and resets all its
     * user inputs with SensorValues to the default dummy sensorchannel
     * @param noLongerAvailableSensors
     */
    private void resetCorruptUserInputs(ArrayList<Sensor> noLongerAvailableSensors) {

        for (UserInput userInput : neededUserInput) {

            //iterate through all user inputs with sensorvalues
            if (userInput.getValue() instanceof SensorValue) {
                SensorValue sensorValue = (SensorValue) userInput.getValue();

                //iterate through all sensorchannels of this sensor
                for (Sensor sensor : noLongerAvailableSensors) {
                    for (SensorChannel channel : sensor.getSensorChannels()) {

                        if (sensorValue.getSensorChannel().equals(channel)) {

                            //reset the value of this sensorvalue to dummy channel
                            sensorValue.setSensorChannel(SensorChannel.getDummy());
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to return all values of userinputs with invalid values
     * GUI checks whether returned list is empty to allow user to proceed
     * @return list with all invalid values of userinputs
     */
    public ArrayList<Value> getInvalidUserInputValues() {

        ArrayList<Value> result = new ArrayList<Value>();

        for (UserInput userInput : this.neededUserInput) {

            if (!userInput.isValueValid()) {
                result.add(userInput.getValue());
            }
        }

        return result;
    }

    /**
     * @return all conditions of set rules that contain invalid conditions
     * at this time they are checked for invalid sensors
     */
    public ArrayList<Condition> getInvalidConditions() {

        ArrayList<Condition> result = new ArrayList<Condition>();

        for (Rule rule : this.rules) {
            result.addAll(rule.getInvalidConditions());
        }

        return result;
    }

    /**
     * Method to set all conditions of rules containing an no longer available
     * sensor to sensor channel DUMMY
     * @param invalidSensors
     */
    public void resetCorruptConditions(ArrayList<Sensor> invalidSensors) {

        for (Rule rule : rules) {

            for (Condition condition : rule.getConditions()) {

                for (Sensor sensor : invalidSensors) {
                    for (SensorChannel channel : sensor.getSensorChannels()) {

                        if (condition.getLeftSideOp().getSensorChannel().equals(channel)) {

                            condition.getLeftSideOp().setSensorChannel(SensorChannel.getDummy());
                        }
                    }
                }
            }
        }
    }

    public ActionToolAction getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(ActionToolAction val) {
        this.defaultAction = val;
    }

    public java.util.ArrayList<Sensor> getPreselectedSensors() {
        return preselectedSensors;
    }

    public Map<RequiredStep, Boolean> getRequiredSteps() {
        return requiredSteps;
    }

    public List<ActionToolAction> getInitActions() {
        return initActions;
    }

    public List<ActionToolAction> getSensorFailureActions() {
        return sensorFailureActions;
    }

    /*public void save (ObjectOutputStream os) {
    }*/
    public String toString() {
        //returns name to be displayed in Animation list
        return this.name;
    }

    public void execute() {
        boolean ruleExecuted = false;
        Rule defaultRule = null;

        if (sensorFailure) {
            executeInitActions();
        }

        for (SensorValueFilter svf : getSensorValueFilter()){
            svf.doFilter();
        }

        for (Filter bf : getBeforeFilter()) {
            bf.doFilter();
        }

        for (Rule r : getRules()) {
            if (r instanceof DefaultRule) {
                defaultRule = r;
            } else {
                if (r.tryExecute()) {
                    ruleExecuted = true;
                    break;
                }
            }
        }

        if (!ruleExecuted) {
            if (defaultRule != null){
                defaultRule.tryExecute();
            }
        }

        for (Filter af : getAfterFilter()) {
            af.doFilter();
        }
    }

    public void executeInitActions() {
        for (ActionToolAction action : initActions) {
            action.execute(tool);
        }
        sensorFailure = false;
    }

    public void executeSensorFailureActions() {
        if (!sensorFailure) {
            for (ActionToolAction action : sensorFailureActions) {
                action.execute(tool);
            }
            sensorFailure = true;
        }
    }
}

