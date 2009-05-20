package peripheral.logic.positioningtool;

import peripheral.logic.symboladapter.Symbol; 

public abstract class PositioningTool implements ActionTool {

    private java.util.Set<Symbol> symbols;

    public PositioningTool () {
    }

    /**
     *  <p style="margin-top: 0">
     *    symbols.add(s);
     *      </p>
     */
    public void addSymbol (Symbol s) {
    }

    public java.util.Set<Symbol> getSymbols () {
        return symbols;
    }

    public void calculateRelativeCoordinates () {
    }

}

