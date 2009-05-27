package peripheral.logic.positioningtool;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import peripheral.logic.symboladapter.Symbol;


public class Line extends PositioningTool {

    private java.awt.Point startPoint;

    private java.awt.Point endPoint;

    private static final int CROSSRANGE = 10;
    
    //reference to either start- or endpoint to define point for manipulation
    private java.awt.Point draggingPoint;

    //offset between origin of symbol and current mouse cursor
    //(otherwise first drag creates a jump if click was far away from origin)
    private java.awt.Point dragOffset;

    public Line () {
        super();

        startPoint = new java.awt.Point();
        endPoint = new java.awt.Point();
        dragOffset = new java.awt.Point();

        startPoint.x = 6;
        startPoint.y = 6;

        endPoint.x = 10;
        endPoint.y = 10;
    }

    public void draw (java.awt.Graphics g) {
    }

    public java.awt.Point getEndPoint () {
        return endPoint;
    }

    public void setEndPoint (java.awt.Point val) {
        this.endPoint = val;
    }

    public java.awt.Point getStartPoint () {
        return startPoint;
    }

    public void setStartPoint (java.awt.Point val) {
        this.startPoint = val;
    }

    public String toString() {
        return "Line";
    }
    /*
     * GRAPHICAL METHODS
     */

    @Override
    public void paint(Graphics g, float scale, Rectangle imageBounds) {

        int scaledStartX = imageBounds.x + (int)((float)startPoint.x*scale);
        int scaledStartY = imageBounds.y + (int)((float)startPoint.y*scale);
        int scaledEndX = imageBounds.x + (int)((float)endPoint.x*scale);
        int scaledEndY = imageBounds.y + (int)((float)endPoint.y*scale);

        //in case of painting symbol paint for symbol
        if (super.getDisplayedSymbol()!=null && super.isSymbolDisplayed()) {

            Symbol symbol = super.getDisplayedSymbol();

            //paint symbol with scale and imageBounds

            BufferedImage symbolImage = symbol.getBufferedImage();

            //ATTENTION: IF CHANGING DISPLAY FROM STARTPOINT HERE ALSO CHANGE IN DRAGABLE
            Rectangle symbolBounds = getSymbolBounds(symbolImage, scale, startPoint, imageBounds);

            g.drawImage(symbolImage, (int) symbolBounds.getX(), (int) symbolBounds.getY(), (int) symbolBounds.getX() + (int) symbolBounds.getWidth(), (int) symbolBounds.getY() + (int) symbolBounds.getHeight(),
                    0, 0, symbolImage.getWidth(), symbolImage.getHeight(), null, null);
        }

        
        //paint line with crosses at begin and endpoint

        //draw line
        g.drawLine(scaledStartX, scaledStartY, scaledEndX, scaledEndY);

        //draw cross startPoint
        g.drawLine(scaledStartX - (int) (CROSSRANGE * scale), scaledStartY - (int) (CROSSRANGE * scale), scaledStartX + (int) (CROSSRANGE * scale), scaledStartY + (int) (CROSSRANGE * scale));
        g.drawLine(scaledStartX - (int) (CROSSRANGE * scale), scaledStartY + (int) (CROSSRANGE * scale), scaledStartX + (int) (CROSSRANGE * scale), scaledStartY - (int) (CROSSRANGE * scale));

        //draw cross endPoint
        g.drawLine(scaledEndX - (int) (CROSSRANGE * scale), scaledEndY - (int) (CROSSRANGE * scale), scaledEndX + (int) (CROSSRANGE * scale), scaledEndY + (int) (CROSSRANGE * scale));
        g.drawLine(scaledEndX-(int)(CROSSRANGE*scale), scaledEndY+(int)(CROSSRANGE*scale), scaledEndX+(int)(CROSSRANGE*scale), scaledEndY-(int)(CROSSRANGE*scale));

    }

    @Override
    public boolean dragable(int x, int y) {
        
        //if internal method returns whether start-or endpoint return true
        if (internalDragable(x,y) != null) {
            return true;
        }
        //if no point is returned drag is not possible
        else {
            return false;
        }
    }


    /**
     * internal method that checks for dragable areas and returns either the
     * start-, endpoint if dragable and null else
     * @param x background image coordinates to check drag for
     * @param y background image coordinates to check drag for
     * @return either start-endpoint if dragable or null if not
     */
    private java.awt.Point internalDragable(int x, int y) {

        //check for both startpoint and endpoint to drag

        //in case symbol is displayed size of symbol is drag area for startpoint
        if (super.getDisplayedSymbol() != null && super.isSymbolDisplayed()) {

            Symbol symbol = super.getDisplayedSymbol();

            if (x >= startPoint.x && x <= (startPoint.x + symbol.getBufferedImage().getWidth()) &&
                    y >= startPoint.y && y <= (startPoint.y + symbol.getBufferedImage().getHeight())) {

                return startPoint;
            }

        } //cross is drag area for startpoint
        else {
            //if startpoint is displayed as cross define cross range as drag area

            if (x >= startPoint.x - CROSSRANGE && x <= startPoint.x + CROSSRANGE &&
                    y >= startPoint.y - CROSSRANGE && y <= startPoint.y + CROSSRANGE) {

                return startPoint;
            }
        }

        //check endpoint (which is always represented as cross for dragable area
        if (x >= endPoint.x - CROSSRANGE && x <= endPoint.x + CROSSRANGE &&
                y >= endPoint.y - CROSSRANGE && y <= endPoint.y + CROSSRANGE) {

            return endPoint;
        }

        //if non of the above conditions is suitable area is not dragable
        return null;
    }

    @Override
    public void dragStart(java.awt.Point origin) {

        //in here remember whether start- or endpoint clicked
        if (draggingPoint == null) {

            draggingPoint = internalDragable(origin.x, origin.y);

            if (draggingPoint != null) {

                //save offset between current curser and origin
                dragOffset.x = origin.x - draggingPoint.x;
                dragOffset.y = origin.y - draggingPoint.y;
            }
        }
    }

    @Override
    public void dragAction(java.awt.Point newPosition) {

        if (draggingPoint != null) {
            this.draggingPoint.x = newPosition.x - dragOffset.x;
            this.draggingPoint.y = newPosition.y - dragOffset.y;
        }
    }

    @Override
    public void dragStop() {
        draggingPoint = null;
    }

}

