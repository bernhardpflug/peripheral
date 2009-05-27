package peripheral.visualization;

import java.awt.Dimension;
import java.util.Vector;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.symboladapter.Symbol;
import processing.core.PApplet;
import processing.core.PImage;

public class VisApplet extends PApplet implements Visualization {

    private static final long serialVersionUID = 1L; //	whatever that's needed for
    private Vector<VisSymbol> symbols;
    private PImage bgImage;
    private Dimension screen;

    public VisApplet() {
        symbols = new Vector<VisSymbol>();
    }


    public void setup() {
        peripheral.logic.Runtime.getInstance().startup(this, "displayConfig.ser");
   
        size((int) screen.getWidth(), (int) screen.getHeight(), P3D);
        frameRate(60);
        noStroke();
    }

    /*public void mouseClicked(){	//used for the mover demo

    }*/
    public void draw() {
        //background(bgImage);
        background(255);
        this.color(0);
        this.rect(100, 100, 200, 200);
        VisSymbol ptr;
        for (int i = 0; i < symbols.size(); i++) {
            ptr = symbols.get(i);
            ptr.calcStep();
            //brightness and alpha
            tint((255.f / ptr.getBrightness()), (255.f / ptr.getAlpha()));
            image(ptr.getImg(), (float) ptr.getPositionIpl().getX(), (float) ptr.getPositionIpl().getY());
        }

    }

    public void add(Symbol s, Region region) {
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

    public void hide(Symbol s) {
        VisSymbol vs = findVSforS(s);
        vs.setVisible(false);
    }

    public void remove(Symbol s) {
        VisSymbol vs = findVSforS(s);
        symbols.remove(vs);

    }

    public void rotate(Symbol s, float angle) {
        s.setAngle(angle);
    }

    public void scale(Symbol s, float factorX, float factorY) {
        s.setScaleX(factorX);
        s.setScaleY(factorY);
    }

    public void show(Symbol s) {
        VisSymbol vs = findVSforS(s);
        vs.setVisible(true);

    }

    public void swap(Symbol s, String filename) {
        // TODO Auto-generated method stub
    }

    private VisSymbol findVSforS(Symbol s) {
        for (int i = 0; i < symbols.size(); i++) {
            VisSymbol vs = symbols.get(i);
            if (vs.getSymbol() == s) {
                return vs;
            }
        }
        return null;
    }

    public void init(String backgroundImageFilename, Dimension resolution) {

        bgImage = loadImage(backgroundImageFilename);
        screen = resolution;
    }

    public void translate(Symbol s, java.awt.Point targetPosition) {
        s.getPosition().setPosition(targetPosition);

    }

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //@todo: read configuration-filename from args

        //Runtime.getInstance().startup("displayConfig.ser");
        PApplet.main(new String[]{"--present", "peripheral.visualization.VisApplet"});

        try {
            System.out.println("Press <Enter> to terminate visualization.");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Runtime.getInstance().shutdown();
    }
}
