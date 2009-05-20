package peripheral.logic.datatype;


public class RectangleF {

    private PointF point;

    private float width;

    private float height;

    public RectangleF () {
    }

    public float getHeight () {
        return height;
    }

    public void setHeight (float val) {
        this.height = val;
    }

    public PointF getPoint () {
        return point;
    }

    public void setPoint (PointF val) {
        this.point = val;
    }

    public float getWidth () {
        return width;
    }

    public void setWidth (float val) {
        this.width = val;
    }

}

