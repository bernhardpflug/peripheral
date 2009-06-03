package peripheral.visualization;

import java.awt.Dimension;
import java.util.Vector;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.symboladapter.Symbol;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;

public class VisApplet extends PApplet implements Visualization {

    private static final long serialVersionUID = 1L; //	whatever that's needed for
    private Vector<VisSymbol> symbols;
    private PImage bgImage;
    private Dimension visDim;
    private boolean isInit = false;
    
    public VisApplet() {
        symbols = new Vector<VisSymbol>();
    }


    public void setup() {    	
    	peripheral.logic.Runtime.getInstance().startup(this, "displayConfig.ser");
        size(screen.width, screen.height, P2D);
        frameRate(60);
        noStroke();
    }

    public void draw() {
    	//TODO: interpolate coords to actual screen resolution
    	if (isInit){
    		if (bgImage != null)
        		background(bgImage);
        	else
        		background(0);
        	
            VisSymbol ptr;
            for (int i = 0; i < symbols.size(); i++) {
                ptr = symbols.get(i);
                ptr.calcStep();
                //draw the image in all its aspects
                pushMatrix();
                	tint((255.f * ptr.getBrightness()), (255.f * ptr.getAlpha()));
    	            translate(ptr.getPosXIpl(), ptr.getPosYIpl());
    	            rotate(radians(ptr.getAngleIpl()));
    	            scale(ptr.getScaleXIpl(), ptr.getScaleYIpl());
    	            image(ptr.getImg(), 0 - ptr.getImg().width/2, 0 - ptr.getImg().height/2);
    	            if(ptr.getImgSwap() != null){
    	            	tint((255.f * ptr.getBrightness()), (255.f * ptr.getAlphaSwap()));
    	            	image(ptr.getImgSwap(), 0 - ptr.getImgSwap().width/2, 0 - ptr.getImgSwap().height/2);
    	            }
                popMatrix();
            }
    	}
    }

    public void addSymbol(Symbol s, Region region) {
    	VisSymbol vs = findVSforS(s);
    	if (vs == null){
	        vs = new VisSymbol(s, region);
	        vs.setImg(loadImage(s.getFile().getPath()));
	        vs.setFadeIn(true);
	        symbols.add(vs);
    	}
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
        if (bgImage != null)
        	isInit = true;
    }

    public void translateSymbol(Symbol s, java.awt.Point targetPosition) {
        s.setPosition(targetPosition);
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
