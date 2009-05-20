package peripheral.logic.positioningtool;

import peripheral.logic.datatype.Rectangle; 
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

}

