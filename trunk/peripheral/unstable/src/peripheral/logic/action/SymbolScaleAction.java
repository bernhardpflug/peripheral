package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
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
        return 0;
        //extract concrete value from Value-Object
        //return factorX;
    }

    public float getFactorY() {
        return 0;
        //extract concrete value from Value-Object
        //return factorY;
    }

    public java.util.List<UserInput> getUserInput() {
        return null;
    }

    public String getName() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    /**
     *  <p style="margin-top: 0">
     *    Visualization.scale(s, factorX, factorY);<br>s.setScaleX(factorX);
     *  <br>s.setScaleY(factorY);
     *      </p>
     *      <p style="margin-top: 0">
     *    
     *      </p>
     *      <p style="margin-top: 0">
     *    <br>
     *      </p>
     */
    public void execute(Symbol s) {
    }
}

