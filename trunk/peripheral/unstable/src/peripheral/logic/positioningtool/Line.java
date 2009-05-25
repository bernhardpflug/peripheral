package peripheral.logic.positioningtool;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Line extends PositioningTool {

    private java.awt.Point startPoint;

    private java.awt.Point endPoint;

    public Line (Dimension backgroundDimension) {
        super();
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

