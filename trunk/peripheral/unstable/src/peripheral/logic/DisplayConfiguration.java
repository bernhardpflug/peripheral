package peripheral.logic;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.symboladapter.SymbolAdapter;

public class DisplayConfiguration implements Serializable {

    private static DisplayConfiguration instance;
    private java.util.List<SymbolAdapter> adapter;
    private String backgroundImageFilename = "c:\\test.jpg";
    //@todo: make SensorServer and contained objects serializable
    private java.util.List<SensorServer> sensorServer;
    private transient BufferedImage backgroundImage;

    private Dimension dimension;

    private DisplayConfiguration() {

        adapter = new ArrayList<SymbolAdapter>();
        sensorServer = new ArrayList<SensorServer>();
    }

    public static DisplayConfiguration getInstance() {

        if (instance == null) {
            instance = new DisplayConfiguration();
        }

        return instance;
    }

    public java.util.List<SymbolAdapter> getAdapter() {
        return adapter;
    }

    public void setBackgroundImageFilename(String val) {
        this.backgroundImageFilename = val;
    }

    public String getBackgroundImageFilename() {
        return backgroundImageFilename;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void save(String filename) {
        dimension = new Dimension(getWidth(), getHeight());

        try {
            FileOutputStream fs = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(this);

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(String filename){
        try{
            FileInputStream fs = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fs);

            instance = (DisplayConfiguration)is.readObject();

            is.close();
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public int getWidth() {

        if (backgroundImage != null) {
            return backgroundImage.getWidth();
        }
        return 0;
    }

    public int getHeight() {
        if (backgroundImage != null) {
            return backgroundImage.getHeight();
        }
        return 0;
    }

    public java.util.List<SensorServer> getSensorServer() {
        return sensorServer;
    }
}

