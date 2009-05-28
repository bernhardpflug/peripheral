package peripheral.logic.positioningtool;

import java.util.ArrayList;


public class ToolList <T extends PositioningTool> implements ActionTool {

    private java.util.ArrayList<T> visibleElements;

    private java.util.ArrayList<T> hiddenElements;

    private T type;

    public T getType() {
        return type;
    }

    public ToolList () {
    }

    public java.util.ArrayList<T> getHiddenElements () {
        return hiddenElements;
    }

    public void setHiddenElements (java.util.ArrayList<T> val) {
        this.hiddenElements = val;
    }

    public java.util.ArrayList<T> getVisibleElements () {
        return visibleElements;
    }

    public void setVisibleElements (java.util.ArrayList<T> val) {
        this.visibleElements = val;
    }

    public void calculateRelativeCoordinates () {
    }

    public void draw (java.awt.Graphics g) {
    }

    public java.util.ArrayList<PositioningTool> getElements() {
        return (ArrayList<PositioningTool>) visibleElements;
    }

}

