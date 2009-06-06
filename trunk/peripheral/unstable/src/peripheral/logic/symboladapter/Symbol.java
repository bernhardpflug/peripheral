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
    //this file can additionally be set if one symbol should change its
    //image if the orientation of the movement changes (left-right/ up-down)
    private File secondFile;
    private transient BufferedImage bufferedImage;
    private float angle = 0;
    private java.awt.Point position;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private SymbolAdapter adapter;

    public Symbol(File file, PositioningTool tool, SymbolAdapter adapter) {
        this.file = file;
        this.adapter = adapter;

        if (tool != null) {
            if (tool instanceof Point) {
                this.position = ((Point) tool).getPosition();
            } else if (tool instanceof Region) {
                this.position = ((Region) tool).getBounds().getLocation();
            }
        }
    }

    public Symbol(File file, File secondFile, PositioningTool tool, SymbolAdapter adapter) {

        this(file,tool,adapter);

        this.secondFile = secondFile;
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

    public File getSecondFile() {
        return secondFile;
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

    public void setFile(File file) {
        this.file = file;
    }

    public void setSecondFile(File secondFile) {
        this.secondFile = secondFile;
    }

    public SymbolAdapter getAdapter() {
        return adapter;
    }

    public ClonedSymbol cloneSymbol() throws CloneNotSupportedException {
        Symbol source = null;
        source = (Symbol) this.clone();

        return new ClonedSymbol(source, this.file, this.getAdapter());
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

