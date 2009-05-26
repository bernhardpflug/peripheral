package peripheral.logic.positioningtool;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Line extends PositioningTool {

    private java.awt.Point startPoint;

    private java.awt.Point endPoint;

    private static final int CROSSRANGE = 10;

    public Line () {
        super();

        startPoint = new java.awt.Point();
        endPoint = new java.awt.Point();

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

        }
        else {
            //paint line with crosses at begin and endpoint

            //draw line
            g.drawLine(scaledStartX, scaledStartY, scaledEndX, scaledEndY);

            //draw cross startPoint
            g.drawLine(scaledStartX-(int)(CROSSRANGE*scale), scaledStartY-(int)(CROSSRANGE*scale), scaledStartX+(int)(CROSSRANGE*scale), scaledStartY+(int)(CROSSRANGE*scale));
            g.drawLine(scaledStartX-(int)(CROSSRANGE*scale), scaledStartY+(int)(CROSSRANGE*scale), scaledStartX+(int)(CROSSRANGE*scale), scaledStartY-(int)(CROSSRANGE*scale));

            //draw cross endPoint
            g.drawLine(scaledEndX-(int)(CROSSRANGE*scale), scaledEndY-(int)(CROSSRANGE*scale), scaledEndX+(int)(CROSSRANGE*scale), scaledEndY+(int)(CROSSRANGE*scale));
            g.drawLine(scaledEndX-(int)(CROSSRANGE*scale), scaledEndY+(int)(CROSSRANGE*scale), scaledEndX+(int)(CROSSRANGE*scale), scaledEndY-(int)(CROSSRANGE*scale));

        }
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

