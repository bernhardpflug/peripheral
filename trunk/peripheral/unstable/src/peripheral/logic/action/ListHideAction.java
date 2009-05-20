package peripheral.logic.action;

import java.util.Observable;
import java.util.Set;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class ListHideAction extends ListAction {

    private Value elementsToHide;

    public ListHideAction () {
    }

    public ListHideAction (Value elementsToHide) {
    }

    public java.util.Set<UserInput> getUserInput () {
        return null;
    }

    public String getName () {
        return null;
    }

    public String getDescription () {
        return null;
    }

    /**
     *  <p style="margin-top: 0">
     *        ToolList toolList = (ToolList)tool;<br><br>for (PositioningTool pt : 
     *        elementsToHide){<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        if (pt instanceof Point){<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        Visualization.hide(((Point)pt).getActSymbol());<br>}else if (pt 
     *        instanceof Region){<br>RegionAction act = new RegionHideAction();<br>act.execute((Region)pt);
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        }
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        }
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        toolList.getHiddenPoints().addAll(elementsToHide);<br>toolList.getVisiblePoints().removeAll(elementsToHide);
     *      </p>
     */
    public void execute (ActionTool tool) {
    }

    public void update (Observable o, Object arg) {
    }

    public Set<PositioningTool> getElementsToHide () {
        return null;
    }

}

