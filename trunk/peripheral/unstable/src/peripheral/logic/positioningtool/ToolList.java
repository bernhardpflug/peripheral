package peripheral.logic.positioningtool;


public class ToolList <T extends PositioningTool> implements ActionTool {

    private java.util.Set<T> visibleElements;

    private java.util.Set<T> hiddenElements;

    private T type;

    public T getType() {
        return type;
    }

    public ToolList () {
    }

    public java.util.Set<T> getHiddenElements () {
        return hiddenElements;
    }

    public void setHiddenElements (java.util.Set<T> val) {
        this.hiddenElements = val;
    }

    public java.util.Set<T> getVisibleElements () {
        return visibleElements;
    }

    public void setVisibleElements (java.util.Set<T> val) {
        this.visibleElements = val;
    }

    public void calculateRelativeCoordinates () {
    }

    public void draw (java.awt.Graphics g) {
    }

}

