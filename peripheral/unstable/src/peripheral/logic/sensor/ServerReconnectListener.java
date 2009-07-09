package peripheral.logic.sensor;

/**
 * Listener that informs about removed and added sensors after reconnection of
 * a sensor server
 * 
 * @author Berni
 *
 */
public interface ServerReconnectListener {

	/**
	 * is invoked if after reconnect the server has a new registered sensor
	 * @param sensor
	 */
	public void sensorAdded(Sensor sensor);
	
	/**
	 * is invoked if after reconnect a before existing sensor has been removed 
	 * @param sensor
	 */
	public void sensorRemoved(Sensor sensor);
	
	/**
	 * is invoked if server finished reconnection process
	 */
	public void reconnectCompleted();
}
