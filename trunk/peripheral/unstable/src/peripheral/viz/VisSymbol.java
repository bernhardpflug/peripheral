package peripheral.viz;

import processing.core.*;
import java.awt.Point;
import java.awt.Rectangle;


import java.util.Observable;
import peripheral.logic.Logging;
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
    //private Point positionIpl;
    private Symbol symbol;
    private PImage img,  imgSwap;
    private Region region;
    private float brightness;
    private boolean fadeIn,  fadeOut,  isVisible;
    private boolean newPositionSet = false;
    private float iplX,  iplY;

    public VisSymbol(Symbol s, Region r) {
        this.symbol = s;
        this.alpha = alphaSwap = 0.f;
        this.angleIpl = s.getAngle();
        this.scaleXIpl = s.getScaleX();
        this.scaleYIpl = s.getScaleY();
        //this.positionIpl = new Point();
        //this.positionIpl.setLocation(s.getPosition());
        this.setIpl(s.getPosition());
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
            alpha += 0.02f;
            if (alpha >= 1.f) {
                alpha = 1.f;
                fadeIn = false;
                setFadeInCompleted();
            }
        }
        if (fadeOut) {
            alpha -= 0.02f;
            if (alpha <= 0.f) {
                alpha = 0.f;
                fadeOut = false;
                setFadeOutCompleted();
            }
        }
        //===========================================
        //compute easing
        //float iplX = (float) positionIpl.getX(), iplY = (float) positionIpl.getY();
        float dx = (float) symbol.getPosition().getX() - iplX;
        float dy = (float) symbol.getPosition().getY() - iplY;
        float factorX = 1, factorY = 1;
        if (Math.abs(dx) > Math.abs(dy)) {
            factorY = Math.abs(dy) / Math.abs(dx);
        } else {
            factorX = Math.abs(dx) / Math.abs(dy);
        }
        //Logging.getLogger().finest("dx=" + dx + ", dy=" + dy + ", factorX=" + factorX + ", factorY=" + factorY);

        boolean positionReached = true;
        if (Math.abs(dx) > 1) {
            iplX += Math.signum(dx) * factorX;//dx * 0.05; //-> easing val
            positionReached = false;
        }
        if (Math.abs(dy) > 1) {
            iplY += Math.signum(dy) * factorY;//dy * 0.05;
            positionReached = false;
        }
        //positionIpl.setLocation(iplX, iplY);
        if (positionReached) {
            setPositionReached();
        }

        //===========================================
        //is img visible?
        checkVisibility();

        //===========================================
        //swap necessary?
        if (imgSwap != null) {
            alpha -= 0.02f;
            alphaSwap += 0.02f;
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
        if (Math.abs(symbol.getAngle() - angleIpl) > 0.3f) {
            if (symbol.getAngle() > angleIpl) {
                angleIpl += 0.3f;
            } else if (symbol.getAngle() < angleIpl) {
                angleIpl -= 0.3f;
            }
        }

        //===========================================
        //calc scaling
        if (Math.abs(symbol.getScaleX() - scaleXIpl) > 0.01f) {
            if (symbol.getScaleX() > scaleXIpl) {
                scaleXIpl += 0.01f;
            } else {
                scaleXIpl -= 0.01f;
            }
        }
        if (Math.abs(symbol.getScaleY() - scaleYIpl) > 0.01f) {
            if (symbol.getScaleY() > scaleYIpl) {
                scaleYIpl += 0.01f;
            } else {
                scaleYIpl -= 0.01f;
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

    public float getIplX() {
        return iplX;
    }

    public float getIplY() {
        return iplY;
    }

    public void setIpl(Point position) {
        this.iplX = position.x;
        this.iplY = position.y;
    }

    /*public Point getPositionIpl() {
    return positionIpl;
    }

    public void setPositionIpl(Point positionIpl) {
    this.positionIpl = positionIpl;
    }*/
    public void setPositionImmediately(Point position) {
        this.getSymbol().setPosition(position);
        //this.positionIpl = position;
        this.iplX = position.x;
        this.iplY = position.y;
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
        if (fadeIn) {
            setFadeOut(false);
        }
    }

    public boolean isFadeIn() {
        return fadeIn;
    }

    public void setFadeOut(boolean fadeOut) {
        this.fadeOut = fadeOut;
        if (fadeOut) {
            setFadeIn(false);
        }
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

        Point leftUp = new Point((int) iplX, (int) iplY);//positionIpl.getLocation();
        Point rightUp = new Point();
        rightUp.setLocation(leftUp.getX() + this.img.width, leftUp.getY());
        Point leftDown = new Point();
        leftDown.setLocation(leftUp.getX(), leftUp.getY() + this.img.height);
        Point rightDown = new Point();
        rightDown.setLocation(leftUp.getX() + this.img.width, leftUp.getY() + this.img.height);

        if (r.contains(leftUp) || r.contains(rightUp) || r.contains(leftDown) || r.contains(rightDown)) {
            isVisible = true;
        } else {
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
