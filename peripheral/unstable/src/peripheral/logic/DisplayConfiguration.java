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
import peripheral.logic.datatype.Directory;
import peripheral.logic.datatype.RestructuredFile;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.sensor.SensorServer.status;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.util.ZipPackager;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.Value;

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
        //createDummySensorServer();
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

    public void save(String filename) throws IOException{
        dimension = new Dimension(getWidth(), getHeight());

        //get all files to change path for
        ArrayList<RestructuredFile> files = ZipPackager.getRestructuredFiles();

        //change to relative path for all files to serialize it
        ZipPackager.setRestructuredFilePaths(files);

        File configFile = new File(CONFIG_FILE);

        try {
            FileOutputStream fs = new FileOutputStream(configFile);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(this);

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reset path for all files to be able to zip files
        ZipPackager.resetRestructuredFilePaths(files);

        ZipPackager.zip(filename, configFile, files);
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

    private static void updateFilePaths(DisplayConfiguration dc, String unzipFolder) {

        //BACKGROUND IMAGE
        dc.setBackgroundImageFile(new File(unzipFolder, dc.getBackgroundImageFile().getPath()));

        for (SymbolAdapter adapter : dc.getAdapter()) {

            //SYMBOLS
            for (PositioningTool pt : adapter.getTool().getElements()) {
                for (Symbol symbol : pt.getSymbols()) {
                    symbol.setFile(new File(unzipFolder, symbol.getFile().getPath()));

                    if (symbol.getSecondFile() != null) {
                        symbol.setSecondFile(new File(unzipFolder, symbol.getSecondFile().getPath()));
                    }
                }
            }

            //VARPOOL
            for (Value value : adapter.getVarpool().values()) {
                //check all constValue whether they include a file or a directory
                if (value instanceof ConstValue) {
                    ConstValue constVal = (ConstValue) value;
                    Object val = value.getValue();
                    if (val != null) {
                        if (val.getClass().equals(File.class)) {
                            constVal.setValue(new File(unzipFolder, ((File) val).getPath()));

                        } else if (val.getClass().equals(Directory.class)) {
                            constVal.setValue(new Directory(unzipFolder, ((File) val).getPath()));
                        }
                    }
                }
            }
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

    //@todo: remove method
    private void createDummySensorServer() {
        // Create dummy server instance
        SensorServer dummyserver = new SensorServer("http://dummyserver", "8080", "admin");

        // Create Sensor
        Sensor dummyserversensor = new Sensor(0, "admin:emptysensor", dummyserver);
        Sensor dummysensor = new Sensor(1, "admin:dummysensor", dummyserver);

        // Create SensorChannel channel1 for dummysensor
        SensorChannel channel1 = new SensorChannel(34, "admin:Dummy Sensor:Dummy Stim:dummychannel1", dummysensor);

        // Create SensorChannel channel2 for dummysensor
        SensorChannel channel2 = new SensorChannel(32, "admin:Dummy Sensor:Dummy Stim:dummychannel2", dummysensor);

        // Create SensorChannel channel2 for dummysensor
        SensorChannel channel3 = new SensorChannel(35, "admin:Dummy Sensor:Dummy Stim:dummychannel3", dummysensor);


        // Create Metadata for channel1
        TreeMap<String, String> metadata1 = channel1.getMetadata();
        metadata1.put("shortname", "AirTemp");
        metadata1.put("description", "Air Temperature");
        metadata1.put("datatype", "Integer8");
        metadata1.put("units", "Degree Celcius");
        metadata1.put("location", "somewhere outside");
        metadata1.put("upperlimit", "500");
        metadata1.put("lowerlimit", "0");

        // Create Metadata for channel2
        TreeMap<String, String> metadata2 = channel2.getMetadata();
        metadata2.put("shortname", "SkyCover");
        metadata2.put("description", "Percentage of Sky Cover");
        metadata2.put("datatype", "Integer8");
        metadata2.put("units", "Percent");
        metadata2.put("location", "unknown");
        metadata2.put("upperlimit", "10");
        metadata2.put("lowerlimit", "0");

        metadata2 = channel3.getMetadata();
        metadata2.put("shortname", "FloatVal");
        metadata2.put("description", "Percentage of Sky Cover");
        metadata2.put("datatype", "Float32");
        metadata2.put("units", "Percent");
        metadata2.put("location", "unknown");
        metadata2.put("upperlimit", "30");
        metadata2.put("lowerlimit", "-20");

        // Add channels to Sensor
        ArrayList<SensorChannel> channellist = dummysensor.getSensorChannels();
        channellist.add(channel1);
        channellist.add(channel2);
        channellist.add(channel3);

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

