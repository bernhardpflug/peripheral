package peripheral.logic.positioningtool;

import java.util.ArrayList;

public class ToolList implements ActionTool {

    private java.util.ArrayList<PositioningTool> visibleElements;
    private java.util.ArrayList<PositioningTool> hiddenElements;
    private Class type;

    public ToolList(Class type) {
        this.type = type;

        if (type.equals(Point.class) || type.equals(Region.class)) {
            visibleElements = new ArrayList<PositioningTool>();
            hiddenElements = new ArrayList<PositioningTool>();
        }
        else{
            throw new IllegalArgumentException("type must be one of Point.class or Region.class");
        }
    }

    public java.util.ArrayList<PositioningTool> getHiddenElements() {
        return hiddenElements;
    }

    public java.util.ArrayList<PositioningTool> getVisibleElements() {
        return visibleElements;
    }

    public Class getType() {
        return type;
    }

    public void calculateRelativeCoordinates() {
    }

    public void draw(java.awt.Graphics g) {
    }

    public java.util.ArrayList<PositioningTool> getElements() {
        return (ArrayList<PositioningTool>) visibleElements;
    }
}

