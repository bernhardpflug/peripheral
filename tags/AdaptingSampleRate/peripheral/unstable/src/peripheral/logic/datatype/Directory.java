/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.datatype;

import java.io.File;
import java.net.URI;

/**
 * Class that represents a directory
 * this class is used to indicate for some properties of an adapter that
 * only the selection of a directory is allowed
 * @author Berni
 */
public class Directory extends File{

    public Directory(File parent, String child)  {
        super(parent, child);
    }

    public Directory(String pathname) {
        super(pathname);
    }

    public Directory(String parent, String child) {
        super(parent,child);
    }

    public Directory(URI uri) {
        super(uri);
    }
}
