package peripheral.logic.positioningtool;


public class Line extends Point {

    private Point startPoint;

    private Point endPoint;

    public Line () {
    }

    public void draw (java.awt.Graphics g) {
    }

    public Point getEndPoint () {
        return endPoint;
    }

    public void setEndPoint (Point val) {
        this.endPoint = val;
    }

    public Point getStartPoint () {
        return startPoint;
    }

    public void setStartPoint (Point val) {
        this.startPoint = val;
    }

}

