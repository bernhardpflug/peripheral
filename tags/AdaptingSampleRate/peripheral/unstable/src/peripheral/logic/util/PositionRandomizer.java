/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Andi
 */
public class PositionRandomizer {

    public static Point getRandomPosition(Rectangle bounds) {
        Random rand = new Random();

        int x = bounds.x + Math.abs(rand.nextInt()) % bounds.width;
        int y = bounds.y + Math.abs(rand.nextInt()) % bounds.height;

        return new Point(x, y);
    }

    public static Point getRandomPosition(Rectangle bounds, Dimension itemSize) {
        Rectangle newBounds = new Rectangle(bounds);

        //newBounds.x -= itemSize.width;
        //newBounds.y -= itemSize.height;

        return getRandomPosition(newBounds);
    }
}
