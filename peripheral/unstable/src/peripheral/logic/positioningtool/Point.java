package peripheral.logic.positioningtool;

import peripheral.logic.symboladapter.Symbol; 

public class Point extends PositioningTool {

    private Point position;

    private Symbol actSymbol;

    public Point () {
    }

    public Point getPosition () {
        return position;
    }

    public void setPosition (Point val) {
        this.position = val;
    }

    public void draw (java.awt.Graphics g) {
    }

    public Symbol getActSymbol () {
        return actSymbol;
    }

    public void setActSymbol (Symbol val) {
        this.actSymbol = val;
    }

}

