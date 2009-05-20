package peripheral.logic.datatype;


public class Rectangle {

    private java.awt.Rectangle rectAbs;

    private RectangleF rectRel;

    public Rectangle () {
    }

    public java.awt.Rectangle getRectAbs () {
        return rectAbs;
    }

    public void setRectAbs (java.awt.Rectangle val) {
        this.rectAbs = val;
    }

    public RectangleF getRectRel () {
        return rectRel;
    }

    public void setRectRel (RectangleF val) {
        this.rectRel = val;
    }

}

