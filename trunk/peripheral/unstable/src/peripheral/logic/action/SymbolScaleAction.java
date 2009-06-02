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
    private boolean needFactorXUserInput = true;
    private boolean needFactorYUserInput = true;

    public SymbolScaleAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolScaleAction(SymbolAdapter adapter, Value factorX, Value factorY) {
        this(adapter);
        this.factorX = factorX;
        this.factorY = factorY;

        needFactorXUserInput = false;
        needFactorYUserInput = false;
    }

    public float getFactorX() {
        //extract concrete value from Value-Object
        return Float.parseFloat(factorX.getValue().toString());
    }

    public float getFactorY() {
        //extract concrete value from Value-Object
        return Float.parseFloat(factorY.getValue().toString());
    }

    @Override
    public java.util.List<UserInput> getUserInput() {
        if (this.needFactorXUserInput) {
            if (factorX == null) {
                factorX = new ConstValue(adapter, "factorX", 1.0f, float.class);
                userInput.add(new UserInput("Factor x-Axis", "Factor to which the symbol should be scaled on the x-axis.", factorX));
            }
        }
        if (this.needFactorYUserInput) {
            if (factorY == null) {
                factorY = new ConstValue(adapter, "factorY", 1.0f, float.class);
                userInput.add(new UserInput("Factor x-Axis", "Factor to which the symbol should be scaled on the y-axis.", factorY));
            }
        }
        return userInput;
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

