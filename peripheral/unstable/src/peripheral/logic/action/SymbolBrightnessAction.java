package peripheral.logic.action;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class SymbolBrightnessAction extends SymbolAction {

    private Value amount;
    private boolean needAmountUserInput = true;

    public SymbolBrightnessAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolBrightnessAction(SymbolAdapter adapter, Value amount) {
        this(adapter);
        this.amount = amount;
        this.needAmountUserInput = false;
    }

    public float getAmount() {
        //extract concrete value from Value-Object
        return Float.parseFloat(amount.getValue().toString());
    }

    @Override
    public java.util.List<UserInput> getUserInput() {
        if (this.needAmountUserInput) {
            if (amount == null) {
                amount = new ConstValue(adapter, "amount", 1.0f, float.class);
                userInput.add(new UserInput("Amount", "Amount of brightness ...?", amount));
            }
        }
        return userInput;
    }

    public String getDescription() {
        return "Changes the brightness of a symbol to a specified amount.";
    }

    public void execute(Symbol s) {
        peripheral.logic.Runtime.getInstance().getVisualization().brightness(s, getAmount());
    }
}

