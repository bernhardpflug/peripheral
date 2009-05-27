package peripheral.logic.sensor;

import java.util.ArrayList;
import java.util.TreeMap;

public class CreateDummySensorEnvironment {

	public static void main(String[] args) {
		
		// Create Server
		SensorServer server = new SensorServer("http://127.0.0.1","8080", "admin");
		
		// Create Sensor
		Sensor sensor = new Sensor(13, "Dummy Sensor", server);
		
		// Create SensorChannel channel1
		SensorChannel channel1 = new SensorChannel(34, "admin:Dummy Sensor:Dummy Stim:dummychannel1", sensor);
		
		// Create SensorChannel channel1
		SensorChannel channel2 = new SensorChannel(32, "admin:Dummy Sensor:Dummy Stim:dummychannel2", sensor);
		
		// Create Metadata for channel1
		TreeMap<String, String> metadata1 = channel1.getMetadata();
		metadata1.put("shortname", "AirTemp");
		metadata1.put("description", "Air Temperature");
		metadata1.put("datatype", "Integer8");
		metadata1.put("units", "Degree Celcius");
		metadata1.put("location", "In your face bitch");
//		metadata1.put("upperlimit", "");
//		metadata1.put("lowerlimit", "");
		
		// Create Metadata for channel2
		TreeMap<String, String> metadata2 = channel2.getMetadata();
		metadata2.put("shortname", "SkyCover");
		metadata2.put("description", "Percentage of Sky Cover");
		metadata2.put("datatype", "Integer8");
		metadata2.put("units", "Percent");
		metadata2.put("location", "In your face bitch");
		metadata2.put("upperlimit", "100");
		metadata2.put("lowerlimit", "0");
		
		// Add channels to Sensor
		ArrayList<SensorChannel> channellist = sensor.getSensorChannels();
		channellist.add(channel1);
		channellist.add(channel2);
		
		// Add Sensor to Server
		ArrayList<Sensor> sensorlist = server.getSensorList();
		sensorlist.add(sensor);
		
		// Print out
		for(Sensor s : server.getSensorList()){
			System.out.println("############## <SENSOR> ##############");
			System.out.println("Sensor: " + s.getName());
			System.out.println("PID: " + s.getPid());
			System.out.println("Sample rate: " + s.getSamplerate() + "\n");
			
			for(SensorChannel c : s.getSensorChannels()){
				System.out.println("	Channel: " + c.getFullname());
				System.out.println("	MID: " + c.getMid());
				
				TreeMap<String, String> meta = c.getMetadata();
				
				for(String key : meta.keySet()){
					System.out.println("	" + key + ": " + meta.get(key));
				}
				
				System.out.println();
				
			}
			System.out.println("############## </SENSOR> ##############");
		}
	}

}
