/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.animation;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.UserInput;

/**
 *
 * @author Andi
 */
public class Mover extends SymbolAnimator{
    public enum Direction {
        Horizontal, Vertical
    }

    public enum FadeIn {
        Left, Right, Top, Bottom, Random, No
    }

    private Direction direction = Direction.Horizontal;
    private double speed = 1.0;
    private boolean bounds = true;
    private boolean repeat = false;
    private FadeIn fadeIn = FadeIn.Random;
    private boolean fadeOut = true;

    public Mover(SymbolAdapter adapter){
        super(adapter);
    }

    public boolean isBounds() {
        return bounds;
    }

    public void setBounds(boolean bounds) {
        this.bounds = bounds;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public FadeIn getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(FadeIn fadeIn) {
        this.fadeIn = fadeIn;
    }

    public boolean isFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(boolean fadeOut) {
        this.fadeOut = fadeOut;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public List<UserInput> getUserInput() {
        List<UserInput> ui = new ArrayList<UserInput>();

        ui.add(new UserInput("Direction", "", new ConstValue(adapter, "mover.direction", direction, direction.getClass())));
        ui.add(new UserInput("Speed", "", new ConstValue(adapter, "mover.speed", speed, Double.class)));
        ui.add(new UserInput("Bounds", "", new ConstValue(adapter, "mover.bounds", bounds, Boolean.class)));
        ui.add(new UserInput("Repeat", "", new ConstValue(adapter, "mover.repeat", repeat, Boolean.class)));
        ui.add(new UserInput("FadeIn", "", new ConstValue(adapter, "mover.fadeIn", fadeIn, fadeIn.getClass())));
        ui.add(new UserInput("FadeOut", "", new ConstValue(adapter, "mover.fadeOut", fadeOut, Boolean.class)));

        return ui;
    }


    
}
