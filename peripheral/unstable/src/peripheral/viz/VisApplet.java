package peripheral.viz;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.Logging;
import peripheral.logic.datatype.Interval;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.value.SensorValue;
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
    private String noSensorChanges = "";
    private ProcessingThread pt;
    private MyTestThread t;
    private static final String UNZIP_DIR = "unzip/";
    private final Object symbolsSynObj = new Object();
    private Map<String, PImage> imageCache = new HashMap<String, PImage>();
    private float globalFactor;

    public VisApplet() {
        symbols = new Vector<VisSymbol>();
    }

    @Override
    public void setup() {
        //size(800, 600, OPENGL);
        size(screen.width, screen.height, OPENGL);

        //size(1680, 1050, P2D);
        //size(screen.width, screen.height, P2D);
        //size(200, 200, OPENGL);
        //size(1920, 1080, OPENGL);
        //SwingUtilities.invokeLater(
        //      new Runnable() {

        //        public void run() {
        //if (!setup){
        peripheral.logic.Runtime.getInstance().startup(VisApplet.this, "g:\\rotor1.zip");
        t = new MyTestThread(DisplayConfiguration.getInstance(), (Visualization) this);
        pt = new ProcessingThread(symbols, 50);
        pt.start();
        //t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        //System.out.println("startup called");
        //setup = true;
        //}
        //      }
        //});*/

        //size((int) screen.getWidth(), (int) screen.getHeight(), P3D);
        //background(100);
        //background(bgImage);
        //frameRate(600);
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
        //background(bgImage);
        //background(100);
        //background(bgImage);
        imageMode(CORNER);
        image(bgImage, 0, 0, screen.width, screen.height);
        //camera(width/2.0f, height/2.0f, (height/2.0f) / (float)Math.tan(PI*60.0 / 360.0), width/2.0f, height/2.0f, 1, 0, 1, 1);
        VisSymbol ptr;

        for (int i = symbols.size() - 1; i >= 0; i--) {
            //for (int i=0; i< symbols.size(); i++){
            ptr = symbols.get(i);

            /*if (ptr.getSymbol().getAdapter().getTool() instanceof Region) {
            Rectangle bounds = ((Region) ptr.getSymbol().getAdapter().getTool()).getBounds();
            this.line(bounds.x, bounds.y, bounds.x, bounds.y + bounds.height);
            this.line(bounds.x + bounds.width, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
            }*/
            if (ptr.getSymbol().getAdapter().getTool() instanceof Line) {
                Line l = (Line) ptr.getSymbol().getAdapter().getTool();
                stroke(126);
                line(l.getStartPoint().x * globalFactor, l.getStartPoint().y * globalFactor, l.getEndPoint().x * globalFactor, l.getEndPoint().y * globalFactor);
            }

            //ptr.calcStep();
            //draw the image in all its aspects
            //tint((255.f * ptr.getBrightness()), (255.f * ptr.getAlpha()));

            pushMatrix();
            tint(255, 255 * ptr.getAlpha());
            //translate((float) (ptr.getPositionIpl().getX() + ptr.getScaledWidth() / 2) * globalFactor, (float) (ptr.getPositionIpl().getY() + ptr.getScaledHeight() / 2) * globalFactor);
            translate((float) (ptr.getIplX() + ptr.getScaledWidth() / 2) * globalFactor, (float) ((ptr.getIplY() + ptr.getScaledHeight() / 2) - (ptr.getScaledHeight() - ptr.getImg().height)) * globalFactor);
            scale(ptr.getScaleXIpl() * globalFactor, ptr.getScaleYIpl() * globalFactor);
            imageMode(CENTER);
            rotate(radians(ptr.getAngleIpl()));//, (float)ptr.getPositionIpl().getX(), (float) ptr.getPositionIpl().getY(), 0);
            image(ptr.getImg(), 0, 0);
            if (ptr.getImgSwap() != null) {
                //tint((255.f * ptr.getBrightness()), (255.f * ptr.getAlphaSwap()));
                tint(255, 255 * ptr.getAlphaSwap());
                imageMode(CENTER);
                translate((float) (ptr.getIplX() + ptr.getScaledWidth() / 2) * -globalFactor, (float) ((ptr.getIplY() + ptr.getScaledHeight() / 2) - (ptr.getScaledHeight() - ptr.getImg().height)) * -globalFactor);
                translate((float) (ptr.getIplX() + ptr.getSwapScaledWidth() / 2) * globalFactor, (float) ((ptr.getIplY() + ptr.getSwapScaledHeight() / 2) - (ptr.getSwapScaledHeight() - ptr.getImgSwap().height)) * globalFactor);
                image(ptr.getImgSwap(), 0, 0);
            }
            popMatrix();
        }


        //Logging.getLogger().finest("" + frameRate);
        noTint();
        text(frameRate + " fps", 0, 40);
        int y = 60;
        for (String s : this.logStrings.values()) {
            text(s, 0, y);
            y += 20;
        }
        text(noSensorChanges, 0, y);
    }

    public void addSymbol(Symbol s, Region region) {
        VisSymbol vs = this.findVSforS(s);


        if (vs == null) {
            vs = new VisSymbol(s, region);
            vs.setImg(loadImage(s.getFile().getPath()));
            if (s.getSecondFile() != null){
                vs.setSecondImage(loadImage(s.getSecondFile().getPath()));
            }

            if (s.getAdapter().getAnimator() == null) {
                vs.setFadeIn(true);
                //synchronized (symbolsSynObj) {
                addVisSymbol(vs);
            //}
            } else {
                Logging.getLogger().finer("create visAnimator");
                VisAnimator animator = s.getAdapter().getAnimator().getVisAnimator(this, vs);
                //vs.setFadeIn(true);
                //vs.addObserver(animator);
                //symbols.add(vs);
                animator.start();
            }
        }
    }

    public void addVisSymbol(VisSymbol vs) {
        //synchronized (symbolsSynObj) {
        boolean inserted = false;
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getSymbol().getAdapter() == vs.getSymbol().getAdapter()) {
                symbols.insertElementAt(vs, i);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            symbols.add(vs);
        }
    //}
    }

    public void brightness(Symbol s, float amount) {
        VisSymbol vs = findVSforS(s);
        synchronized (vs) {
            vs.setBrightness(amount);
        }
    }

    public void contrast(Symbol s, float amount) {
        // TODO Auto-generated method stub
    }

    public void hideSymbol(Symbol s) {
        VisSymbol vs = findVSforS(s);
        //synchronized (vs) {
        vs.setFadeOut(true);
        vs.setVisible(false);
    //}
    }

    public void removeSymbol(Symbol s) {
        VisSymbol vs = findVSforS(s);
        synchronized (vs) {
            vs.addObserver(this);
            vs.setFadeOut(true);
        }
    //symbols.remove(vs);

    }

    public void rotateSymbol(Symbol s, float angle) {
        synchronized (s) {
            if (angle == 360) {
                s.setAngle(angle);
            } else {
                s.setAngle(angle % 360);
            }
        }
    }

    public void scaleSymbol(Symbol s, float factorX, float factorY) {
        synchronized (s) {
            s.setScaleX(factorX);
            s.setScaleY(factorY);
        }
    }

    public void showSymbol(Symbol s) {
        VisSymbol vs = findVSforS(s);
        synchronized (vs) {
            vs.setFadeIn(true);
            vs.setVisible(true);
        }
    }

    public void swapSymbol(Symbol s, String filename) {
        VisSymbol vs = findVSforS(s);
        boolean newSymbol = false;

        if (vs == null && filename != null) {
            newSymbol = true;
            vs = new VisSymbol(s, null);
            vs.setVisible(false);
        }

        if (vs.isVisible()) {
            try {
                PImage tempImg = loadImage(filename);

                if (tempImg != null) {
                    synchronized (vs) {
                        vs.setImgSwap(tempImg);
                    }
                } else {
                    hideSymbol(s);
                }
            } catch (Exception e) {
                //Exception is thrown, if filename is an invalid filepath
                hideSymbol(s);
                e.printStackTrace();
            }
        } else {
            try {
                if (filename != null) {
                    vs.setImg(loadImage(filename));

                    if (newSymbol) {
                        this.addVisSymbol(vs);
                    }

                    showSymbol(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

        globalFactor = (float) screen.width / bgImage.width;

        bgImage.resize(screen.width, screen.height);

        initialized = true;
    }

    public void translateSymbol(Symbol s, java.awt.Point targetPosition) {
        VisSymbol vs = this.findVSforS(s);
        if (vs != null) {
            vs.setNewPositionSet(true);
        }
        s.setPosition(targetPosition);
    }

    /*public Vector<VisSymbol> getSymbols() {
    return this.symbols;
    }L*/
    public void update(Observable o, Object arg) {
        if (arg instanceof VisSymbol.Event) {
            VisSymbol.Event event = (VisSymbol.Event) arg;
            VisSymbol symbol = (VisSymbol) o;

            if (event == VisSymbol.Event.FadeOutCompleted) {
                symbol.deleteObserver(this);
                //synchronized (symbolsSynObj) {
                symbols.remove(symbol);
                Logging.getLogger().finest("removed vissymbol");
            //}
            }
        }
    }

    @Override
    public void stop() {
        pt.interrupt();
        t.interrupt();
        super.stop();
    }

    @Override
    public PImage loadImage(String filename) {
        if (filename == null) {
            return null;
        }

        if (imageCache.containsKey(filename)) {
            return imageCache.get(filename);
        }
        PImage img = super.loadImage(filename);
        imageCache.put(filename, img);
        return img;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //@todo: read configuration-filename from args
        System.out.println(System.getProperty("java.library.path"));
        //Runtime.getInstance().startup("displayConfig.ser");
        PApplet.main(new String[]{"--present", "peripheral.viz.VisApplet", "test"});

    /*try {
    System.out.println("Press <Enter> to terminate visualization.");
    //System.in.read();
    } catch (Exception e) {
    e.printStackTrace();
    }*/

    //Runtime.getInstance().shutdown();
    }

    class ProcessingThread extends Thread {

        private Vector<VisSymbol> symbols;
        private long updaterate;
        private boolean runFlag = true;

        public ProcessingThread(Vector<VisSymbol> symbols, int updaterate) {
            this.symbols = symbols;
            this.updaterate = updaterate;

            this.setPriority(Math.max(Thread.currentThread().getPriority() - 1, Thread.MIN_PRIORITY));

        }

        public void stopThread() {
            runFlag = false;
        }

        @Override
        public void run() {

            while (runFlag) {
                if (isInterrupted()) {
                    break;
                }

                //synchronized (symbolsSynObj) {
                //Logging.getLogger().finest("start iterating through all vis symbols");
                Vector<VisSymbol> copyOfSymbols = new Vector<VisSymbol>();
                copyOfSymbols.addAll(symbols);
                for (VisSymbol symbol : copyOfSymbols) {
                    synchronized (symbol) {
                        symbol.calcStep();
                    //Logging.getLogger().finest("calc next step for symbol");
                    }
                }
                //Logging.getLogger().finest("stopped iterating through all vis symbols");
                //}
                try {

                    Thread.sleep((long) ((1f / (float) updaterate) * 1000));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    interrupt();
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
            this.setPriority(Thread.MIN_PRIORITY);
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

            int cnt = 0;
            while (true) {
                if (isInterrupted()) {
                    break;
                }

                cnt++;
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
                                    iVal = (int) bounds.getLowerBound() + (int) (iVal % (bounds.getUpperBound()));
                                }
                                actValue = iVal;
                            } else {
                                dVal = Math.abs(r.nextFloat());
                                if (bounds != null) {
                                    dVal = bounds.getLowerBound() + (dVal % bounds.getUpperBound());
                                }
                                actValue = dVal;
                            }

                            //actValue = 1;
                            String logString = "simulated value for channel " + c.getFullname() + ": " + actValue;
                            Logging.getLogger().finer(logString);
                            logStrings.put(c, logString);
                        }
                        for (SensorValue v : c.getSensorValues()) {
                            v.setActValue(actValue);
                        }
                    }

                    if (cnt <= 3) {
                        noSensorChanges = "";
                        peripheral.logic.Runtime.getInstance().setSensorChanged(s);
                    } else {
                        cnt = 0;
                        noSensorChanges = "set no sensor changes";
                        peripheral.logic.Runtime.getInstance().setSensorNoChanges(s);
                    }
                }
                try {
                    sleep(50000);
                    if (cnt==0){
                        //sleep(15000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    interrupt();
                }
            }
        }
    }
}
