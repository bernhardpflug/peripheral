package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
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

    public SymbolRotateAction () {
    }

    public float getAngle () {
        return 0;
        //extract concrete value from Value-Object
        //return angle;
    }

    public java.util.Set<UserInput> getUserInput () {
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

    public SymbolRotateAction (Value angle) {

    }

}

