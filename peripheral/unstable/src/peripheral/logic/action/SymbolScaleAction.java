package peripheral.logic.action;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class SymbolScaleAction extends SymbolAction {

    private Value factorX;
    private Value factorY;

    public SymbolScaleAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolScaleAction(SymbolAdapter adapter, Value factorX, Value factorY) {
        this(adapter);
        this.factorX = factorX;
        this.factorY = factorY;
    }

    public float getFactorX() {
        //extract concrete value from Value-Object
        return Float.parseFloat(factorX.getValue().toString());
    }

    public float getFactorY() {
        //extract concrete value from Value-Object
        return Float.parseFloat(factorY.getValue().toString());
    }

    public java.util.List<UserInput> getUserInput() {
        List<UserInput> ui = new ArrayList<UserInput>();
        if (factorX == null) {
            ui.add(new UserInput("Factor x-Axis", "Factor to which the symbol should be scaled on the x-axis.", new ConstValue(adapter, "factorX", 1.0f, float.class)));
        }
        if (factorY == null){
            ui.add(new UserInput("Factor x-Axis", "Factor to which the symbol should be scaled on the y-axis.", new ConstValue(adapter, "factorY", 1.0f, float.class)));
        }
        return ui;
    }

    public String getDescription() {
        return "Scales a symbol according to two given scaling factors. One for the x- and one for the y-axis.";
    }


    /**
     * calls scale on visualization
     * visualization has to update the symbols according to the new factors
     *
     * @param s
     */
    public void execute(Symbol s) {
        peripheral.logic.Runtime.getInstance().getVisualization().scaleSymbol(s, getFactorX(), getFactorY());
    }
}

