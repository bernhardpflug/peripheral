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

import peripheral.logic.Logging;

public class CSVSamplerateEstimator extends Thread {

	private Sensor sensor;
	
	// Number of measurements N over which mean samplerate is calculated
	private static final int elements = 5;
	
	public CSVSamplerateEstimator(Sensor sensor){
		this.sensor = sensor;
	}
	
	public void run(){
		
			Logging.getLogger().finest("Calculating samplerate for " + sensor.getName());
		
			String startmark = sensor.getStartmark();
			calculateFromCSVFile(startmark);
			
			// Activate buffering if samplerate is below 1.0f
			if(sensor.getSamplerate()<1.0f){
				sensor.setPollingrate(sensor.getSamplerate()*10);
			}else{
				sensor.setPollingrate(sensor.getSamplerate());
			}
			
			// Notify observers to start sampling
			sensor.samplingStarted();
			
	}
	
	private void calculateFromCSVFile(String startmark){
		
		Stack<Long> timestamps = new Stack<Long>();
		
		do {
			timestamps = getNewMeasStack(startmark);
			
			try {
				Thread.sleep( (long) sensor.getPollingrate()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while (timestamps.size()<elements+1);
		
		// sample rate mean
		long mean = 0;
		long tmp = 0;
		
		for(int i = 0; i < elements; i++){
			long pop = timestamps.pop();
			long peek = timestamps.peek();
			long diff = pop - peek;
			tmp = tmp + diff;
		}
		
		mean  = tmp/(elements);
		
		sensor.setSamplerate((float)mean/1000);
		
		Logging.getLogger().finest("Calculated samplerate for " + sensor.getName() + ": " + sensor.getSamplerate());
		
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
		// 2009-05-02 10:06:17.436
	}
	
	private Stack<Long> getNewMeasStack(String startmark) {
		
		// Strings for url
		String address = sensor.getServer().getAddress();
		String port = sensor.getServer().getPort();
		
		// Establish connections
		URL url;
		URLConnection conn;
		DataInputStream is;
		
		// Create Instance of BufferedReader to read inputstream
		BufferedReader reader;
		
		// Stack to return
		Stack<Long> resultstack = new Stack<Long>();
		
		try {
			
			String mid = String.valueOf(sensor.getSensorChannels().get(0).getMid());
			
			// TODO Set back to sensor.getStartmark()
			url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid + "&startMark=" + startmark);
//			url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid + "&startMark=" + "10000");
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
				resultstack.push(timestamp);
			}
			
			reader.close();
			
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} catch (IndexOutOfBoundsException e) {
		}
		
		return resultstack;
	}
}
