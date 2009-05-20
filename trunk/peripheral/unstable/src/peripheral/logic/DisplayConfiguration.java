package peripheral.logic;

import java.awt.image.BufferedImage;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.symboladapter.SymbolAdapter;


public class DisplayConfiguration {

    private java.util.List<SymbolAdapter> adapter;

    private BufferedImage backgroundImage;

    private java.util.List<SensorServer> sensorServer;

    private DisplayConfiguration () {
    }

    public static DisplayConfiguration getInstance () {
        return null;
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
        return 0;
    }

    public int getHeight () {
        return 0;
    }

    public java.util.List<SensorServer> getSensorServer () {
        return sensorServer;
    }

}

