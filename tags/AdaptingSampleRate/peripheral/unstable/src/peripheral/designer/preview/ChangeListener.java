/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.designer.preview;

/**
 *
 * @author Berni
 */
public interface ChangeListener {

    /**
     * Event that is thrown if drag starts
     * @param origin
     */
    public void dragStart(java.awt.Point origin);

    /**
     * called every time the position changes
     * should work only after dragStart occured on dragable area
     * @param newPosition
     */
    public void dragAction(java.awt.Point newPosition);

    /**
     * indicates end of drag action
     */
    public void dragStop();
}
