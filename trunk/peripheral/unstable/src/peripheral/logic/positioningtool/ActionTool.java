package peripheral.logic.positioningtool;

import java.io.Serializable;
import java.util.List;


public interface ActionTool extends Serializable{

    public void calculateRelativeCoordinates ();

    public void draw (java.awt.Graphics g);

    public List<PositioningTool> getElements();

}

