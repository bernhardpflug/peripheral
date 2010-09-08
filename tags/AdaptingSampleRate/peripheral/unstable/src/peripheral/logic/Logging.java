/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andi
 */
public class Logging {

    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("PeripheralLogger");
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.ALL);

            logger.addHandler(ch);
            logger.setLevel(Level.ALL);
            //SimpleFormatter formatter = new SimpleFormatter();
            //ch.setFormatter(formatter);
        }
        return logger;
    }
}
