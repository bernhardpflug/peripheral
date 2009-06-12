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
import peripheral.logic.value.Value;
import peripheral.viz.Visualization;
import peripheral.viz.VisSymbol;
import peripheral.viz.VisAnimator;
import peripheral.viz.VisMoverAnimator;

/**
 *
 * @author Andi
 */
public class Mover extends SymbolAnimator {

    public enum Direction {

        Horizontal, Vertical
    }

    public enum FadeIn {

        LeftOrTop, RightOrBottom, Random//, No
    }
    private Direction direction = Direction.Horizontal;
    private FadeIn fadeIn = FadeIn.Random;
    /*private double speed = 1.0;
    private boolean bounds = true;
    private boolean repeat = false;
    private FadeIn fadeIn = FadeIn.Random;*/
    //private boolean fadeOut = true;


    private Value valDirection;
    private Value valSpeed;
    private Value valBounds;
    //private Value valRepeat;
    private Value valFadeIn;

    List<UserInput> ui;

    public Mover(SymbolAdapter adapter) {
        super(adapter);

        ui = new ArrayList<UserInput>();

        valDirection = new ConstValue(adapter, "Mover.direction", direction, direction.getClass());
        //valSpeed = new ConstValue(adapter, "Mover.speed", 1.0, Double.class);
        valBounds = new ConstValue(adapter, "Mover.bounds", true, Boolean.class);
        //valRepeat = new ConstValue(adapter, "Mover.repeat", false, Boolean.class);
        valFadeIn = new ConstValue(adapter, "Mover.fadeIn", fadeIn, fadeIn.getClass());

        ui.add(new UserInput("Direction", "", valDirection));
        ui.add(new UserInput("Speed", "", valSpeed));
        ui.add(new UserInput("Bounds", "", valBounds));
        //ui.add(new UserInput("Repeat", "", valRepeat));
        ui.add(new UserInput("FadeIn", "", valFadeIn));
    }

    public boolean isBounds() {
        return (Boolean) valBounds.getValue();
    }

    /*public void setBounds(boolean bounds) {
    this.bounds = bounds;
    }*/
    public Direction getDirection() {
        return (Direction) valDirection.getValue();
    }

    /*public void setDirection(Direction direction) {
    this.direction = direction;
    }*/
    public FadeIn getFadeIn() {
        return (FadeIn) valFadeIn.getValue();
    }

    /*public void setFadeIn(FadeIn fadeIn) {
    this.fadeIn = fadeIn;
    }*/

    /*public boolean isFadeOut() {
    return fadeOut;
    }

    public void setFadeOut(boolean fadeOut) {
    this.fadeOut = fadeOut;
    }*/
    /*public boolean isRepeat() {
        return (Boolean) valRepeat.getValue();
    }*/

    /*public void setRepeat(boolean repeat) {
    this.repeat = repeat;
    }*/
    /*public double getSpeed() {
        return (Double) valSpeed.getValue();
    }*/

    /*public void setSpeed(double speed) {
    this.speed = speed;
    }*/
    public List<UserInput> getUserInput() {


        return ui;
    }

    @Override
    public VisAnimator getVisAnimator(Visualization viz, VisSymbol symbol) {
        return new VisMoverAnimator(this, symbol, viz);
    }
}
