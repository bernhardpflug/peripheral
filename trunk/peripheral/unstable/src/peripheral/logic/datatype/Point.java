package peripheral.logic.datatype;


public class Point {

    private java.awt.Point positionAbs;

    private PointF positionRel;

    public Point () {
    }

    public java.awt.Point getPositionAbs () {
        return positionAbs;
    }

    /**
     *  <p style="margin-top: 0">
     *    set abs position and calculate relative position
     *  <br>
     *  <br>Display width and height via DisplayConfiguration.getInstance().getWidth();
     *      </p>
     */
    public void setPositionAbs (java.awt.Point val) {
        this.positionAbs = val;
    }

    public PointF getPositionRel () {
        return positionRel;
    }

    /**
     *  <p style="margin-top: 0">
     *    set rel position and calculate absolute position
     *      </p>
     */
    public void setPositionRel (PointF val) {
        this.positionRel = val;
    }

}

