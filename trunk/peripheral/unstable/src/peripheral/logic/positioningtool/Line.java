package peripheral.logic.positioningtool;


public class Line extends Point {

    private java.awt.Point startPoint;

    private java.awt.Point endPoint;

    public Line () {
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

}

