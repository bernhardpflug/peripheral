package peripheral.logic;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.sensor.SensorUpdateThread;
import peripheral.logic.symboladapter.ClonedSymbol;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.util.PositionRandomizer;
import peripheral.viz.VisSymbol;
import peripheral.viz.Visualization;

public class Runtime {

    private Map<Sensor, java.util.List<SymbolAdapter>> sensorAdapterMapping;
    private static Runtime theInstance = null;
    private List<SensorUpdateThread> threads = new ArrayList<SensorUpdateThread>();
    private DisplayConfiguration displayConfig;
    private Visualization viz;

    private Runtime() {
        sensorAdapterMapping = new HashMap<Sensor, List<SymbolAdapter>>();
    }

    public static Runtime getInstance() {
        if (theInstance == null) {
            theInstance = new Runtime();
        }

        return theInstance;
    }

    public Visualization getVisualization() {
        if (viz != null) {
            return viz;
        }

        //@todo: comment out and return real viz-instance
        return new Visualization() {

            public void addSymbol(Symbol s, Region region) {
                Logging.getLogger().finer("Visualization: added symbol");
            }

            public void brightness(Symbol s, float amount) {
                Logging.getLogger().finer("Visualization: changed brightness to " + amount);
            }

            public void contrast(Symbol s, float amount) {
                Logging.getLogger().finer("Visualization: changed contrast to " + amount);
            }

            public void hideSymbol(Symbol s) {
                Logging.getLogger().finer("Visualization: hid symbol");
            }

            public void init(String backgroundImageFilename, Dimension resolution) {
                Logging.getLogger().finer("Visualization-init: background-image: " + backgroundImageFilename + "; resolution: " + resolution);
            }

            public void removeSymbol(Symbol s) {
                Logging.getLogger().finer("Visualization: removed symbol");
            }

            public void rotateSymbol(Symbol s, float angle) {
                Logging.getLogger().finer("Visualization: rotated symbol at angle " + angle);
            }

            public void scaleSymbol(Symbol s, float factorX, float factorY) {
                Logging.getLogger().finer("Visualization: scaled symbol at factor(x,y) (" + factorX + "," + factorY + ")");
            }

            public void showSymbol(Symbol s) {
                Logging.getLogger().finer("Visualization: showed symbol");
            }

            public void swapSymbol(Symbol s, String filename) {
                Logging.getLogger().finer("Visualization: swapped image of symbol. new image: " + filename);
            }

            public void translateSymbol(Symbol s, java.awt.Point targetPosition) {
                Logging.getLogger().finer("Visualization: translated symbol to position " + targetPosition);
            }

            public void addVisSymbol(VisSymbol s) {
                throw new UnsupportedOperationException("Not supported yet.");
            }




        };
    }

    public Map<Sensor, java.util.List<SymbolAdapter>> getSensorAdapterMapping() {
        return sensorAdapterMapping;
    }

    public void startup(Visualization viz, String configFile) {
        this.viz = viz;

        DisplayConfiguration.load(configFile);
        displayConfig = DisplayConfiguration.getInstance();

        //@todo: uncomment when visualization is ready
        initVisualization();

        executeAdapterInitActions();

        //@todo: delete: only for testing purpose
        //executeAdapters();
        ////@todo: delete: only for testing purpose
        //startSensorServer();

        createSensorAdapterMapping();
    //createSensorUpdateThreads();

    //startSensorCheckout();
    }

    private void startSensorServer() {
        // Create Server
        SensorServer server = new SensorServer("http://dyn167048.wlan.jku.at", "8080", "admin");
        server.connect();

        // Check count of sensors
        System.out.println("Server has " + server.getSensorList().size() + " Sensors.\n");

        for (SymbolAdapter s : DisplayConfiguration.getInstance().getAdapter()) {
            for (Sensor sensor : server.getSensorList()) {
                if (sensor.getPid() == 12) {
                    s.getPreselectedSensors().add(sensor);
                }
            }
        }

        for (Sensor sensor : server.getSensorList()) {
//			if(sensor.getPid() == 12){
            sensor.startCheckout();
//			}
        }
    }

    public void shutdown() {
        stopSensorCheckout();

        for (Thread th : threads) {
            th.interrupt();
        }

        File unzipDir = new File(DisplayConfiguration.UNZIP_DIR);
        deleteDirectory(unzipDir);
    }

    /**
     * helper method to delete a non-empty directory
     *
     * @param path
     * @return
     */
    private boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    private void executeAdapters() {
        for (SymbolAdapter s : DisplayConfiguration.getInstance().getAdapter()) {
            s.execute();
        }
    }

    private void executeAdapterInitActions() {
        for (SymbolAdapter adapter : DisplayConfiguration.getInstance().getAdapter()) {
            adapter.executeInitActions();
        }
    }

    private void initVisualization() {
        Visualization viz = getVisualization();

        viz.init(
                //@todo: change visualization interface to pass file object
                displayConfig.getBackgroundImageFile().getPath(),
                displayConfig.getDimension());

        ActionTool tool;
        for (SymbolAdapter adapter : displayConfig.getAdapter()) {
            tool = adapter.getTool();

            for (PositioningTool pt : tool.getElements()) {
                if (pt instanceof Point) {
                    initPoint(viz, (Point) pt);
                } else if (tool instanceof Region) {
                    initRegion(viz, (Region) pt);
                }
            }
        }
    }

    private void initPoint(Visualization viz, Point point) {
        viz.addSymbol(point.getActSymbol(), null);
    }

    private void initRegion(Visualization viz, Region region) {
        for (Symbol s : region.getSymbolList().getSourceSymbols()) {
            for (ClonedSymbol clone : region.getSymbolList().getClones(s)) {
                clone.setFile(s.getFile());
                if (s.getSecondFile() != null){
                    clone.setSecondFile(s.getFile());
                }
                clone.setPosition(PositionRandomizer.getRandomPosition(region.getBounds()));
                viz.addSymbol(clone, region);
            }
        }
    }

    private void createSensorAdapterMapping() {
        for (SymbolAdapter adapter : DisplayConfiguration.getInstance().getAdapter()) {
            for (Sensor s : adapter.getPreselectedSensors()) {
                if (!sensorAdapterMapping.containsKey(s)) {
                    sensorAdapterMapping.put(s, new ArrayList<SymbolAdapter>());
                }
                sensorAdapterMapping.get(s).add(adapter);
            }
        }
    }

    //only for testing purpose in visualization
    public List<Sensor> getSensors() {
        return new ArrayList<Sensor>(this.sensorAdapterMapping.keySet());
    }

    public void setSensorChanged(Sensor changedSensor) {
        for (SymbolAdapter adapterToNotify : sensorAdapterMapping.get(changedSensor)) {
            adapterToNotify.execute();
        }
    }

    private void createSensorUpdateThreads() {
        for (Sensor sensor : sensorAdapterMapping.keySet()) {
            threads.add(new SensorUpdateThread(sensor));
        }
    }

    private void startSensorCheckout() {
        for (Sensor sensor : sensorAdapterMapping.keySet()) {
            sensor.startCheckout();
        }
    }

    private void stopSensorCheckout() {
        for (Sensor sensor : sensorAdapterMapping.keySet()) {
            sensor.stopCheckout();
        }
    }
}

