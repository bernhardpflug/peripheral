/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.designer.preview;

import peripheral.logic.positioningtool.PositioningTool;

/**
 *
 * @author Berni
 */
public interface ChangeListener {

    /**
     * Event that is thrown if preview fulfills a drag event
     * @param tool
     */
    public void dragOccurred(PositioningTool tool);
}
