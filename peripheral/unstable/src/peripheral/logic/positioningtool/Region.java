package peripheral.logic.positioningtool;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import peripheral.logic.symboladapter.Symbol; 

public class Region extends PositioningTool {

    //defines the right lower area of the rectangle that supports dragging
    private static final int DRAGAREA = 50;
    //defines the minimum relation between rectangle size and DRAGAREA so that dragging is allowed
    private static final int MIN_DRAGSIZE = 2;

    private enum DragOption {
        resize,
        move
    }

    private DragOption currentOption;

    //offset between origin of symbol and current mouse cursor
    //(otherwise first drag creates a jump if click was far away from origin)
    private java.awt.Point dragOffset;


    private Rectangle bounds;

    private java.util.Set<Symbol> userSelectedSymbols;

    private boolean hidden;

    public Region () {
        super();

        bounds = new Rectangle();
        bounds.x = 100;
        bounds.y = 100;
        bounds.width = 500;
        bounds.height = 500;

        dragOffset = new java.awt.Point();
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
    public void paint(Graphics g, float scale) {

        int scaledStartX = (int)((float)bounds.x*scale);
        int scaledStartY = (int)((float)bounds.y*scale);
        int scaledEndX = (int)((float)(bounds.x+bounds.width)*scale);
        int scaledEndY = (int)((float)(bounds.y+bounds.height)*scale);
        int scaledWidth = scaledEndX - scaledStartX;
        int scaledHeight = scaledEndY - scaledStartY;

        //in case of painting symbol paint for symbol
        if (super.getDisplayedSymbol()!=null && super.isSymbolDisplayed()) {

            Graphics imageGraphics = g.create(scaledStartX, scaledStartY, scaledWidth, scaledHeight);
            Symbol symbol = super.getDisplayedSymbol();

            //paint symbol with scale and imageBounds

            BufferedImage symbolImage = symbol.getBufferedImage();

            //set origin to zero/zero as seperate graphics object is used
            Rectangle symbolBounds = getSymbolBounds(symbolImage, scale, new java.awt.Point(0, 0));

            imageGraphics.drawImage(symbolImage, (int) symbolBounds.getX(), (int) symbolBounds.getY(), (int) symbolBounds.getX() + (int) symbolBounds.getWidth(), (int) symbolBounds.getY() + (int) symbolBounds.getHeight(),
                    0, 0, symbolImage.getWidth(), symbolImage.getHeight(), null, null);

            imageGraphics.dispose();
        }

        //draw rectangle for region
        g.drawRect(scaledStartX, scaledStartY, scaledWidth, scaledHeight);

        //draw cross for dragging area

        //only allow dragging if rectangle is at minimum double as large as dragging area
        if (bounds.width > DRAGAREA * MIN_DRAGSIZE && bounds.height > DRAGAREA * MIN_DRAGSIZE) {

            //draw cross in area
            int lowerX = scaledEndX - (int)(DRAGAREA*scale);
            int lowerY = scaledEndY - (int)(DRAGAREA*scale);
            int higherX = scaledEndX;
            int higherY = scaledEndY;

            g.drawLine(lowerX, lowerY, higherX, higherY);
            g.drawLine(higherX, lowerY, lowerX, higherY);
        }
    }

    @Override
    public boolean dragable(int x, int y) {

        if (internalDragable(x,y) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * internal method that determines whether resize or move option is appropriate
     * @param x
     * @param y
     * @return
     */
    private DragOption internalDragable(int x, int y) {

        //first check whether in resize area (which is right lower part of the rectangle
        if (x >= bounds.x + bounds.width - DRAGAREA && x <= bounds.x + bounds.width && y >= bounds.y + bounds.height - DRAGAREA && y <= bounds.y +bounds.height) {
            return DragOption.resize;
        }
        //if not in resize area check for whole bounds
        else if (x >= bounds.x && x <= bounds.x + bounds.width && y >= bounds.y && y <= bounds.y + bounds.height) {
            return DragOption.move;
        }
        else {
            return null;
        }
    }

    @Override
    public void dragStart(java.awt.Point origin) {

        if (currentOption == null) {
            currentOption = internalDragable(origin.x, origin.y);

            if (currentOption != null) {

                if (currentOption == DragOption.move) {
                    this.dragOffset.x = origin.x - bounds.x;
                    this.dragOffset.y = origin.y - bounds.y;
                }
                else {
                    this.dragOffset.x = bounds.x + bounds.width - origin.x;
                    this.dragOffset.y = bounds.y + bounds.height - origin.y;
                }
            }
        }
    }

    @Override
    public void dragAction(java.awt.Point newPosition) {

        if (currentOption != null) {

            if (currentOption == DragOption.move) {
                bounds.x = newPosition.x - dragOffset.x;
                bounds.y = newPosition.y - dragOffset.y;
            }
            else {
                
                int checkWidth = newPosition.x - bounds.x + dragOffset.x;
                int checkHeight = newPosition.y - bounds.y + dragOffset.y;

                if (checkWidth > 0) {
                    bounds.width = checkWidth;
                }

                if (checkHeight > 0) {
                    bounds.height = checkHeight;
                }
            }
        }
    }

    @Override
    public void dragStop() {

        currentOption = null;
    }

}

