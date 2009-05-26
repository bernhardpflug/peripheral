package peripheral.logic.sensor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import peripheral.logic.sensor.exception.CSVDataException;
import peripheral.logic.sensor.exception.SensorChannelException;
import peripheral.logic.sensor.exception.SensorServerAddressException;

public class CSVCheckout extends Thread {

	// References
    private Sensor sensor;
    private boolean active;
    
    // String values for HTTPRequests
    private String address;
    private String port;
    
    // Current run's marks
    private String startmark;
    private String stopmark;
    
    // Last run's stopmark
    private String prev_stopmark;

    public CSVCheckout (Sensor sensor) {
    	
    	this.sensor = sensor;
    	
    	this.address = sensor.getServer().getAddress();
    	this.port = sensor.getServer().getPort();
    	
    	// Set markers to "0"
    	startmark = "0";
    	stopmark = "0";
    	prev_stopmark ="0";
    }
    
    public void run(){
    	
    	// Set Thread's active flag
    	active = true;
    	    	
    	while(active){
    		
    		// Convert Sensor's current samplerate from float/s to long/ms 
        	// Needs to be done in here each while iteration to support variable sampling rate
    		float samplerate = sensor.getSamplerate()*1000;
    		long millisec = (long) samplerate;
    		
    		// Get startmark string
        	startmark = sensor.getStartmark();
    		
    		try{
    			
            	// Find a startmark if startmark of sensor == ""
            	if(startmark.compareTo("0")==0){
            		startmark = getLastMark();
            		prev_stopmark = startmark;	// Set to startmark as otherwise parseMeasToQueue() adds initial corrupt measurements
            	}
        
    			// Get new Measurements for each Channel and set new sensor startmark to mark of last reveived measurement,
        		// which is done in parseMeasToQueue Method
        		
        		for(SensorChannel channel : sensor.getSensorChannels()){
      
        			BufferedReader reader = checkout(channel);
        			parseMeasToQueue(channel, reader);
            	}
        		
    		} catch (SensorServerAddressException e){
    			System.err.println("[" + sensor.getName() + "] - " + e.getMessage());
    		} catch (SensorChannelException e) {
    			System.err.println("[" + sensor.getName() + "] - " + e.getMessage());
			} catch (CSVDataException e) {
				System.err.println("[" + sensor.getName() + "] - " + e.getMessage());
			} finally{
    		
    			// Set Markers with mark of last acquired measurement and marker for stopmark of 
				// last run to avoid duplicate entries if no new measurements arrived on server
    			sensor.setStartmark(stopmark);
    			prev_stopmark = stopmark;
        		
    			try{
    				Thread.sleep(millisec);
    			} catch (InterruptedException e){
    				e.printStackTrace();
    			}
    			
        		try{
        			System.out.println("[" + sensor.getName() + "] - " + "New Startmark: " + sensor.getStartmark());

        			System.out.println("[" + sensor.getSensorChannels().get(0).getFullname() + " - MID: " + sensor.getSensorChannels().get(0).getMid() + "] - " 
        					+ "Number of Measurements:" + sensor.getSensorChannels().get(0).getMeasQueue().size());
            		System.out.println("[" + sensor.getSensorChannels().get(1).getFullname() + " - MID: " + sensor.getSensorChannels().get(1).getMid() + "] - "
            				+ "Number of Measurements:" + sensor.getSensorChannels().get(1).getMeasQueue().size());
            		System.out.println("[" + sensor.getSensorChannels().get(2).getFullname() + " - MID: " + sensor.getSensorChannels().get(2).getMid() + "] - "
            				+ "Number of Measurements:" + sensor.getSensorChannels().get(2).getMeasQueue().size());
            		System.out.println();
            		
        		}catch (NullPointerException e){
        			
        		}catch (IndexOutOfBoundsException e){
    				
    			}
    		}
    	}
    }
    
    private String getLastMark() throws SensorChannelException, SensorServerAddressException {
    	
    	// Setup URL
    	URL url;
    	URLConnection conn;
    	DataInputStream is;
    	
    	// Setup BufferedReader to read InputStream
    	BufferedReader reader;
    	
    	// Return String
    	String result = "-1";
    	
    	try{
        	// get MID, throws ArrayIndexOutOfBoundsException if Server probe is processed,
    		// as server probe does not have any probes
        	String mid = Long.toString(sensor.getSensorChannels().get(0).getMid());
    		
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
			
    	} catch (MalformedURLException e){
    		throw new SensorServerAddressException("Please enter a valid URL: " + e.getMessage());
    	} catch (IOException e) {
    		throw new SensorServerAddressException("Error retrieving data from URLConnection: " + e.getMessage());
		} catch (IndexOutOfBoundsException e){
			throw new SensorChannelException("No SensorChannels available: " + e.getMessage());
		}
    	
    	return result;
   
    }
    
    private BufferedReader checkout(SensorChannel channel) throws SensorServerAddressException, CSVDataException {
    	
    	// Setup URL
    	URL url;
    	URLConnection conn;
    	DataInputStream is;
    	
    	try{
    		
    		// String MID
        	String mid = Long.toString(channel.getMid());
    		
        	// Increase Startmark with 1
        	long mark = Long.parseLong(startmark);
        	mark = mark++;
        	startmark = Long.toString(mark);
    		
        	if(sensor.getSensorChannels().get(0).equals(channel)){
        		url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid + "&startMark=" + startmark);
        	}else{
        		url = new URL(address + ":" + port + "/nrss/data/csv?mid=" + mid + "&startMark=" + startmark + "&stopMark=" + stopmark);
        	}
    		
    		// Establish URLConnection
			conn = url.openConnection();
			is = new DataInputStream(conn.getInputStream());
			
			// return BufferedReader
			return new BufferedReader(new InputStreamReader(is));
			
    	} catch(MalformedURLException e){
    		throw new SensorServerAddressException("Please enter a valid URL: " + e.getMessage());
    	} catch (IOException e) {
    		throw new SensorServerAddressException("Error retrieving data from URLConnection: " + e.getMessage());
		} catch (NumberFormatException e){
			throw new CSVDataException("No Measurements performed for 24h, set Startmark manually.");
		}
    }
    
    private void parseMeasToQueue(SensorChannel channel, BufferedReader reader){
    	
    	// Temp Strings
		String strLine;
		String[] fullmeas = {""};
		
		try {
			
			// Check for new Measurements
			long stop = Long.parseLong(prev_stopmark);
			long current;
			
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
			
			// Set stopmark if first channel measurements are getting acquired
			// By doing so, each channel will get the same amount of measurements
			if(sensor.getSensorChannels().get(0).equals(channel)){
				stopmark = fullmeas[5];
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("[" + sensor.getName() + "] - " + "Nothing parsed: " + e.getMessage());
		}
    }
    
    public void setActive(boolean active){
    	this.active = active;
    }
}

