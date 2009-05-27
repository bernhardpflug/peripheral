/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic;

/**
 *
 * @author Andi
 */
public class Peripheral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //@todo: read configuration-filename from args

        Runtime.getInstance().startup("displayConfiguration.ser");

        try {
            System.out.println("Press <Enter> to terminate visualization.");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Runtime.getInstance().shutdown();
    }
}
