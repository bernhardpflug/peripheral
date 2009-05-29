package peripheral.logic.sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class SensorServer extends Observable implements Serializable {

    private String address;
    private String port;
    private String username;
    private ArrayList<Sensor> sensors;
    
    private status connectionStatus;
    
    public static enum status {
    	offline,
    	connecting,
    	online,
    	error
    };

    public SensorServer (String address, String port, String username) {
    	this.address = address;
    	this.port = port;
    	this.username = username;
    	this.sensors = new ArrayList<Sensor>();
    	
    	connectionStatus = status.offline;
    }
    
    public void reconnect(){
    	sensors = new ArrayList<Sensor>();
    	
    	XmlMetaParser parser = new XmlMetaParser(this);
    	parser.start();
    }
    
    public void connect(){
    	XmlMetaParser parser = new XmlMetaParser(this);
    	parser.start();
    }
    
    // GETTERS AND SETTERS
    public String getAddress () {
        return address;
    }

    public String getPort () {
        return port;
    }

    public java.util.ArrayList<Sensor> getSensorList () {
        return sensors;
    }

    public String getUsername () {
        return username;
    }

	public status getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(status conn_status) {
		this.connectionStatus = conn_status;
		setChanged();
		notifyObservers(new ServerConnectionStatusChangedEvent());
	}

}

