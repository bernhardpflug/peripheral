package peripheral.logic.sensor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Stack;

public class CSVSamplerateEstimator extends Thread {

	private Sensor sensor;
	
	// Number of measurements N over which mean samplerate is calculated
	private static final int elements = 20;
	
	public CSVSamplerateEstimator(Sensor sensor){
		this.sensor = sensor;
	}
	
	public void run(){
			calculateFromCSVFile();
	}
	
	private void calculateFromCSVFile(){
		
		Stack<Long> timestamps = new Stack<Long>();
		Stack<Long> temp = new Stack<Long>();
		int count = 0;
		
		while(count<elements){
			temp = pushNewMeasOnStack(timestamps);
			timestamps = temp;
			count = timestamps.capacity();
		}
		
		// sample rate mean
		long mean = 0;
		long tmp = 0;
		
		for(int i = 0; i < elements; i++){
			if(timestamps.capacity()>1){
				long pop = timestamps.pop();
				long peek = timestamps.peek();
				long diff = pop - peek;
				tmp = tmp + diff;
			}
		}
		
		mean  = tmp/(elements);
		
		System.err.println("[SAMPLERATE] - " + "Newly Calculated Samplerate: " + mean + "\n");
		
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
	}
	
	private Stack<Long> pushNewMeasOnStack(Stack<Long> timestamps){
		
		// Strings for url
		String address = sensor.getServer().getAddress();
		String port = sensor.getServer().getPort();
		
		// Establish connections
		URL url;
		URLConnection conn;
		DataInputStream is;
		
		// Create Instance of BufferedReader to read inputstream
		BufferedReader reader;
		
		try {
			
			String mid = String.valueOf(sensor.getSensorChannels().get(0).getMid());
			
			// TODO Set back to sensor.getStartmark()
//			url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid + "&startMark=" + sensor.getStartmark());
			url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid + "&startMark=" + "10000");
			conn = url.openConnection();
			is = new DataInputStream(conn.getInputStream());
			
			reader = new BufferedReader(new InputStreamReader(is));
			
			String strLine;
			reader.readLine();	// To get rid of header line
			
			while((strLine = reader.readLine()) != null){
				
				// Split line
				String items[] = strLine.split(",");
				
				// Get Time column value
				String raw = items[1];
				
				// Get Calendar fields from raw string
				// 2009-05-02 10:06:17.436
				int year = Integer.parseInt(raw.substring(0,4));
				int month = Integer.parseInt(raw.substring(5,7)); 
				int day = Integer.parseInt(raw.substring(8,10));
				int hour = Integer.parseInt(raw.substring(11,13));
				int min = Integer.parseInt(raw.substring(14, 16));
				int sec = Integer.parseInt(raw.substring(17, 19));
				int millis = Integer.parseInt(raw.substring(20,23));
				
				// Create Calendar Instance to easily derive time in miliseconds, 
				// add millis to it and the calculate timestamps difference to infer samplerate
				Calendar cal = Calendar.getInstance();
				cal.set(year, month-1, day, hour, min, sec);
				
				long timestamp = cal.getTimeInMillis() + millis;
				timestamps.push(timestamp);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return timestamps;
	}
}
