package peripheral.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.positioningtool.ToolList;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorUpdateThread;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.visualization.Visualization;
import processing.core.PApplet;

public class Runtime {

    private Map<Sensor, java.util.List<SymbolAdapter>> sensorAdapterMapping;
    private static Runtime theInstance = null;
    private List<SensorUpdateThread> threads = new ArrayList<SensorUpdateThread>();
    private DisplayConfiguration displayConfig;

    private Runtime() {
    }

    public static Runtime getInstance() {
        if (theInstance == null) {
            theInstance = new Runtime();
        }

        return theInstance;
    }

    public Map<Sensor, java.util.List<SymbolAdapter>> getSensorAdapterMapping() {
        return sensorAdapterMapping;
    }

    public void startup(String configFile) {
        DisplayConfiguration.load(configFile);
        displayConfig = DisplayConfiguration.getInstance();

        //@todo: uncomment when visualization is ready
        //initVisualization();

        createSensorAdapterMapping();
        createSensorUpdateThreads();

        //@todo: uncomment when visualization is ready
        showVisualization();
    }

    public void shutdown() {
        for (Thread th : threads) {
            th.interrupt();
        }
    }

    private void initVisualization() {
        Visualization viz = null; //@todo: = VisualizationImpl.getVisualizationInstance()
        viz.init(
                displayConfig.getBackgroundImageFilename(),
                displayConfig.getDimension());

        ActionTool tool;
        ToolList<PositioningTool> toolList;
        for (SymbolAdapter adapter : displayConfig.getAdapter()) {
            tool = adapter.getTool();

            if (tool instanceof Point) {
                initPoint(viz, (Point) tool);
            } else if (tool instanceof Region) {
                initRegion(viz, (Region) tool);
            } else if (tool instanceof ToolList) {
                toolList = (ToolList<PositioningTool>) tool;

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

    private void showVisualization (){
        PApplet.main(new String[]{"--present", "NameFromAppletClass"});
    }

    private void initPoint(Visualization viz, Point point) {
        viz.add(point.getActSymbol(), null);
    }

    private void initRegion(Visualization viz, Region region) {
        for (Symbol s : region.getUserSelectedSymbols()) {
            viz.add(s, region);
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
}

