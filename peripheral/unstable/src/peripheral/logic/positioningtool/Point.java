package peripheral.logic.positioningtool;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import peripheral.logic.symboladapter.Symbol; 

public class Point extends PositioningTool {

    private static final int CROSSRANGE = 10;

    private java.awt.Point position;

    private Symbol actSymbol;

    private boolean dragFlag = false;

    //offset between origin of symbol and current mouse cursor
    //(otherwise first drag creates a jump if click was far away from origin)
    private java.awt.Point dragOffset;

    public Point () {
        super();

        position = new java.awt.Point();
        position.x = 5;
        position.y = 5;

        dragOffset = new java.awt.Point();
    }

    public java.awt.Point getPosition () {
        return position;
    }

    public void setPosition (java.awt.Point val) {
        this.position = val;
    }

    public void draw (java.awt.Graphics g) {
    }

    public Symbol getActSymbol () {
        return getSymbols().get(0);//actSymbol;
    }

    public void setActSymbol (Symbol val) {
        this.actSymbol = val;
    }

    @Override
    public String toString() {
        return "Point [" + position.x + ", " + position.y + "]";
    }

    /*
     * GRAPHICAL METHODS
     */


    @Override
    public void paint(Graphics g, float scale) {

        //in case of painting symbol paint for symbol
        if (super.getDisplayedSymbol()!=null && super.isSymbolDisplayed()) {

            Symbol symbol = super.getDisplayedSymbol();

            //paint symbol with scale and imageBounds

            BufferedImage symbolImage = symbol.getBufferedImage();

            Rectangle symbolBounds = getSymbolBounds(symbolImage, scale, position);

            g.drawImage(symbolImage, (int) symbolBounds.getX(), (int) symbolBounds.getY(), (int) symbolBounds.getX() + (int) symbolBounds.getWidth(), (int) symbolBounds.getY() + (int) symbolBounds.getHeight(),
                    0, 0, symbolImage.getWidth(), symbolImage.getHeight(), null, null);

        }
        //else just paint a cross with the point in the middle
        else {

            int scaledPosX = (int)((float)position.x*scale);
            int scaledPosY = (int)((float)position.y*scale);

            g.drawLine(scaledPosX-(int)(CROSSRANGE*scale), scaledPosY-(int)(CROSSRANGE*scale), scaledPosX+(int)(CROSSRANGE*scale), scaledPosY+(int)(CROSSRANGE*scale));
            g.drawLine(scaledPosX-(int)(CROSSRANGE*scale), scaledPosY+(int)(CROSSRANGE*scale), scaledPosX+(int)(CROSSRANGE*scale), scaledPosY-(int)(CROSSRANGE*scale));
        }
    }

    @Override
    public boolean dragable(int x, int y) {
        
        //in case symbol is displayed size of symbol is drag area
        if (super.getDisplayedSymbol()!=null && super.isSymbolDisplayed()) {

            Symbol symbol = super.getDisplayedSymbol();
            
            if (x >= position.x && x <= (position.x + symbol.getBufferedImage().getWidth()) &&
                    y >=position.y && y <= (position.y + symbol.getBufferedImage().getHeight())) {

                return true;
            }

            return false;
        }
        else {
            //if only point is displayed (cross) allow dragging within
            //cross range
            
            if (x >= position.x -CROSSRANGE && x <= position.x + CROSSRANGE &&
                    y >= position.y - CROSSRANGE && y <= position.y + CROSSRANGE) {
                
                return true;
            }
            else {
                
                return false;
            }
        }
    }

    @Override
    public void dragStart(java.awt.Point origin) {

        if (dragable(origin.x,origin.y)) {
            //save offset between current curser and origin
            dragOffset.x = origin.x - position.x;
            dragOffset.y = origin.y - position.y;

            dragFlag = true;
        }
    }

    @Override
    public void dragAction(java.awt.Point newPosition) {

        if (dragFlag) {
            this.position.x = newPosition.x - dragOffset.x;
            this.position.y = newPosition.y - dragOffset.y;
        }
    }

    @Override
    public void dragStop() {
        dragFlag = false;
    }

}

