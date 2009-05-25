package peripheral.logic.positioningtool;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import peripheral.logic.symboladapter.Symbol; 

public abstract class PositioningTool implements ActionTool {

    protected java.util.List<Symbol> symbols;

    //graphical flag that defines whether this tool displays one of its defined symbols
    private boolean symbolDisplayed = false;


    public PositioningTool () {
        symbols = new ArrayList<Symbol>();

    }

    public java.util.List<Symbol> getSymbols () {
        return symbols;
    }

    public void calculateRelativeCoordinates () {
    }

    /*
     * GRAPHICAL METHODS
     */

    public void setSymbolDisplayed(boolean flag) {
        symbolDisplayed = flag;
    }

    public boolean getSymbolDisplayed() {
        return symbolDisplayed;
    }

    /**
     * Every positioning tool has to be able to paint itself on the preview
     * @param g graphics object of the panel
     * @param scale to be aware of different scaling
     * @param imageBounds to draw component from image origin coordinates
     */
    public abstract void paint(Graphics g, float scale, Rectangle imageBounds);

    /**
     * Returns whether this position tool has dragpoint on this position
     * @param x position in background image coordinate
     * @param y position in background image coordinate
     * @return true if drag point lies on this position
     */
    public abstract boolean dragable(int x, int y);

    /**
     * indicates the start of a drag action
     * in here the type of drag action should be declared by the position tool
     * e.g. movement of the whole component or width/height de-/increasing
     * Further due to selection graphical changes (bold, color...) can be made
     * @param origin coordinates in background image coordinates
     */
    public abstract void dragStart(java.awt.Point origin);

    /**
     * Called everytime the position of a currently ongoing drag operation
     * changes
     * @param newPosition current mouse position in background coordinates
     */
    public abstract void dragAction (java.awt.Point newPosition);

    /**
     * Indicates the end of the drag action
     */
    public abstract void dragStop();

}

