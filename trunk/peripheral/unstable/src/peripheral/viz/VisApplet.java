package peripheral.viz;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.Logging;
import peripheral.logic.datatype.Interval;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.value.SensorValue;
import peripheral.visualization.Visualization;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class VisApplet extends PApplet implements Visualization, Observer {

    private static final long serialVersionUID = 1L; //	whatever that's needed for
    private Vector<VisSymbol> symbols;
    private PImage bgImage;
    private Dimension visDim;
    //private boolean setup = false;
    private boolean initialized = false;

    private Map<SensorChannel, String> logStrings = new HashMap<SensorChannel, String>();


    public VisApplet() {
        symbols = new Vector<VisSymbol>();
    }

    @Override
    public void setup() {
        //size(screen.width, screen.height, OPENGL);
        //size(1680, 1050);
        size(screen.width, screen.height);
        //size(1920, 1080, OPENGL);
        //SwingUtilities.invokeLater(
        //      new Runnable() {

        //        public void run() {
        //if (!setup){
        peripheral.logic.Runtime.getInstance().startup(VisApplet.this, "/users/Berni/A_new_config.zip");
        MyTestThread t = new MyTestThread(DisplayConfiguration.getInstance(), (Visualization) this);
        ProcessingThread pt = new ProcessingThread(symbols,50);
        pt.start();
        //t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        //System.out.println("startup called");
        //setup = true;
        //}
        //      }
        //});*/

        //size((int) screen.getWidth(), (int) screen.getHeight(), P3D);
        background(100);
        frameRate(60);
        noStroke();

        PFont font = createFont("Arial", 20);
        textFont(font);
    //Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void draw() {
        if (!initialized) {
            return;
        }
        //TODO: interpolate coords to actual screen resolution
        background(bgImage);
        //background(100);
        VisSymbol ptr;
        for (int i = 0; i < symbols.size(); i++) {
            ptr = symbols.get(i);
            //ptr.calcStep();
            //draw the image in all its aspects
            tint((255.f * ptr.getBrightness()), (255.f * ptr.getAlpha()));
            pushMatrix();
            translate((float) ptr.getPositionIpl().getX() + ptr.getScaledWidth() / 2, (float) ptr.getPositionIpl().getY() + ptr.getScaledHeight() / 2);
            scale(ptr.getScaleXIpl(), ptr.getScaleYIpl());
            imageMode(CENTER);
            rotate(radians(ptr.getAngleIpl()));//, (float)ptr.getPositionIpl().getX(), (float) ptr.getPositionIpl().getY(), 0);
            image(ptr.getImg(), 0, 0);
            if (ptr.getImgSwap() != null) {
                tint((255.f * ptr.getBrightness()), (255.f * ptr.getAlphaSwap()));
                image(ptr.getImgSwap(), 0, 0);
            }
            popMatrix();
        }

        text(frameRate + " fps", 0, 80);
        int y = 40;
        for (String s : this.logStrings.values()){
            text(s, 0, y);
            y+=20;
        }

    }

    public void addSymbol(Symbol s, Region region) {
        VisSymbol vs = new VisSymbol(s, region);
        vs.setImg(loadImage(s.getFile().getPath()));
        vs.setFadeIn(true);
        symbols.add(vs);
    }

    public void brightness(Symbol s, float amount) {
        VisSymbol vs = findVSforS(s);
        vs.setBrightness(amount);
    }

    public void contrast(Symbol s, float amount) {
        // TODO Auto-generated method stub
    }

    public void hideSymbol(Symbol s) {
        VisSymbol vs = findVSforS(s);
        vs.setFadeOut(true);
        vs.setVisible(false);
    }

    public void removeSymbol(Symbol s) {
        VisSymbol vs = findVSforS(s);
        vs.addObserver(this);
        vs.setFadeOut(true);
        //symbols.remove(vs);

    }

    public void rotateSymbol(Symbol s, float angle) {
        s.setAngle(angle);
    }

    public void scaleSymbol(Symbol s, float factorX, float factorY) {
        s.setScaleX(factorX);
        s.setScaleY(factorY);
    }

    public void showSymbol(Symbol s) {
        VisSymbol vs = findVSforS(s);
        vs.setFadeIn(true);
        vs.setVisible(true);

    }

    public void swapSymbol(Symbol s, String filename) {
        VisSymbol vs = findVSforS(s);
        PImage tempImg = loadImage(filename);
        vs.setImgSwap(tempImg);
    }

    private VisSymbol findVSforS(Symbol s) {
        for (int i = 0; i < symbols.size(); i++) {
            VisSymbol vs = symbols.get(i);
            if (vs.getSymbol() == s) {
                return vs;
            }
        }
        System.out.println("findVSforS: no suitable symbol found.");
        return null;
    }

    public void init(String backgroundImageFilename, Dimension resolution) {

        bgImage = loadImage(backgroundImageFilename);
        visDim = resolution;
        bgImage.resize(screen.width, screen.height);

        initialized = true;
    }

    public void translateSymbol(Symbol s, java.awt.Point targetPosition) {
        s.setPosition(targetPosition);

    }

    public void update(Observable o, Object arg) {
        if (arg instanceof VisSymbol.Event){
            VisSymbol.Event event = (VisSymbol.Event)arg;
            VisSymbol symbol = (VisSymbol)o;

            if (event == VisSymbol.Event.FadeOutCompleted){
                symbol.deleteObserver(this);
                symbols.remove(symbol);
            }
        }
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //@todo: read configuration-filename from args

        //Runtime.getInstance().startup("displayConfig.ser");
        PApplet.main(new String[]{"--present", "peripheral.viz.VisApplet", "test"});

        try {
            System.out.println("Press <Enter> to terminate visualization.");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    //Runtime.getInstance().shutdown();
    }

    class ProcessingThread extends Thread {

        private Vector<VisSymbol> symbols;
        private long updaterate;

        private long previousStep;

        private boolean runFlag = true;

        public ProcessingThread(Vector<VisSymbol> symbols, int updaterate) {
            this.symbols = symbols;
            this.updaterate = updaterate;

            this.setPriority(Math.min(Thread.currentThread().getPriority()+1, Thread.MAX_PRIORITY));

            previousStep = System.currentTimeMillis();
        }

        public void stopThread() {
            runFlag = false;
        }

        @Override
        public void run() {

            while (runFlag) {
                synchronized (symbols) {
                    for (VisSymbol symbol : symbols) {
                        symbol.calcStep();
                    }
                }
                try {
                    System.out.println("Difference since last call: "+(System.currentTimeMillis()-previousStep));
                    previousStep = System.currentTimeMillis();
                    System.out.println("Waiting "+((1f / (float)updaterate)*1000)+" milliseconds");
                    Thread.sleep((long)(1f / (float)updaterate)*1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class MyTestThread extends Thread {

        Visualization viz;
        DisplayConfiguration config;

        public MyTestThread(DisplayConfiguration config, Visualization viz) {
            this.viz = viz;
            this.config = config;
        }

        public void run() {
            Random r = new Random();
            /*Symbol symbol = ((PositioningTool)config.getAdapter().get(0).getTool()).getSymbols().get(0);
            Symbol symbol2 = ((PositioningTool)config.getAdapter().get(0).getTool()).getSymbols().get(1);
            init(config.getBackgroundImageFile().getPath(), config.getDimension());
            viz.addSymbol(symbol, null);
            symbol2.setPosition(new java.awt.Point(10, 10));
            viz.addSymbol(symbol2, null);
            try{
            sleep(4000);
            //for (int i=0; i<=360; i++){
            translateSymbol(symbol, new java.awt.Point(800, 800));
            sleep(3000);
            rotateSymbol(symbol, 360);
            swapSymbol(symbol, symbol2.getFile().getPath());
            //translateSymbol(symbol2, new java.awt.Point(1000, 900));
            scaleSymbol(symbol2, 0.5f, 0.5f);

            sleep(500);
            }catch (Exception e){e.printStackTrace();}
            scaleSymbol(symbol2, 0.2f, 0.8f);
            //}*/

            while (true) {
                for (Sensor s : peripheral.logic.Runtime.getInstance().getSensors()) {
                    for (SensorChannel c : s.getSensorChannels()) {
                        Object actValue = null;
                        if (Number.class.isAssignableFrom(c.getDatatype())) {
                            Interval bounds = c.getBounds();
                            int iVal = 0;
                            double dVal = 0.0;
                            if (c.getDatatype().equals(Integer.class)) {
                                iVal = Math.abs(r.nextInt());
                                if (bounds != null) {
                                    iVal = (int) bounds.getLowerBound() + (int) (iVal % bounds.getUpperBound());
                                }
                                actValue = iVal;
                            } else {
                                dVal = Math.abs(r.nextFloat());
                                if (bounds != null) {
                                    dVal = bounds.getLowerBound() + (dVal % bounds.getUpperBound());
                                }
                                actValue = dVal;
                            }

                            String logString = "simulated value for channel " + c.getFullname() + ": " + actValue;
                            Logging.getLogger().finer(logString);
                            logStrings.put(c, logString);
                        }
                        for (SensorValue v : c.getSensorValues()) {
                            v.setActValue(actValue);
                        }
                    }
                    peripheral.logic.Runtime.getInstance().setSensorChanged(s);
                }
                try {
                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
