package peripheral.logic.symboladapter;

import peripheral.logic.action.SymbolAction;
import peripheral.logic.positioningtool.Point; 

public class Symbol {


    private String filename;

    private float angle;

    private Point position;

    private float scaleX;

    private float scaleY;

    public Symbol () {
    }

    public void executeAction (SymbolAction action) {
    }

    public float getAngle () {
        return angle;
    }

    public void setAngle (float val) {
        this.angle = val;
    }

    public String getFilename () {
        return filename;
    }

    public void setFilename (String val) {
        this.filename = val;
    }

    public Point getPosition () {
        return position;
    }

    public void setPosition (Point val) {
        this.position = val;
    }

    public float getScaleX () {
        return scaleX;
    }

    public void setScaleX (float val) {
        this.scaleX = val;
    }

    public float getScaleY () {
        return scaleY;
    }

    public void setScaleY (float val) {
        this.scaleY = val;
    }

    public ClonedSymbol cloneSymbol () {
        return null;
    }

    public void draw (java.awt.Graphics g) {
    }


}

