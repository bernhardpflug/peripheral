package peripheral.logic.sensor;

public class CreateInstancesFromServerXML {
	
	public static void main(String[] args) {
		
		// Create Server
		SensorServer server = new SensorServer("http://127.0.0.1","8080", "admin");
		server.connect();
		
		DummyQueueReader dummyreader;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(Sensor sensor : server.getSensorList()){
			if(sensor.getPid() == 13){
				dummyreader = new DummyQueueReader(sensor);
				sensor.startCheckout();
			}
		}
	}
}
