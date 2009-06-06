/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.datatype;

import java.io.File;

/**
 * Class that keeps
 * @author Berni
 */
public class RestructuredFile {

    private File oldFile;

    private String newPath;

    public RestructuredFile(File oldFile) {
        this.oldFile = oldFile;
        
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public File getOldFile() {
        return oldFile;
    }

    public void setOldFile(File oldFile) {
        this.oldFile = oldFile;
    }
}
