package peripheral.logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.symboladapter.SymbolAdapter;


public class DisplayConfiguration {

    private static DisplayConfiguration instance;

    private java.util.List<SymbolAdapter> adapter;

    private BufferedImage backgroundImage;

    private java.util.List<SensorServer> sensorServer;

    private DisplayConfiguration () {

        adapter = new ArrayList<SymbolAdapter>();

        sensorServer = new ArrayList<SensorServer>();
    }

    public static DisplayConfiguration getInstance () {

        if (instance == null) {
            instance = new DisplayConfiguration();
        }

        return instance;
    }

    public java.util.List<SymbolAdapter> getAdapter () {
        return adapter;
    }

    public void setAdapter (java.util.List<SymbolAdapter> val) {
        this.adapter = val;
    }

    public BufferedImage getBackgroundImage () {
        return backgroundImage;
    }

    public void setBackgroundImage (BufferedImage val) {
        this.backgroundImage = val;
    }

    public void save (String filename) {
    }

    public int getWidth () {

        if (backgroundImage != null) {
            return backgroundImage.getWidth();
        }
        return 0;
    }

    public int getHeight () {
        if (backgroundImage != null) {
            return backgroundImage.getHeight();
        }
        return 0;
    }

    public java.util.List<SensorServer> getSensorServer () {
        return sensorServer;
    }

}

