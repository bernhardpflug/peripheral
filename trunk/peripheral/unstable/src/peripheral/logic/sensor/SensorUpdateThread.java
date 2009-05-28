package peripheral.logic.sensor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import peripheral.logic.value.SensorValue;

public class SensorUpdateThread extends Thread implements Observer{

    private float samplingRate;
    private Sensor sensor;

    public SensorUpdateThread(Sensor sensor) {
        sensor.addObserver(this);
    }

    public float getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate(float val) {
        this.samplingRate = val;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor val) {
        this.sensor = val;
    }

    /**
     *  <p style="margin-top: 0">
     *    per tick:
     *  <br>- getValue from SensorChannel-Queue
     *      </p>
     *      <p style="margin-top: 0">
     *    
     *      </p>
     *      <p style="margin-top: 0">
     *    - run through SensorValue-List in SensorChannel and call setValue 
     *    (actVal)<br>
     *      </p>
     */
    public void run() {
        boolean sensorChanged;

        while (true) {
            if (isInterrupted()){
                break;
            }

            sensorChanged = false;

            //run through all channels of the sensor and change the act-value of
            //the assigned sensor values
            for (SensorChannel channel : sensor.getSensorChannels()) {
                Object actValue = channel.getMeasQueue().poll();
                if (actValue != null) {
                    sensorChanged = true;
                    List<SensorValue> sensorValues = null; //@todo: = channel.getSensorValues();
                    for (SensorValue sv : sensorValues) {
                        sv.setActValue(actValue);
                    }
                }
            }

            if (sensorChanged) {
                peripheral.logic.Runtime.getInstance().setSensorChanged(sensor);
            }
            
            try {
                //unit of samplingRate??
                sleep((long) (samplingRate * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt();
            }
        }
    }

    public void update(Observable o, Object arg) {
    	// TODO SensorSamplingRateChanged gibts nimmer homma gsogt, oder? Hab an error griagt und glšscht - Cheers tobi
    	
//        if (arg instanceof SensorSamplingRateChanged) {
//            samplingRate = ((SensorSamplingRateChanged)arg).getSamplerate();
//        } else if (arg instanceof SensorSamplingStarted) {
//            this.start();
//        }
    }
}

