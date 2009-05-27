package peripheral.visualization;

import processing.core.*;
import java.awt.Point;
import java.awt.Rectangle;
import peripheral.logic.symboladapter.*;
import peripheral.logic.positioningtool.Region;

public class VisSymbol {
	private float alpha;
	//uses interpolation (Ipl) steps of the current target aspects:
	private float angleIpl;
	private float scaleXIpl;
	private float scaleYIpl;
	private Point positionIpl;
	private Symbol symbol;
	private PImage img;
	private Region region;
	private float brightness;
	private boolean fadeIn, fadeOut, isVisible;
	
	public VisSymbol(Symbol s, Region r)
	{
		this.symbol = s;
		this.alpha = 0.f;
		this.angleIpl = s.getAngle();
		this.scaleXIpl = s.getScaleX();
		this.scaleYIpl = s.getScaleY();
		this.positionIpl = new Point();
		this.positionIpl.setLocation(s.getPosition().getPosition());
		this.img = null;
		this.fadeIn = this.fadeOut = false;
		this.isVisible = true;
		this.region = r;
		this.brightness = 1.f;
	}
	
	public void calcStep(){
		//===========================================
		//compute alpha value
		if (fadeIn){
			alpha += 0.02;
			if (alpha >= 1.f){
				alpha = 1.f;
				fadeIn = false;
			}
		}
		if (fadeOut){
			alpha -= 0.02;
			if (alpha <= 0.f){
				alpha = 0.f;
				fadeOut = false;
			}
		}
		//===========================================
		//compute easing
		float dx = (float)symbol.getPosition().getPosition().getX() - (float)positionIpl.getX();
		float iplX = 0.f, iplY = 0.f;
		if(Math.abs(dx) > 1) {
			iplX += dx * 0.05; //-> easing val
		}
		float dy = (float)symbol.getPosition().getPosition().getY() - (float)positionIpl.getY();
		if(Math.abs(dy) > 1) {
			iplY += dy * 0.05;
		}
		positionIpl.setLocation(iplX, iplY);
		
		//===========================================
		//is img visible?
		checkVisibility();
		
		
		//===========================================
		//TODO: calc rotation, 
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

	public Point getPositionIpl() {
		return positionIpl;
	}

	public void setPositionIpl(Point positionIpl) {
		this.positionIpl = positionIpl;
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

	public void setFadeOut(boolean fadeOut) {
		this.fadeOut = fadeOut;
	}

	public float getBrightness() {
		return brightness;
	}

	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}
	
	public void setVisible(boolean visible){
		this.isVisible = visible;
	}
	
	public boolean isVisible(){
		return this.isVisible;
	}
	
	private void checkVisibility(){
		if (region == null) return;
		Rectangle r = region.getBounds();
		isVisible = r.contains(positionIpl.getLocation());
	}
}
