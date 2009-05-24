package peripheral.logic.sensor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CSVCheckout extends Thread {

	// References
    private Sensor sensor;
    private boolean active;
    
    // String values for HTTPRequests
    private String address;
    private String port;
    
    // Current run's marks
    private String startmark;
    private String temp_stopmark;
    
    // Last run's startmark
    private String prev_stopmark;

    public CSVCheckout (Sensor sensor) {
    	this.sensor = sensor;
    	
    	this.address = sensor.getServer().getAddress();
    	this.port = sensor.getServer().getPort();
    	
    	// Set markers to ""
    	prev_stopmark ="0";
    	temp_stopmark = "0";
    	startmark = "0";
    }
    
    public void run(){
    	
    	// Set Thread's active flag
    	active = true;
    	
    	System.out.println("Channel " + sensor.getSensorChannels().get(0).getFullname() + " - Number of Measurements:" + sensor.getSensorChannels().get(0).getMeasQueue().size());
    	
    	while(active){
    		
        	// Get startmark string
        	startmark = sensor.getStartmark();
        	
        	// Evaluate startmark if ""
        	if(startmark.compareTo("0")==0){
        		startmark = estimateStartmark();
        		prev_stopmark = startmark;
        	}
    		
    		// Convert Sensor's current samplerate from float/s to long/ms 
        	// Needs to be done in here each while iteration to support variable sampling rate
    		float samplerate = sensor.getSamplerate()*1000;
    		long millisec = (long) samplerate;
    
			// Get new Measurements for each Channel and set new sensor startmark to mark of last reveived measurement,
    		// which is done in parseMeasToQueue Method
    		
    		for(SensorChannel channel : sensor.getSensorChannels()){
  
    			BufferedReader reader = checkout(Long.toString(channel.getMid()), startmark);
    			parseMeasToQueue(channel, reader);
        	}
    		
    		// Set Markers with mark of last acquired measurement
    		prev_stopmark = temp_stopmark;
			sensor.setStartmark(temp_stopmark);
    		
    		System.out.println("New Startmark of Sensor: " + sensor.getStartmark());
    		
    		try{
    			System.out.println("Channel " + sensor.getSensorChannels().get(0).getFullname() + " - Number of Measurements:" + sensor.getSensorChannels().get(0).getMeasQueue().size());
        		System.out.println("Channel " + sensor.getSensorChannels().get(1).getFullname() + " - Number of Measurements:" + sensor.getSensorChannels().get(1).getMeasQueue().size());
        		System.out.println("Channel " + sensor.getSensorChannels().get(2).getFullname() + " - Number of Measurements:" + sensor.getSensorChannels().get(2).getMeasQueue().size());
        		
        		Thread.sleep(millisec);
        		
    		}catch (NullPointerException e){
    			System.out.println("No measurements available yet. Please be patient...");
    		}catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    private String estimateStartmark(){
    	
    	// get MID
    	String mid = Long.toString(sensor.getSensorChannels().get(0).getMid());
    	
    	// Setup URL
    	URL url;
    	URLConnection conn;
    	DataInputStream is;
    	
    	// Setup BufferedReader to read InputStream
    	BufferedReader reader;
    	
    	// Return String
    	String result = "-1";
    	
    	try{
    		url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid);
    		
    		// Establish URLConnection
			conn = url.openConnection();
			is = new DataInputStream(conn.getInputStream());
			
			// Instantiate BufferedReader
			reader = new BufferedReader(new InputStreamReader(is));
	    		
    		// Temp Strings
    		String strLine;
    		String[] resultLine = {""};
    		
    		// Go to last line of retrieved CSV File
    		while((strLine = reader.readLine()) != null){
    			resultLine = strLine.split(",");
    		}
    		
    		result = resultLine[5];
			
    	}catch(MalformedURLException e){
    		System.out.println("Please enter a valid URL: " + e.getMessage());
    	} catch (IOException e) {
    		System.out.println("Could not establish URLConnection: " + e.getMessage());
		}
    	
    	return result;
    }
    
    private BufferedReader checkout(String mid, String startmark ){
    	
    	// Setup URL
    	URL url;
    	URLConnection conn;
    	DataInputStream is;
    	
    	// Increase Startmark with 1
    	long mark = Long.parseLong(startmark);
    	mark = mark++;
    	startmark = Long.toString(mark);
    	
    	try{
    		url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid + "&startMark=" + startmark);
    		
    		// Establish URLConnection
			conn = url.openConnection();
			is = new DataInputStream(conn.getInputStream());
			
			// return BufferedReader
			return new BufferedReader(new InputStreamReader(is));
			
    	}catch(MalformedURLException e){
    		System.out.println("Please enter a valid URL: " + e.getMessage());
    		return null;
    	} catch (IOException e) {
    		System.out.println("Could not establish URLConnection: " + e.getMessage());
    		return null;
		}
    }
    
    private void parseMeasToQueue(SensorChannel channel, BufferedReader reader){
    	
    	// Temp Strings
		String strLine;
		String[] fullmeas = {""};
		
		// Check for new Measurements
		long stop = Long.parseLong(prev_stopmark);
		long current;
		
		try {
			
			// Read header line and get rid of it
			strLine = reader.readLine();
			
			while((strLine = reader.readLine()) != null){
				
				// Parse String
				fullmeas = strLine.split(",");
				
				// Parse to Double current
				current = Long.parseLong(fullmeas[5]);
				
				// Create new Measurement
				Measurement newmeas = new Measurement(fullmeas[5], fullmeas[4], fullmeas[2]);
				
				// Add new Measurement to MeasQueue if mark is not equal to last runs startmark
				if(stop<current){
					channel.getMeasQueue().add(newmeas);
				}
			}
			
			// Set temporary stopmark - final stopmark for this channel iteration is set after all channel's measurements have been acquired
			temp_stopmark = fullmeas[5];
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setActive(boolean active){
    	this.active = active;
    }
}

