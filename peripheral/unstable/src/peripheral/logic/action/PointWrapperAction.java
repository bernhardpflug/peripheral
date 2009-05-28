package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.positioningtool.Point;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public abstract class PointWrapperAction extends PointAction {

    private SymbolAction symbolAction;

    public SymbolAction getSymbolAction() {
        return symbolAction;
    }

    private PointWrapperAction (SymbolAdapter adapter, SymbolAction symbolAction) {
        super(adapter);
        this.symbolAction = symbolAction;
    }

    /**
     *  <p style="margin-top: 0">
     *    return symbolAction.getUserInput();
     *      </p>
     */
    public java.util.List<UserInput> getUserInput () {
        return null;
    }

    public abstract String getName ();

    public abstract String getDescription ();

    /**
     *  <p style="margin-top: 0">
     *        symbolAction.execute ();
     *      </p>
     */
    public void execute (ActionTool tool) {
        symbolAction.execute(((Point)tool).getActSymbol());
    }

    /**
     *  <p style="margin-top: 0">
     *    returns anonymous class based on the passed SymbolAction.
     *  <br>
     *  <br>e.g.:
     *  <br>return new PointWrapperAction(symbolAction){
     *  <br>  public String getName(){...}
     *  <br>  public String getDescription(){...<br>}
     *      </p>
     */
    public static PointWrapperAction getPointWrapperAction (SymbolAdapter adapter, SymbolAction symbolAction) {
        return new PointWrapperAction(adapter, symbolAction){
            @Override
            public String getDescription() {
                return this.getSymbolAction().getDescription();
            }

            @Override
            public String getName() {
                return this.getSymbolAction().getName();
            }

        };
    }

    public void update (Observable o, Object arg) {
    }

}

