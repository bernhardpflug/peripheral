package peripheral.logic;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.sensor.SensorServer.status;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.util.ZipPackager;

public class DisplayConfiguration implements Serializable {

    private static DisplayConfiguration instance;
    private java.util.List<SymbolAdapter> adapter;
    private File backgroundImageFile;
    //@todo: make SensorServer and contained objects serializable
    private java.util.List<SensorServer> sensorServer;
    private transient BufferedImage backgroundImage;
    private Dimension dimension;
    private static final String CONFIG_FILE = "DisplayConfiguration.config";
    public static final String UNZIP_DIR = "res/unzip";

    private DisplayConfiguration() {

        adapter = new ArrayList<SymbolAdapter>();
        sensorServer = new ArrayList<SensorServer>();

        //@todo: remove
        createDummySensorServer();
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

    public void setBackgroundImageFile(File file) {
        this.backgroundImageFile = file;
    }

    public File getBackgroundImageFile() {
        return backgroundImageFile;
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

        File configFile = new File(CONFIG_FILE);

        try {
            FileOutputStream fs = new FileOutputStream(configFile);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(this);

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ZipPackager.zip(filename, configFile);
        configFile.delete();
    }

    public static void load(String filename) {
        ZipPackager.unzip(filename, UNZIP_DIR);

        File configFile = new File(UNZIP_DIR + File.separator + CONFIG_FILE);

        try {
            FileInputStream fs = new FileInputStream(configFile);
            ObjectInputStream is = new ObjectInputStream(fs);

            instance = (DisplayConfiguration) is.readObject();

            updateFilePaths(instance, UNZIP_DIR);

            is.close();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void updateFilePaths (DisplayConfiguration dc, String unzipFolder){
        for (SymbolAdapter adapter : dc.getAdapter()) {
            for (PositioningTool pt : adapter.getTool().getElements()) {
                for (Symbol symbol : pt.getSymbols()) {
                    symbol.setFile(new File(unzipFolder, ZipPackager.getRelativePath(symbol.getFile())));
                }
            }
        }

        dc.setBackgroundImageFile(new File(unzipFolder, ZipPackager.getRelativePath(dc.getBackgroundImageFile())));
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

    //@todo: remove method
    private void createDummySensorServer() {
        // Create dummy server instance
        SensorServer dummyserver = new SensorServer("http://dummyserver", "8080", "admin");

        // Create Sensor
        Sensor dummyserversensor = new Sensor(0, "admin:00999*99999*99999*99999", dummyserver);
        Sensor dummysensor = new Sensor(1, "admin:dummysensor", dummyserver);

        // Create SensorChannel channel1 for dummysensor
        SensorChannel channel1 = new SensorChannel(34, "admin:Dummy Sensor:Dummy Stim:dummychannel1", dummysensor);

        // Create SensorChannel channel2 for dummysensor
        SensorChannel channel2 = new SensorChannel(32, "admin:Dummy Sensor:Dummy Stim:dummychannel2", dummysensor);

        // Create Metadata for channel1
        TreeMap<String, String> metadata1 = channel1.getMetadata();
        metadata1.put("shortname", "AirTemp");
        metadata1.put("description", "Air Temperature");
        metadata1.put("datatype", "Integer8");
        metadata1.put("units", "Degree Celcius");
        metadata1.put("location", "In your face bitch");

        // Create Metadata for channel2
        TreeMap<String, String> metadata2 = channel2.getMetadata();
        metadata2.put("shortname", "SkyCover");
        metadata2.put("description", "Percentage of Sky Cover");
        metadata2.put("datatype", "String");
        metadata2.put("units", "Percent");
        metadata2.put("location", "In your face bitch");
        metadata2.put("upperlimit", "100");
        metadata2.put("lowerlimit", "0");

        // Add channels to Sensor
        ArrayList<SensorChannel> channellist = dummysensor.getSensorChannels();
        channellist.add(channel1);
        channellist.add(channel2);

        // Add Sensor to Server
        ArrayList<Sensor> sensorlist = dummyserver.getSensorList();
        sensorlist.add(dummyserversensor);
        sensorlist.add(dummysensor);

        // Add observers
        //dummyserver.addObserver(this);
        dummyserver.setConnectionStatus(status.online);

        // Add dummyserver to serverList
        this.sensorServer.add(dummyserver);
    }
}

