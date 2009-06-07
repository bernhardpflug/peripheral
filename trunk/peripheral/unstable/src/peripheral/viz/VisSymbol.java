package peripheral.viz;

import processing.core.*;
import java.awt.Point;
import java.awt.Rectangle;


import java.util.Observable;
import peripheral.logic.symboladapter.*;
import peripheral.logic.positioningtool.Region;

public class VisSymbol extends Observable {

    public enum Event {

        FadeInCompleted, FadeOutCompleted, PositionReached
    }
    private float alpha,  alphaSwap;
    //uses interpolation (Ipl) steps of the current target aspects:
    private float angleIpl;
    private float scaleXIpl;
    private float scaleYIpl;
    private Point positionIpl;
    private Symbol symbol;
    private PImage img,  imgSwap;
    private Region region;
    private float brightness;
    private boolean fadeIn,  fadeOut,  isVisible;
    private boolean newPositionSet = false;

    public VisSymbol(Symbol s, Region r) {
        this.symbol = s;
        this.alpha = alphaSwap = 0.f;
        this.angleIpl = s.getAngle();
        this.scaleXIpl = s.getScaleX();
        this.scaleYIpl = s.getScaleY();
        this.positionIpl = new Point();
        this.positionIpl.setLocation(s.getPosition());
        this.img = this.imgSwap = null;
        this.fadeIn = this.fadeOut = false;
        this.isVisible = true;
        this.region = r;
        this.brightness = 1.f;
    }

    private void setFadeInCompleted() {
        this.setChanged();
        this.notifyObservers(Event.FadeInCompleted);
    }

    private void setFadeOutCompleted() {
        this.setChanged();
        this.notifyObservers(Event.FadeOutCompleted);
    }

    private void setPositionReached() {
        if (this.isNewPositionSet()) {
            this.setNewPositionSet(false);
            this.setChanged();
            this.notifyObservers(Event.PositionReached);
        }
    }

    public void calcStep() {
        //===========================================
        //compute alpha value
        if (fadeIn) {
            alpha += 0.02;
            if (alpha >= 1.f) {
                alpha = 1.f;
                fadeIn = false;
                setFadeInCompleted();
            }
        }
        if (fadeOut) {
            alpha -= 0.02;
            if (alpha <= 0.f) {
                alpha = 0.f;
                fadeOut = false;
                setFadeOutCompleted();
            }
        }
        //===========================================
        //compute easing
        float iplX = (float) positionIpl.getX(), iplY = (float) positionIpl.getY();
        float dx = (float) symbol.getPosition().getX() - iplX;
        boolean positionReached = true;
        if (Math.abs(dx) > 0) {
            iplX += Math.signum(dx) * 1;//dx * 0.05; //-> easing val
            positionReached = false;
        }
        float dy = (float) symbol.getPosition().getY() - iplY;
        if (Math.abs(dy) > 0) {
            iplY += Math.signum(dy) * 1;//dy * 0.05;
            positionReached = false;
        }
        positionIpl.setLocation(iplX, iplY);
        if (positionReached) {
            setPositionReached();
        }

        //===========================================
        //is img visible?
        checkVisibility();

        //===========================================
        //swap necessary?
        if (imgSwap != null) {
            alpha -= 0.02;
            alphaSwap += 0.02;
            if (alpha <= 0.f) {
                alpha = 1.f;
                alphaSwap = 0.f;
                img = imgSwap;
                imgSwap = null;
            }
        }

        //===========================================
        //calc rotation
        //symbol.setAngle(symbol.getAngle()%360); angleIpl = angleIpl%360;
        if (symbol.getAngle() > angleIpl) {
            angleIpl += 0.1;
        } else if (symbol.getAngle() < angleIpl) {
            angleIpl -= 0.1;
        }

        //===========================================
        //calc scaling
        if (Math.abs(symbol.getScaleX() - scaleXIpl) > 0.05) {
            if (symbol.getScaleX() > scaleXIpl) {
                scaleXIpl += 0.05;
            } else {
                scaleXIpl -= 0.05;
            }
        }
        if (Math.abs(symbol.getScaleY() - scaleYIpl) > 0.05) {
            if (symbol.getScaleY() > scaleYIpl) {
                scaleYIpl += 0.05;
            } else {
                scaleYIpl -= 0.05;
            }
        }

    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getAngleIpl() {
        return angleIpl;
    }

    public void setAngleIpl(float angleIpl) {
        this.angleIpl = angleIpl;
    }

    public PImage getImg() {
        return img;
    }

    public void setImg(PImage img) {
        this.img = img;
    }

    public boolean isNewPositionSet() {
        return newPositionSet;
    }

    public void setNewPositionSet(boolean newPositionSet) {
        this.newPositionSet = newPositionSet;
    }

    public Point getPositionIpl() {
        return positionIpl;
    }

    public void setPositionIpl(Point positionIpl) {
        this.positionIpl = positionIpl;
    }

    public void setPositionImmediately (Point position){
        this.getSymbol().setPosition(position);
        this.positionIpl = position;
    }

    public float getScaleXIpl() {
        return scaleXIpl;
    }

    public void setScaleXIpl(float scaleXIpl) {
        this.scaleXIpl = scaleXIpl;
    }

    public float getScaleYIpl() {
        return scaleYIpl;
    }

    public void setScaleYIpl(float scaleYIpl) {
        this.scaleYIpl = scaleYIpl;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void setFadeIn(boolean fadeIn) {
        this.fadeIn = fadeIn;
    }

    public boolean isFadeIn() {
        return fadeIn;
    }

    public void setFadeOut(boolean fadeOut) {
        this.fadeOut = fadeOut;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    private void checkVisibility() {
        if (region == null) {
            return;
        }
        Rectangle r = region.getBounds();

        Point leftUp = positionIpl.getLocation();
        Point rightUp = new Point();
        rightUp.setLocation(positionIpl.getX()+this.img.width, positionIpl.getY());
        Point leftDown = new Point();
        leftDown.setLocation(positionIpl.getX(), positionIpl.getY()+this.img.height);
        Point rightDown = new Point();
        rightDown.setLocation(positionIpl.getX()+this.img.width, positionIpl.getY()+this.img.height);

        if (r.contains(leftUp) || r.contains(rightUp) || r.contains(leftDown) || r.contains(rightDown)) {
            isVisible = true;
        }
        else {
            isVisible = false;
        }
    }

    public PImage getImgSwap() {
        return imgSwap;
    }

    public void setImgSwap(PImage imgSwap) {
        this.imgSwap = imgSwap;
    }

    public float getAlphaSwap() {
        return alphaSwap;
    }

    public float getScaledWidth() {
        return this.img.width * this.scaleXIpl;
    }

    public float getScaledHeight() {
        return this.img.height * this.scaleYIpl;
    }
}
