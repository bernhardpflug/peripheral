package peripheral.logic.symboladapter;

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
    private java.util.Set<Sensor> preselectedSensors;
    private Map<RequiredStep, Boolean> requiredSteps;
    private Action defaultAction;

    private List<ActionToolAction> initActions;

    public SymbolAdapter() {

        beforeFilter = new ArrayList<Filter>();
        afterFilter = new ArrayList<Filter>();

        rules = new ArrayList<Rule>();

        varpool = new java.util.HashMap<String, Value>();

        neededUserInput = new ArrayList<UserInput>();

        preselectedSensors = new java.util.HashSet<Sensor>();

        requiredSteps = new java.util.HashMap<RequiredStep, Boolean>();

        initActions = new ArrayList<ActionToolAction>();
    }

    public java.util.List<Filter> getAfterFilter() {
        return afterFilter;
    }

    public SymbolAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(SymbolAnimator val) {
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

    public Action getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(Action val) {
        this.defaultAction = val;
    }

    public java.util.Set<Sensor> getPreselectedSensors() {
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

    public void executeInitActions(){
        for (ActionToolAction action : initActions){
            action.execute(tool);
        }
    }
}

