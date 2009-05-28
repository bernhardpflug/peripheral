package peripheral.logic.positioningtool;

import java.io.Serializable;
import java.util.ArrayList;


public interface ActionTool extends Serializable{

    public void calculateRelativeCoordinates ();

    public void draw (java.awt.Graphics g);

    public ArrayList<PositioningTool> getElements();

}

