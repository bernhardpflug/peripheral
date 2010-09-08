package peripheral.logic.sensor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import peripheral.logic.Logging;
import peripheral.logic.value.SensorValue;

public class SensorUpdateThread extends Thread implements Observer{

    private Sensor sensor;

    public SensorUpdateThread(Sensor sensor) {
        this.sensor = sensor;
        sensor.addObserver(this);
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
        int nrOfNoMeas = 0;

        while (true) {
            if (isInterrupted()){
                break;
            }

            sensorChanged = false;

            //run through all channels of the sensor and change the act-value of
            //the assigned sensor values
            for (SensorChannel channel : sensor.getSensorChannels()) {
                //Logging.getLogger().info("channel " + channel.getFullname() + ", queue size: " + channel.getMeasQueue().size());
                Measurement actMeasurement = channel.getMeasQueue().poll();
                if (actMeasurement != null) {
                    sensorChanged = true;
                    List<SensorValue> sensorValues = channel.getSensorValues();
                    for (SensorValue sv : sensorValues) {
                        //Logging.getLogger().info("act value: " + actMeasurement.getValue());
                        sv.setActValue(actMeasurement.getValue());
                    }
                }
            }

            if (sensorChanged) {
                peripheral.logic.Runtime.getInstance().setSensorChanged(sensor);
            }
            else{
                nrOfNoMeas++;
            }

            if (nrOfNoMeas >= 10){
                peripheral.logic.Runtime.getInstance().setSensorNoChanges(sensor);
                nrOfNoMeas = 0;
            }

            try {
                float samplingRate = sensor.getSamplerate();
                //Logging.getLogger().info("sampling rate: " + (samplingRate * 1000));
                sleep((long) (samplingRate * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt();
            }
        }
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof SensorSamplingStarted) {
            this.start();
            Logging.getLogger().fine("SensorUpdateThread for Sensor " + this.getSensor().getName() + " started.");
        }
    }
}

