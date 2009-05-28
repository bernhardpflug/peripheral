package peripheral.logic.positioningtool;

import java.util.ArrayList;


public class ToolList implements ActionTool {

    private java.util.ArrayList<PositioningTool> visibleElements;

    private java.util.ArrayList<PositioningTool> hiddenElements;

    public ToolList () {
        visibleElements = new ArrayList<PositioningTool>();
        hiddenElements = new ArrayList<PositioningTool>();
    }

    public java.util.ArrayList<PositioningTool> getHiddenElements () {
        return hiddenElements;
    }

    public void setHiddenElements (java.util.ArrayList<PositioningTool> val) {
        this.hiddenElements = val;
    }

    public java.util.ArrayList<PositioningTool> getVisibleElements () {
        return visibleElements;
    }

    public void setVisibleElements (java.util.ArrayList<PositioningTool> val) {
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

