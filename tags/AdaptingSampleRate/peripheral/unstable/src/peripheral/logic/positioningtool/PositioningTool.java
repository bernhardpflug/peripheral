package peripheral.logic.positioningtool;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.Symbol; 

public abstract class PositioningTool implements ActionTool {

    protected java.util.List<Symbol> symbols;

    //reference to symbol to paint
    private Symbol displayedSymbol;

    private boolean displaySymbol;

    private boolean visible = true;

    public PositioningTool () {
        symbols = new ArrayList<Symbol>();

    }

    public java.util.List<Symbol> getSymbols () {
        return symbols;
    }

    public void calculateRelativeCoordinates () {
    }

    //@Override
    public List<PositioningTool> getElements() {
        ArrayList list = new ArrayList(1);
        list.add(this);
        return list;
    }

    /*
     * GRAPHICAL METHODS
     */

    public void setDisplayedSymbol(Symbol symbol) {
        displayedSymbol = symbol;
    }

    public Symbol getDisplayedSymbol() {
        return displayedSymbol;
    }

    public boolean isSymbolDisplayed() {
        return displaySymbol;
    }

    public void setSymbolDisplayed(boolean flag) {
        this.displaySymbol = flag;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * calculate scaled bounds of given symbol image with respect to
     * background image origin in panel
     * @param symbolImage buffered image containing the symbol
     * @param scale of the current szene
     * @param origin of the symbol
     * @return rectangle containing the bounds of the symbol
     */
    protected Rectangle getSymbolBounds(BufferedImage symbolImage,float scale,java.awt.Point origin) {

        Rectangle symbolBounds = new Rectangle();

        int ImgW = (int)((float)symbolImage.getWidth()*scale);
        int ImgH = (int)((float)symbolImage.getHeight()*scale);

        int NorthWestCornerX = (int)((float)origin.x*scale);
        int NorthWestCornerY = (int)((float)origin.y*scale);

        symbolBounds.setBounds(NorthWestCornerX, NorthWestCornerY, ImgW, ImgH);

        return symbolBounds;
    }

    /**
     * Every positioning tool has to be able to paint itself on the preview
     * @param g graphics object of the panel
     * @param scale to be aware of different scaling
     * @param imageBounds to draw component from image origin coordinates
     */
    public abstract void paint(Graphics g, float scale);

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

