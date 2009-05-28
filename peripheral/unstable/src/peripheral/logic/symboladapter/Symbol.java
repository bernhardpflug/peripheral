package peripheral.logic.symboladapter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import peripheral.logic.action.SymbolAction;
import peripheral.logic.positioningtool.Point; 

public class Symbol implements Serializable{


    private File file;

    private transient BufferedImage bufferedImage;

    private float angle;

    private Point position;

    private float scaleX;

    private float scaleY;

    public Symbol (File file) {
        this.file = file;
    }

    public void executeAction (SymbolAction action) {
    }

    public float getAngle () {
        return angle;
    }

    public void setAngle (float val) {
        this.angle = val;
    }

    public File getFile () {
        return file;
    }

    public Point getPosition () {
        return position;
    }

    public void setPosition (Point val) {
        this.position = val;
    }

    public float getScaleX () {
        return scaleX;
    }

    public void setScaleX (float val) {
        this.scaleX = val;
    }

    public float getScaleY () {
        return scaleY;
    }

    public void setScaleY (float val) {
        this.scaleY = val;
    }

    public ClonedSymbol cloneSymbol () {
        return null;
    }

    public BufferedImage getBufferedImage () {

        if (bufferedImage == null) {
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return bufferedImage;
    }

    public void flush() {

        if (bufferedImage != null) {
            bufferedImage.flush();
        }
    }

    @Override
    public String toString() {
        return file.getName();
    }

}

