package peripheral.logic.action;

import java.util.Collections;
import java.util.List;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.positioningtool.ToolList;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;

public class ListShowAction extends ListAction {

    private Value elementsToShow;
    private Value hideOthers;

    public ListShowAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public ListShowAction(SymbolAdapter adapter, Value elementsToShow, Value hideOthers) {
        this(adapter);
        this.elementsToShow = elementsToShow;
        this.hideOthers = hideOthers;
    }

    public java.util.List<UserInput> getUserInput() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Shows a list of passed points or regions.";
    }

    public void execute(ActionTool tool) {
        ToolList toolList = (ToolList) tool;
        List<PositioningTool> elementsToShow = getElementsToShow();

        if (getHideOthers()) {
            for (PositioningTool pt : toolList.getElements()) {
                if (!elementsToShow.contains(pt)) {
                    if (pt instanceof Point) {
                        PointWrapperAction act = new PointHideAction(adapter);
                        act.execute(pt);
                    } else if (pt instanceof Region) {
                        RegionAction act = new RegionHideAction(adapter);
                        act.execute(pt);
                    }
                }
            }
        }

        for (PositioningTool pt : elementsToShow) {
            if (pt instanceof Point) {
                PointWrapperAction act = new PointShowAction(adapter);
                act.execute(pt);
            } else if (pt instanceof Region) {
                RegionAction act = new RegionShowAction(adapter);
                act.execute(pt);
            }
        }
    }

    public List<PositioningTool> getElementsToShow() {
        return Collections.unmodifiableList((List<PositioningTool>) elementsToShow.getValue());
    }

    public boolean getHideOthers() {
        return (Boolean) hideOthers.getValue();
    }
}

