package peripheral.logic.action;

import java.util.Observable;
import java.util.Set;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.positioningtool.ToolList;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;
import peripheral.logic.Runtime;

public class ListHideAction extends ListAction {

    private Value elementsToHide;

    public ListHideAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public ListHideAction(SymbolAdapter adapter, Value elementsToHide) {
        this(adapter);
        this.elementsToHide = elementsToHide;
    }

    public java.util.List<UserInput> getUserInput() {
        return null;
    }

    public String getName() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public void execute(ActionTool tool) {
        Set<PositioningTool> elementsToHide = getElementsToHide();
        ToolList toolList = (ToolList) tool;

        for (PositioningTool pt : elementsToHide) {
            if (pt instanceof Point) {
                Runtime.getInstance().getVisualization().hideSymbol(((Point) pt).getActSymbol());
            } else if (pt instanceof Region) {
                RegionAction act = new RegionHideAction(adapter);
                act.execute((Region) pt);
            }
        }
        toolList.getHiddenElements().addAll(elementsToHide);
        toolList.getVisibleElements().removeAll(elementsToHide);
    }

    public void update(Observable o, Object arg) {
    }

    public Set<PositioningTool> getElementsToHide() {
        return null;
    }
}

