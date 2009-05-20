package peripheral.logic.sensor;

import java.util.List;


public class Sensor {

    private long pid;

    private String name;

    private SensorServer server;

    private java.util.List<SensorChannel> channels;

    private float samplerate;

    private SensorServer parentServer;

    public Sensor(){}

    public Sensor (long pid, String name, SensorServer server, float samplerate) {
    }

    public List<SensorChannel> getSensorChannels () {
        return channels;
    }

    public String getName () {
        return name;
    }

    public long getPid () {
        return pid;
    }

    public SensorServer getServer () {
        return server;
    }

    public void addSensorChannel (SensorChannel channel) {
    }

    public void removeSensorChannel (long mid) {
    }

    public SensorServer getParentServer () {
        return parentServer;
    }

    public void setParentServer (SensorServer server) {
        this.parentServer = server;
    }

    public float getSamplerate () {
        return samplerate;
    }

    public void setSamplerate (float samplerate) {
        this.samplerate = samplerate;
    }

}

