/*
 * Special Filechooser used in the whole designer that is
 * restricted to image files and supports single click
 * directory entry
 *
 * To be informed about approve events register ActionListener
 * with ActionCommand=ApproveSelection
 */

package peripheral.designer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Berni
 */
public class ImageFileChooser extends JFileChooser{

    public ImageFileChooser() {
        super();

        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.addPropertyChangeListener(new MyPropertyChangeListener(this));
        this.setFileFilter(new ImageFilter());
    }

    public ImageFileChooser(boolean enableImagePreviews) {
        
        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.setFileFilter(new ImageFilter());

        if (enableImagePreviews) {
            this.setAccessory(new ImagePreview(this));
        }
    }

    class MyPropertyChangeListener implements PropertyChangeListener {

        private ImageFileChooser ifc;

        //To prevent reentry
        private boolean handlingEvent = false;

        public MyPropertyChangeListener(ImageFileChooser ifc) {
            this.ifc = ifc;
        }
        public void propertyChange(PropertyChangeEvent e) {

            //Prevent reentry
            if (handlingEvent) {
                return;
            } else //Mark it as handling the event
            {
                handlingEvent = true;
            }

            String propertyName = e.getPropertyName();

            //We are interested in both event types
            if (propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) ||
                    propertyName.equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {

                File selectedFile = (File) e.getNewValue();
                if (selectedFile != null) {
                    if (selectedFile.isDirectory()) {
                        //Allow the user to navigate directories with single click
                        //ifc.setCurrentDirectory(selectedFile);
                    } else {
                        ifc.setSelectedFile(selectedFile);
                        if (ifc.getSelectedFile() != null) //Accept it
                        {
                            ifc.approveSelection();
                        }
                    }
                }
            }

            //Allow new events to be processed now
            handlingEvent = false;
        }
    }
}
