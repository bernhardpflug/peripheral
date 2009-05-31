package peripheral.logic.positioningtool;

import java.util.ArrayList;
import java.util.List;

public class ToolList implements ActionTool {

    //private java.util.ArrayList<PositioningTool> visibleElements;
    //private java.util.ArrayList<PositioningTool> hiddenElements;

    private List<PositioningTool> elements;
    private Class type;

    public ToolList(Class type) {
        this.type = type;

        if (type.equals(Point.class) || type.equals(Region.class)) {
            //visibleElements = new ArrayList<PositioningTool>();
            //hiddenElements = new ArrayList<PositioningTool>();
            elements = new ArrayList<PositioningTool>();
        }
        else{
            throw new IllegalArgumentException("type must be one of Point.class or Region.class");
        }

        if (type.equals(Point.class)){
            //visibleElements.add(new Point());
            elements.add(new Point());
        }
        else {
            //visibleElements.add(new Region());
            elements.add(new Region());
        }
    }

    /*public java.util.ArrayList<PositioningTool> getHiddenElements() {
        return hiddenElements;
    }

    public java.util.ArrayList<PositioningTool> getVisibleElements() {
        return visibleElements;
    }*/

    public Class getType() {
        return type;
    }

    public void calculateRelativeCoordinates() {
    }

    public void draw(java.awt.Graphics g) {
    }

    
    @Override
    public List<PositioningTool> getElements() {
        //return (ArrayList<PositioningTool>) visibleElements;
        return elements;
    }

}

