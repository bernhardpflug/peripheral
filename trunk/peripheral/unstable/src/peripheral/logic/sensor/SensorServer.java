package peripheral.logic.sensor;

import java.util.ArrayList;



public class SensorServer {

    private String address;
    private int port;
    private String username;
    private ArrayList<Sensor> sensors;
    private XmlMetaParser parser;

    public SensorServer (String address, int port, String username) {
    	this.address = address;
    	this.port = port;
    	this.username = username;
    	this.sensors = new ArrayList<Sensor>();
    	
    	this.parser = new XmlMetaParser(this);
    }
    
    // GETTERS AND SETTERS
    public String getAddress () {
        return address;
    }

    public int getPort () {
        return port;
    }

    public java.util.ArrayList<Sensor> getSensorList () {
        return sensors;
    }

    public String getUsername () {
        return username;
    }
    
    public XmlMetaParser getXmlMetaParser(){
    	return parser;
    }

}

