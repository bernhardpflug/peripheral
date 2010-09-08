/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic;

import java.io.File;
import javax.swing.JFileChooser;
import peripheral.designer.StartUpDialog.StartOption;

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

        String file = "displayConfig.zip";
        file = showOpenDlg();
        Runtime.getInstance().startup(null, file);

        try {
            System.out.println("Press <Enter> to terminate visualization.");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Runtime.getInstance().shutdown();
    }

    private static String showOpenDlg() {
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setFileFilter(new ConfigFileFilter());
        fileChooser.setDialogTitle("Select an configuration file to load");
        File file = null;

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        return file.getPath();
    }
}
