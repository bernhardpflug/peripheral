package peripheral.logic.action;

import java.util.Observable;
import java.util.Set;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class ListShowAction extends ListAction {

    private Value elementsToShow;

    private Value hideOthers;

    public ListShowAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public ListShowAction (SymbolAdapter adapter, Value elementsToShow, Value hideOthers) {
        this(adapter);
        this.elementsToShow = elementsToShow;
        this.hideOthers = hideOthers;
    }

    public java.util.List<UserInput> getUserInput () {
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
     *        ToolList toolList = (ToolList)tool;<br><br>Set&lt;PositioningTool&gt; 
     *        elemsToHide = new HashSet&lt;PositioningTool&gt;();<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        if (hideOthers){
     *      </p>
     *      <p style="margin-top: 0">
     *        for (PositioningTool pt : toolList.getVisibleElements()){<br>if 
     *        (!elementsToShow.contains(pt)){<br>if (pt instanceof Point){<br>
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
     *        elemsToHide.add(pt);<br>}<br>else {<br>//Point is already visible, so no 
     *        new show-effect has to be applied
     *      </p>
     *      <p style="margin-top: 0">
     *        elementsToShow.remove(pt);<br>}<br>}
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        }<br>else {<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        elementsToShow.removeAll(toolList.getVisibleElements());<br>}<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        toolList.getHiddenPoints().removeAll(elementsToShow);<br>toolList.getHiddenPoints().addAll(elemsToHide);
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        
     *      </p>
     *      <p style="margin-top: 0">
     *        toolList.getVisiblePoints().removeAll(elemsToHide);
     *      </p>
     *      <p style="margin-top: 0">
     *        toolList.getVisiblePoints().addAll(elementsToShow);
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        for (PositioningTool pt : elementsToShow){<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        if (pt instanceof Point){<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        Visualization.show(((Point)pt).getActSymbol());<br>}else if (pt 
     *        instanceof Region){<br>RegionAction act = new RegionShowAction();<br>act.execute((Region)pt);
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        }
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        }
     *      </p>
     */
    public void execute (ActionTool tool) {
    }

    public void update (Observable o, Object arg) {
    }

    public Set<PositioningTool> getElementsToShow () {
        return null;
    }

    public boolean getHideOthers () {
        return true;
    }

}

