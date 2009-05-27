package peripheral.logic.positioningtool;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import peripheral.logic.symboladapter.Symbol; 

public class Region extends PositioningTool {

    private Rectangle bounds;

    private java.util.Set<Symbol> userSelectedSymbols;

    private boolean hidden;

    /**
     *  <p style="margin-top: 0">
     *    
     *      </p>
     */
    public Region () {
        super();
    }

    public Rectangle getBounds () {
        return bounds;
    }

    public void setBounds (Rectangle val) {
        this.bounds = val;
    }

    public void draw (java.awt.Graphics g) {
    }

    public java.util.Set<Symbol> getUserSelectedSymbols () {
        return userSelectedSymbols;
    }

    public String toString() {
        return "Region";
    }

    /**
     *  <p style="margin-top: 0">
     *        super.addSymbol(s);
     *  <br>userSelectedSymbols.addSymbol(s);
     *      </p>
     */
    public void addSymbol (Symbol s) {
    }

    public boolean isHidden () {
        return hidden;
    }

    public void setHidden (boolean val) {
        this.hidden = val;
    }

    /*
     * GRAPHICAL METHODS
     */

    @Override
    public void paint(Graphics g, float scale, java.awt.Rectangle imageBounds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean dragable(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dragStart(java.awt.Point origin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dragAction(java.awt.Point newPosition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dragStop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

