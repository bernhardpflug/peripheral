package peripheral.logic.action;

import peripheral.logic.positioningtool.Point; 
import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class SymbolTranslateAction extends SymbolAction {

    private Value targetPosition;

    public SymbolTranslateAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolTranslateAction (SymbolAdapter adapter, Value targetPosition) {
        this(adapter);
        this.targetPosition = targetPosition;
    }

    public Point getTargetPosition () {
        return null;
        //extract targetPosition (Point) from targetPosition (Value)
        //return targetPosition;
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

    /**
     *  <p style="margin-top: 0">
     *        Visualization.translate(s, targetPosition);
     *  <br>s.setPosition(targetPosition);
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *      </p>
     */
    public void execute (Symbol s) {
    }

}

