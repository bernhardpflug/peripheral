package peripheral.logic;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.positioningtool.ToolList;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.sensor.SensorUpdateThread;
import peripheral.logic.sensor.XmlMetaParser;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.visualization.Visualization;

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

    public Visualization getVisualization(){
        //return viz;

        //@todo: comment out and return real viz-instance
        return new Visualization(){

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
                throw new UnsupportedOperationException("Not supported yet.");
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
        //initVisualization();

        //@todo: delete: only for testing purpose
        executeAdapters();

        // startSensorServer();

        //createSensorAdapterMapping();
        //createSensorUpdateThreads();

        //startServers();
    //startSensorCheckout();
    }

    private void startSensorServer() {
        // Create Server
        SensorServer server = new SensorServer("http://dyn167048.wlan.jku.at", "8080", "admin");

        // Get Server's XML Parser
        XmlMetaParser xml = server.getXmlMetaParser();
        xml.createInstancesFromXML();

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
    }

    private void executeAdapters(){
        for (SymbolAdapter s : DisplayConfiguration.getInstance().getAdapter()){
            s.execute();
        }
    }

    private void initVisualization() {
        viz.init(
                displayConfig.getBackgroundImageFilename(),
                displayConfig.getDimension());
 
        ActionTool tool;
        ToolList toolList;
        for (SymbolAdapter adapter : displayConfig.getAdapter()) {
            tool = adapter.getTool();

            if (tool instanceof Point) {
                initPoint(viz, (Point) tool);
            } else if (tool instanceof Region) {
                initRegion(viz, (Region) tool);
            } else if (tool instanceof ToolList) {
                toolList = (ToolList) tool;

                for (PositioningTool pt : toolList.getVisibleElements()) {
                    if (pt instanceof Point) {
                        initPoint(viz, (Point) pt);
                    } else if (tool instanceof Region) {
                        initRegion(viz, (Region) pt);
                    }
                }
            }
        }
    }

    private void showVisualization() {
        
    }

    private void initPoint(Visualization viz, Point point) {
        viz.addSymbol(point.getActSymbol(), null);
    }

    private void initRegion(Visualization viz, Region region) {
        for (Symbol s : region.getSymbols()) {
            viz.addSymbol(s, region);
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

    private void startServers() {
        for (Thread th : threads) {
           // th.start();
        }
    }
}

