package peripheral.logic.action;

import peripheral.logic.datatype.Percentage;
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
        if (amount.getValueType().equals(Percentage.class)) {
            //subtract -0.5 because value of Percentage is between 0.0 and 1.0 and value for
            //brightness should be between -0.5 and 0.5
            return Double.valueOf(((Percentage)amount.getValue()).getVal()).floatValue() - 0.5f;
        }
        else{
            try{
                return Float.parseFloat(amount.getValue().toString());
            }catch(NumberFormatException e){
                return 0.0f;
            }
        }
    }

    @Override
    public java.util.List<UserInput> getUserInput() {
        if (this.needAmountUserInput) {
            if (amount == null) {
                amount = new ConstValue(adapter, "amount", 0.0f, Float.class);
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

