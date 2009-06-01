package peripheral.logic.action;

import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.Runtime;

public class RegionHideAction extends RegionAction {

    public RegionHideAction(SymbolAdapter adapter) {
        super(adapter);
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
        Region region = (Region) tool;

        if (region.isVisible()) {
            region.setVisible(false);
            for (Symbol s : region.getSymbols()) {
                Runtime.getInstance().getVisualization().hideSymbol(s);
            }
        }
    }
}

