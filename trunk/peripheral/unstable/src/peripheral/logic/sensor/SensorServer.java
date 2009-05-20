package peripheral.logic.sensor;

import java.net.URL;


public class SensorServer {

    private URL address;

    private int port;

    private String username;

    private java.util.List<Sensor> sensors;

    public SensorServer (URL address, int port, String username) {
    }

    public URL getAddress () {
        return address;
    }

    public void setAddress (URL val) {
        this.address = val;
    }

    public int getPort () {
        return port;
    }

    public void setPort (int val) {
        this.port = val;
    }

    public java.util.List<Sensor> getSensorList () {
        return sensors;
    }

    public void setSensors (java.util.List<Sensor> sensorlist) {
        this.sensors = sensorlist;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String val) {
        this.username = val;
    }

    public void addSensor (Sensor sensor) {
    }

    public void removeSensor (long pid) {
    }

}

