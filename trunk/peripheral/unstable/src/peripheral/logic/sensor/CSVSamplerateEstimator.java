package peripheral.logic.sensor;

public class CSVSamplerateEstimator extends Thread {

	private Sensor sensor;
	
	public CSVSamplerateEstimator(Sensor sensor){
		this.sensor = sensor;
	}
	
	public void run(){
			long currentrate = estimateSamplerate();
			sensor.setSamplerate(currentrate);
	}
	
	private long estimateSamplerate(){
		// TODO Add logic
		return new Long(null);
	}
}
