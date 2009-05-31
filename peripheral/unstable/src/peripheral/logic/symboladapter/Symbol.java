package peripheral.logic.symboladapter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import peripheral.logic.action.SymbolAction;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;

public class Symbol implements Serializable, Cloneable {

    private File file;
    private transient BufferedImage bufferedImage;
    private float angle = 0;
    private java.awt.Point position;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;

    public Symbol(File file, PositioningTool tool) {
        this.file = file;

        if (tool != null) {
            if (tool instanceof Point) {
                this.position = ((Point) tool).getPosition();
            } else if (tool instanceof Region) {
                this.position = ((Region) tool).getBounds().getLocation();
            }
        }
    }

    public void executeAction(SymbolAction action) {
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float val) {
        this.angle = val;
    }

    public File getFile() {
        return file;
    }

    public java.awt.Point getPosition() {
        return position;
    }

    public void setPosition(java.awt.Point val) {
        this.position = val;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float val) {
        this.scaleX = val;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float val) {
        this.scaleY = val;
    }

    public ClonedSymbol cloneSymbol() throws CloneNotSupportedException {
        Symbol source = null;
        source = (Symbol) this.clone();

        return new ClonedSymbol(source, this.file);
    }

    public BufferedImage getBufferedImage() {

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

