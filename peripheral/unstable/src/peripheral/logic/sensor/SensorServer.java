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
    
    private ArrayList<ServerReconnectListener> updateListeners;
    
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
    	
    	updateListeners = new ArrayList<ServerReconnectListener>();
    }
    
    public void reconnect(){
    	
    	XmlMetaParser parser = new XmlMetaParser(this);
    	parser.start();
    	
    	
    }
    
    public void connect(){
    	XmlMetaParser parser = new XmlMetaParser(this);
    	parser.start();
    	
    }
    
    /**
     * is called by xml meta parser if update completed
     */
    public void updateCompleted(XmlMetaParser parser) {
    	this.updateSensors(parser.getSensorList());
    	this.fireReconnectCompleted();
    }

    public boolean equals(Object obj) {

        if (obj instanceof SensorServer) {
            SensorServer server = (SensorServer)obj;

            if (this.address.equals(server.address) && this.port.equals(server.port)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return super.equals(obj);
        }
    }
    
    /**
     * compares and updates old with new sensor list (after reconnect)
     * and fires events in case of changes
     * @param newSensorList
     */
    private void updateSensors(ArrayList<Sensor> newSensorList) {
    	
    	ArrayList<Sensor> toDel = new ArrayList<Sensor>();
    	
    	for (Sensor sensor : sensors) {
    		
    		//check whether sensor from old list is still in new one
    		if (!newSensorList.contains(sensor)) {
    			toDel.add(sensor);
    		}
    		//if sensor is still in new list remove it from new list
    		//so that all remaining elements after this iteration are
    		//new added sensors
    		else {
    			newSensorList.remove(sensor);
    		}
    	}
    	
    	//delete all removed sensors
    	for (Sensor sensor : toDel) {
    		sensors.remove(sensor);
    		this.fireSensorRemoved(sensor);
    	}
    	
    	//add all remaining sensors from new list as new sensors
    	for (Sensor sensor : newSensorList) {
    		sensors.add(sensor);
    		this.fireSensorAdded(sensor);
    	}
    }
    
    /*
	 * Sensor update event methods
	 */
	
	public void addServerReconnectListener(ServerReconnectListener listener) {
		if (!this.updateListeners.contains(listener)) {
			this.updateListeners.add(listener);
		}
	}

	public void removeServerReconnectListener(ServerReconnectListener listener) {
		this.updateListeners.remove(listener);
	}

	public void fireSensorAdded(Sensor sensor) {
		System.out.println("Server "+this.address+" added new sensor "+sensor.getName());
		for (ServerReconnectListener listener : this.updateListeners) {
			listener.sensorAdded(sensor);
		}
	}

	public void fireSensorRemoved(Sensor sensor) {
		System.out.println("Server "+this.address+" removed sensor "+sensor.getName());
		for (ServerReconnectListener listener : this.updateListeners) {
			listener.sensorRemoved(sensor);
		}
	}
	
	public void fireReconnectCompleted() {
		for (ServerReconnectListener listener : this.updateListeners) {
			listener.reconnectCompleted();
		}
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

