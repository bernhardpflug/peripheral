/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.util;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Andi
 */
public class PositionRandomizer {

    public static Point getRandomPosition (java.awt.Rectangle bounds){
        Random rand = new Random();

        int x = bounds.x + Math.abs(rand.nextInt()) % bounds.width;
        int y = bounds.y + Math.abs(rand.nextInt()) % bounds.height;

        return new Point(x, y);
    }
}
