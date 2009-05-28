package peripheral.logic.action;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class SymbolContrastAction extends SymbolAction {

    private Value amount;

    public SymbolContrastAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolContrastAction(SymbolAdapter adapter, Value amount) {
        this(adapter);
        this.amount = amount;
    }

    public float getAmount() {
        //extract concrete value from Value-Object
        return Float.parseFloat(amount.getValue().toString());
    }

    public java.util.List<UserInput> getUserInput() {
        List<UserInput> ui = new ArrayList<UserInput>();
        if (amount == null) {
            ui.add(new UserInput("Amount", "Amount of contrast ...?", new ConstValue(adapter, "amount", 1.0f, float.class)));
        }
        return ui;
    }

    public String getDescription() {
        return "Changes the contrast of a symbol to a specified amount.";
    }

    public void execute(Symbol s) {
        peripheral.logic.Runtime.getInstance().getVisualization().contrast(s, getAmount());
    }
}

