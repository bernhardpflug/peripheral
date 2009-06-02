package peripheral.logic.action;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class SymbolTranslateAction extends SymbolAction {

    private Value targetPositionX;
    private Value targetPositionY;
    private Value targetPosition;
    private boolean needTargetPositionXUserInput = true;
    private boolean needTargetPositionYUserInput = true;

    public SymbolTranslateAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolTranslateAction(SymbolAdapter adapter, Value targetPositionX, Value targetPositionY) {
        this(adapter);
        this.targetPositionX = targetPositionX;
        this.targetPositionY = targetPositionY;

        this.needTargetPositionXUserInput = false;
        this.needTargetPositionYUserInput = false;
    }

    public SymbolTranslateAction(SymbolAdapter adapter, Value targetPosition) {
        this(adapter);
        this.targetPosition = targetPosition;

        this.needTargetPositionXUserInput = false;
        this.needTargetPositionYUserInput = false;
    }

    public int getTargetPositionX() {
        //extract concrete value from Value-Object
        return Integer.parseInt(targetPositionX.getValue().toString());
    }

    public int getTargetPositionY() {
        //extract concrete value from Value-Object
        return Integer.parseInt(targetPositionY.getValue().toString());
    }

    public java.awt.Point getTargetPosition() {
        //extract concrete value from Value-Object
        return (java.awt.Point) targetPosition.getValue();
    }

    @Override
    public java.util.List<UserInput> getUserInput() {
        if (this.needTargetPositionXUserInput) {
            if (targetPositionX == null) {
                targetPositionX = new ConstValue(adapter, "targetPositionX", 0, int.class);
                userInput.add(new UserInput("Target position x", "x-value to which the symbol should be moved.", targetPositionX));
            }
        }
        if (this.needTargetPositionYUserInput) {
            if (targetPositionY == null) {
                targetPositionY = new ConstValue(adapter, "targetPositionY", 0, int.class);
                userInput.add(new UserInput("Target position y", "y-value to which the symbol should be moved.", targetPositionY));
            }
        }
        return userInput;
    }

    public String getDescription() {
        return "Moves a symbol to a specified target position.";
    }

    /**
     * calls translate on visualization
     * visualization has to update the symbol according to the new position
     *
     * @param s
     */
    public void execute(Symbol s) {
        peripheral.logic.Runtime.getInstance().getVisualization().translateSymbol(s, new Point(getTargetPositionX(), getTargetPositionY()));
    }
}

