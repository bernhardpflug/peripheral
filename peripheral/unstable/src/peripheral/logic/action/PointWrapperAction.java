package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.value.UserInput;

public abstract class PointWrapperAction extends PointAction {

    private SymbolAction symbolAction;

    public SymbolAction getSymbolAction() {
        return symbolAction;
    }

    private PointWrapperAction (SymbolAction symbolAction) {
    }

    /**
     *  <p style="margin-top: 0">
     *    return symbolAction.getUserInput();
     *      </p>
     */
    public java.util.Set<UserInput> getUserInput () {
        return null;
    }

    public abstract String getName ();

    public abstract String getDescription ();

    /**
     *  <p style="margin-top: 0">
     *        symbolAction.execute (((Point)tool).getActSymbol());
     *      </p>
     */
    public void execute (ActionTool tool) {
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
    public static PointWrapperAction getPointWrapperAction (SymbolAction symbolAction) {
        return new PointWrapperAction(symbolAction){
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

