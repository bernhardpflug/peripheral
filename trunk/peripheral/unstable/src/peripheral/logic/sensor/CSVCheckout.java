package peripheral.logic.sensor;


public class CSVCheckout extends Thread {

    private Sensor sensor;
    private float samplerate;

    public CSVCheckout (Sensor sensor) {
    	this.sensor = sensor;
    	this.samplerate = sensor.getSamplerate();
    }
    
    public void run(){
    	while(true){
    		try {
    			this.getNewMeas(null, null);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    // GETTERS AND SETTERS
    public void getNewMeas (SensorChannel channel, String startmark) {
    }

    public Sensor getSensor () {
        return sensor;
    }

}

