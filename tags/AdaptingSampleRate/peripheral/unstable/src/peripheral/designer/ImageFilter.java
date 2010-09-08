/*
 * Filter that allows filechooser to just display supported image files
 */

package peripheral.designer;

import java.io.File;
import javax.swing.filechooser.*;
import peripheral.designer.preview.PreviewDialog;


public class ImageFilter extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        if (PreviewDialog.isExtentionSupported(f)) {
            return true;
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Just Images";
    }
}
