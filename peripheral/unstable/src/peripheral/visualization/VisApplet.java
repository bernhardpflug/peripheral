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
    private Dimension visDim;

    public VisApplet() {
        symbols = new Vector<VisSymbol>();
    }


    public void setup() {
        peripheral.logic.Runtime.getInstance().startup(this, "displayConfig.ser");
        size(screen.width, screen.height, P2D);
        //size((int) screen.getWidth(), (int) screen.getHeight(), P3D);
        frameRate(60);
        noStroke();
    }

    public void draw() {
    	//TODO: interpolate coords to actual screen resolution
        background(bgImage);
        VisSymbol ptr;
        for (int i = 0; i < symbols.size(); i++) {
            ptr = symbols.get(i);
            ptr.calcStep();
            //draw the image in all its aspects
            pushMatrix();
            	tint((255.f * ptr.getBrightness()), (255.f * ptr.getAlpha()));
	            translate((float)ptr.getPositionIpl().getX(), (float)ptr.getPositionIpl().getY());
	            scale(ptr.getScaleXIpl(), ptr.getScaleYIpl());
	            rotate(radians(ptr.getAngleIpl()));
	            image(ptr.getImg(), ptr.getImg().width/2, ptr.getImg().height/2);
            popMatrix();
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
        vs.setVisible(false);
    }

    public void removeSymbol(Symbol s) {
        VisSymbol vs = findVSforS(s);
        symbols.remove(vs);

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
    }

    public void translateSymbol(Symbol s, java.awt.Point targetPosition) {
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
