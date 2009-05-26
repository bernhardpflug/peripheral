package peripheral.logic.positioningtool;

import java.io.Serializable;


public interface ActionTool extends Serializable{

    public void calculateRelativeCoordinates ();

    public void draw (java.awt.Graphics g);

}

