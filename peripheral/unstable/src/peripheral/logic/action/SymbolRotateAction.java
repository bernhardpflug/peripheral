package peripheral.logic.action;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class SymbolRotateAction extends SymbolAction {

    private Value angle;
    private boolean needAngleUserInput = true;

    public SymbolRotateAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolRotateAction(SymbolAdapter adapter, Value angle) {
        this(adapter);
        this.angle = angle;
        this.needAngleUserInput = false;
    }

    public float getAngle() {
        //extract concrete value from Value-Object
        return Float.parseFloat(angle.getValue().toString());
    }

    @Override
    public java.util.List<UserInput> getUserInput() {
        if (this.needAngleUserInput) {
            if (angle == null) {
                angle = new ConstValue(adapter, "angle", 0.0f, Float.class);
                userInput.add(new UserInput("Angle", "Angle to which the symbol should be rotated.", angle));
            }
        }
        return userInput;
    }

    public String getDescription() {
        return "Rotates a symbol to a specified angle";
    }

    /**
     * calls rotate on visualization
     * visualization has to update the symbol according to the new angle
     *
     * @param s
     */
    public void execute(Symbol s) {
        peripheral.logic.Runtime.getInstance().getVisualization().rotateSymbol(s, getAngle());
    }
}

