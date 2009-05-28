package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

/**
 *  <p style="margin-top: 0">
 *    Visualization.rotate(s, angle);<br>s.setAngle(angle);
 *      </p>
 *      <p style="margin-top: 0">
 *    <br>
 *      </p>
 */
public class SymbolRotateAction extends SymbolAction {

    private Value angle;

    public SymbolRotateAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolRotateAction (SymbolAdapter adapter, Value angle) {
        this(adapter);
        this.angle = angle;
    }

    public float getAngle () {
        return 0;
        //extract concrete value from Value-Object
        //return angle;
    }

    public java.util.List<UserInput> getUserInput () {
        return null;
    }

    public String getName () {
        return null;
    }

    public String getDescription () {
        return null;
    }

    public void execute (Symbol s) {
    }

}

