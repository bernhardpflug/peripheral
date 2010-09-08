package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.positioningtool.Point;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public class PointWrapperAction extends PointAction {

    private SymbolAction symbolAction;

    public SymbolAction getSymbolAction() {
        return symbolAction;
    }

    public PointWrapperAction (SymbolAdapter adapter, SymbolAction symbolAction) {
        super(adapter);
        this.symbolAction = symbolAction;
    }

    public java.util.List<UserInput> getUserInput () {
        return symbolAction.getUserInput();
    }

    @Override
    public String getName (){
        return symbolAction.getName();
    }

    @Override
    public String getDescription (){
        return symbolAction.getDescription();
    }

    @Override
    public void execute (ActionTool tool) {
        Point p = (Point)tool;

        if (p.getActSymbol() == null){
            p.getSymbols().add(new Symbol(null, p, adapter));
        }

        symbolAction.execute(p.getActSymbol());
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
    /*public static PointWrapperAction getPointWrapperAction (SymbolAdapter adapter, SymbolAction symbolAction) {
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
    }*/

    public void update (Observable o, Object arg) {
    }

}

