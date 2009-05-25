package peripheral.logic.sensor;

import java.util.TreeMap;


public class CreateInstancesFromServerXML {

	public static void main(String[] args) {
		
		// Create Server
		SensorServer server = new SensorServer("http://127.0.0.1","8080", "admin");
		
		// Get Server's XML Parser
		XmlMetaParser xml = server.getXmlMetaParser();
		xml.createInstancesFromXML();

		// Check count of sensors
		System.out.println("Server has " + server.getSensorList().size() + " Sensors.\n");
		
//		for(Sensor sensor : server.getSensorList()){
//			System.out.println("Sensor " + sensor.getName() + " with PID " + sensor.getPid()
//					+ " has " + sensor.getSensorChannels().size() + " Channels:");
//			
//			for(SensorChannel channel : sensor.getSensorChannels()){
//				System.out.println("Channel " + channel.getFullname() + " with MID " + channel.getMid()
//						+ " with " + channel.getMetadata().size() + " Metatags.");
//				
//			}
//			
//		}
		
		for(Sensor sensor : server.getSensorList()){
			if(sensor.getPid() == 12){
				sensor.startCheckout();
			}
		}
	}

}
