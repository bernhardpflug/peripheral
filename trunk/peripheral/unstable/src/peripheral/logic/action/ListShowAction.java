package peripheral.logic.action;

import java.util.ArrayList;
import java.util.HashSet;
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
import peripheral.visualization.Visualization;
import peripheral.logic.Runtime;

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

    public String getName() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public void execute(ActionTool tool) {
        ToolList toolList = (ToolList) tool;
        Set<PositioningTool> elemsToHide = new HashSet<PositioningTool>();
        Set<PositioningTool> elementsToShow = getElementsToShow();

        if (getHideOthers()) {
            for (PositioningTool pt : toolList.getVisibleElements()) {
                if (!elementsToShow.contains(pt)) {
                    if (pt instanceof Point) {
                        Runtime.getInstance().getVisualization().hideSymbol(((Point) pt).getActSymbol());
                    } else if (pt instanceof Region) {
                        RegionAction act = new RegionHideAction(adapter);
                        act.execute((Region) pt);
                    }
                    elemsToHide.add(pt);
                } else {
                    //Point is already visible, so no new show-effect has to be applied
                    elementsToShow.remove(pt);
                }
            }
        } else {
            elementsToShow.removeAll(toolList.getVisibleElements());
        }

        toolList.getHiddenElements().removeAll(elementsToShow);
        toolList.getHiddenElements().addAll(elemsToHide);
        toolList.getVisibleElements().removeAll(elemsToHide);
        toolList.getVisibleElements().addAll(elementsToShow);

        for (PositioningTool pt : elementsToShow) {
            if (pt instanceof Point) {
                Runtime.getInstance().getVisualization().showSymbol(((Point) pt).getActSymbol());
            } else if (pt instanceof Region) {
                RegionAction act = new RegionShowAction(adapter);
                act.execute((Region) pt);
            }
        }
    }

    public void update(Observable o, Object arg) {
    }

    public Set<PositioningTool> getElementsToShow() {
        return null;
    }

    public boolean getHideOthers() {
        return true;
    }
}

