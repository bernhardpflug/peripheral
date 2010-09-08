package peripheral.logic.action;

import java.util.Collections;
import java.util.List;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

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
        List<PositioningTool> elementsToHide = getElementsToHide();

        for (PositioningTool pt : elementsToHide) {
            if (pt instanceof Point) {
                PointWrapperAction act = new PointHideAction(adapter);
                act.execute(pt);
            } else if (pt instanceof Region) {
                RegionAction act = new RegionHideAction(adapter);
                act.execute(pt);
            }
        }
    }

    public List<PositioningTool> getElementsToHide() {
        return Collections.unmodifiableList((List<PositioningTool>) elementsToHide.getValue());
    }
}

