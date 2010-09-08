/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.action;

import peripheral.logic.Logging;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.symboladapter.SymbolAdapter;

/**
 *
 * @author Andi
 */
public class PointShowAction extends PointWrapperAction {
    public PointShowAction(SymbolAdapter adapter) {
        super(adapter, new SymbolShowAction(adapter));
    }

    @Override
    public void execute(ActionTool tool) {
        Point point = (Point) tool;

        if (!point.isVisible()) {
            point.setVisible(true);
            super.execute(tool);
        }
        else{
            Logging.getLogger().finer("Point is already visible.");
        }
    }
}
