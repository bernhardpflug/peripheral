package peripheral.logic.sensor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import peripheral.logic.Logging;
import peripheral.logic.sensor.exception.SensorChannelException;
import peripheral.logic.sensor.exception.SensorServerAddressException;

public class CSVStartmarkEstimator {
	
	private Sensor sensor;

	public CSVStartmarkEstimator(Sensor sensor){
		this.sensor = sensor;
	}
	
	public String getLatestmark() throws SensorServerAddressException, SensorChannelException {
		
		Logging.getLogger().finest("Parsing startmark in CSV File for " + sensor.getName());
		
    	// Setup URL
    	URL url;
    	URLConnection conn;
    	DataInputStream is;
    	
    	// Setup BufferedReader to read InputStream
    	BufferedReader reader;
    	
    	// Return String
    	String result = "0";
    	
    	String address = sensor.getServer().getAddress();
    	String port = sensor.getServer().getPort();
    	String mid;
    	
    	try{
        	// get MID, throws ArrayIndexOutOfBoundsException if Server probe is processed,
    		// as server probe does not have any probes
        	mid = Long.toString(sensor.getSensorChannels().get(0).getMid());
    		
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
    		
    		reader.close();
    		
    		result = resultLine[5];
    		
    		Logging.getLogger().finest("Startmark for sensor " + sensor.getName() + ": " + result);
    		
    	} catch (MalformedURLException e){
    		throw new SensorServerAddressException("Please enter a valid URL: " + e.getMessage());
    	} catch (IOException e) {
    		throw new SensorServerAddressException("Error retrieving data from URLConnection: " + e.getMessage());
		} catch (IndexOutOfBoundsException e){
			throw new SensorChannelException("No SensorChannels available: " + e.getMessage());
		}
		
		return result;
	}
	
}
