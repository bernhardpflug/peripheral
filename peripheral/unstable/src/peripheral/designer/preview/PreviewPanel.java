/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.designer.preview;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Berni
 */
public class PreviewPanel extends JPanel implements Runnable, MouseMotionListener, MouseListener{

    //Variables

    private PreviewDialog parent;

    private String no_preview_msg = "No preview available";
    private String error_msg = "Image can't be read";
    private String load_msg = "Loading image...";

    private File sourceFile;
    private BufferedImage backgroundImage;

    private boolean errorOccured, imageLoaded;

    private float scale;
    private Rectangle imageBounds;

    //boolean that defines whether mouse is currently in image
    private boolean inBounds = false;

    public PreviewPanel(PreviewDialog parent) {

        this.parent = parent;

        backgroundImage = null;
        errorOccured = false;
        imageLoaded = false;

        this.addMouseMotionListener(this);
        this.addMouseListener(this);

    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(File sourceFile) {

        this.sourceFile = sourceFile;

        imageLoaded = false;

        if (backgroundImage != null) {
            flushResources();
        }

        //check whether given file is image file, if not display no-support msg
        if (PreviewDialog.isExtentionSupported(sourceFile)) {
            //create thread of this with minor priority to load thumbnail
            Thread t = new Thread(this);
            t.setPriority(Math.max(Thread.currentThread().getPriority()-1, Thread.MIN_PRIORITY));
            t.start();
        }
        else {
            imageLoaded = true;
            backgroundImage = null;
        }

        this.repaint();
    }

    public void flushResources() {

        if (backgroundImage != null) {
            backgroundImage.flush();
        }
        backgroundImage = null;
        Runtime r = Runtime.getRuntime();
        r.gc();
    }

    @Override
    public void paint (Graphics g) {

        if (!imageLoaded) {

            paintBackground(g);
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D msgArea = fm.getStringBounds(load_msg,g);
            g.drawString(load_msg,(int)(this.getWidth()-msgArea.getWidth())/2,(int)(this.getHeight()-msgArea.getHeight())/2);
        }
        else if (errorOccured){

            paintBackground(g);
            g.setColor(Color.RED);
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D msgArea = fm.getStringBounds(error_msg,g);
            g.drawString(error_msg,(int)(this.getWidth()-msgArea.getWidth())/2,(int)(this.getHeight()-msgArea.getHeight())/2);
        }
        else if (backgroundImage == null) {

            paintBackground(g);
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D msgArea = fm.getStringBounds(no_preview_msg,g);
            g.drawString(no_preview_msg,(int)(this.getWidth()-msgArea.getWidth())/2,(int)(this.getHeight()-msgArea.getHeight())/2);
        }
        else {

            //This rectangle is drawn that after reload the old image isn't shown anymore
            //where the new one isn't painted!
            paintBackground(g);

            Rectangle imageBounds = calculateImageBounds(backgroundImage);
            
            g.drawImage(backgroundImage,(int)imageBounds.getX(),(int)imageBounds.getY(),(int)imageBounds.getX() + (int)imageBounds.getWidth(), (int)imageBounds.getY() + (int)imageBounds.getHeight(),
             0,0,backgroundImage.getWidth(),backgroundImage.getHeight(),Color.BLACK,null);

            g.setColor(Color.RED);
            g.drawRect(1,1,this.getX()-1,this.getY()-1);
        }
    }

    private void paintBackground(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(0,0,this.getWidth(),this.getHeight());
    }

    /**
     * Creates the bounds of the scaled image according to the panel size
     * @param backgroundImage
     * @return
     */
    private Rectangle calculateImageBounds(BufferedImage backgroundImage) {

        Rectangle imageBounds = new Rectangle();

        scale = calculateScale(backgroundImage);

        int ImgW = (int)((float)backgroundImage.getWidth()*scale);
        int ImgH = (int)((float)backgroundImage.getHeight()*scale);

        int NorthWestCornerX = (this.getWidth()-ImgW)/2;
        int NorthWestCornerY = (this.getHeight()-ImgH)/2;

        imageBounds.setBounds(NorthWestCornerX, NorthWestCornerY, ImgW, ImgH);

        this.imageBounds = imageBounds;

        return imageBounds;
    }
    /**
      * Method which is used from createThumbnailImg to caluclate best scale to fit
      * original image in defined preferred thumbnail size
      * @return float with best scaling rate for image
      */
     private float calculateScale(BufferedImage Original) {

         float diffX = (float)this.getWidth() / (float)Original.getWidth();
         float diffY = (float)this.getHeight() / (float)Original.getHeight();

         if (diffX > diffY) {
             return diffY;
         }
         else {
             return diffX;
         }
     }

     /**
      * Runs image load in thread to not influence GUI
      */
     public void run() {
         
        try {
            backgroundImage = ImageIO.read(sourceFile);
        }
        catch(IOException ioex) {
            ioex.printStackTrace();
            errorOccured = true;
        }

        imageLoaded = true;
        this.repaint();
    }

    /*
     *
     *MOUSE MOTION LISTENER
     */

    public void mouseDragged(MouseEvent e) {
        //DO nothing
    }

    public void mouseMoved(MouseEvent e) {

        if (backgroundImage != null) {
            //Only update coords if it is within image bounds
            if ((e.getX() >= imageBounds.x) && (e.getX() <= (imageBounds.x + imageBounds.width)) &&
                    (e.getY() >= imageBounds.y) && (e.getY() < (imageBounds.y + imageBounds.height))) {

                inBounds = true;
                parent.setMouseCoords(""+(int)((e.getX()-imageBounds.x)/scale), ""+(int)((e.getY()-imageBounds.y)/scale));
            }
            else {
                inBounds = false;
                parent.setMouseCoords("-", "-");
            }
        }
        
    }

    /*
     * MOUSE LISTENER
     */

    public void mouseClicked(MouseEvent e) {

        //in case mouse is within image
    }

    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("mouse entered");
    }

    /**
     * Additionally registers that mouse is out of bounds as if image
     * is directly at the border of the window mouse motion listener
     * does not recognize exit of mouse
     * @param e
     */
    public void mouseExited(MouseEvent e) {
        inBounds = false;
        parent.setMouseCoords("-", "-");
    }
}
