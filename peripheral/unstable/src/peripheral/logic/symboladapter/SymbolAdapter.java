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
import peripheral.logic.action.Action;
import peripheral.logic.action.ActionToolAction;
import peripheral.logic.filter.Filter;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.rule.Rule;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.value.SensorValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class SymbolAdapter implements Serializable {

    public enum RequiredStep implements Serializable {

        Rules
    }
    private ActionTool tool;
    private java.util.List<Filter> beforeFilter;
    private java.util.List<Filter> afterFilter;
    private java.util.List<Rule> rules;
    private java.util.Map<String, Value> varpool;
    private java.util.List<UserInput> neededUserInput;
    private String name;
    private String description;
    private SymbolAnimator animator;
    private java.util.ArrayList<Sensor> preselectedSensors;
    private Map<RequiredStep, Boolean> requiredSteps;
    private Action defaultAction;
    private List<ActionToolAction> initActions;

    //defines whether symbols of the positioningtools of this adapter
    //may add a second file for a symbol if the symbol changes its direction
    //in the animation
    private boolean allowOrientedSymbols;

    public SymbolAdapter() {

        beforeFilter = new ArrayList<Filter>();
        afterFilter = new ArrayList<Filter>();

        rules = new ArrayList<Rule>();

        varpool = new java.util.HashMap<String, Value>();

        neededUserInput = new ArrayList<UserInput>();

        preselectedSensors = new java.util.ArrayList<Sensor>();

        requiredSteps = new java.util.HashMap<RequiredStep, Boolean>();

        initActions = new ArrayList<ActionToolAction>();

        allowOrientedSymbols = false;
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

    public Action getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(Action val) {
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

    /*public void save (ObjectOutputStream os) {
    }*/
    public String toString() {
        //returns name to be displayed in Animation list
        return this.name;
    }

    public void execute() {
        for (Filter bf : getBeforeFilter()) {
            bf.doFilter();
        }

        for (Rule r : getRules()) {
            if (r.tryExecute()) {
                break;
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
    }
}

