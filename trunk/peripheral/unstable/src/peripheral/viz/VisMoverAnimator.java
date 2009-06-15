/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.viz;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import peripheral.logic.Logging;
import peripheral.logic.animation.Mover;
import peripheral.logic.animation.Mover.Direction;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.util.PositionRandomizer;
import peripheral.viz.VisSymbol.Event;

/**
 *
 * @author Andi
 */
public class VisMoverAnimator implements VisAnimator, Observer {

    private Mover mover;
    private VisSymbol symbol;
    private SymbolAdapter adapter;
    private Point fadeInStartPosition;
    private Point endPosition;
    private Point startPosition;
    private Visualization viz;
    private Point nextPosition;
    //private boolean doFadeIn;

    public VisMoverAnimator(Mover mover, VisSymbol symbol, Visualization viz) {
        this.mover = mover;
        this.adapter = mover.getAdapter();
        this.symbol = symbol;
        this.viz = viz;

        this.symbol.addObserver(this);

        init();
    }

    private void init() {
        if (adapter.getTool() instanceof Region) {
            Region region = (Region) adapter.getTool();

            fadeInStartPosition = PositionRandomizer.getRandomPosition(region.getBounds(), new Dimension(symbol.getImg().width, symbol.getImg().height));
            endPosition = new Point();
            startPosition = new Point(fadeInStartPosition);

            //if (mover.getFadeIn() != Mover.FadeIn.No) {

            Mover.FadeIn fadeIn = mover.getFadeIn();
            if (fadeIn == Mover.FadeIn.Random) {
                fadeIn = Mover.FadeIn.values()[Math.abs((new Random()).nextInt()) % 2];
            }

            //fadeIn = Mover.FadeIn.Left;
            //mover.setDirection(Direction.Horizontal);
            int fadeInStartPositionRandX = Math.abs((new Random()).nextInt()) % 100;
            int fadeInStartPositionRandY = Math.abs((new Random()).nextInt()) % 100;
            switch (fadeIn) {
                case LeftOrTop:
                    if (mover.getDirection() == Direction.Horizontal) {
                        fadeInStartPosition.x = region.getBounds().x - (symbol.getImg().width + fadeInStartPositionRandX);
                        startPosition.x = region.getBounds().x;
                        endPosition.y = fadeInStartPosition.y;
                        if (!mover.isBounds()) {
                            endPosition.x = region.getBounds().x + region.getBounds().width;
                        } else {
                            endPosition.x = region.getBounds().x + region.getBounds().width - symbol.getImg().width;
                        }
                    } else if (mover.getDirection() == Direction.Vertical) {
                        fadeInStartPosition.y = region.getBounds().y - (symbol.getImg().height + fadeInStartPositionRandY);
                        startPosition.y = region.getBounds().y;
                        endPosition.x = fadeInStartPosition.x;
                        if (!mover.isBounds()) {
                            endPosition.y = region.getBounds().y + region.getBounds().height;
                        } else {
                            endPosition.y = region.getBounds().y + region.getBounds().height - symbol.getImg().height;
                        }
                    }
                    symbol.toogleImage();
                    break;
                case RightOrBottom:
                    if (mover.getDirection() == Direction.Horizontal) {
                        fadeInStartPosition.x = region.getBounds().x + (region.getBounds().width + fadeInStartPositionRandX);
                        startPosition.x = region.getBounds().x + region.getBounds().width - symbol.getImg().width;
                        endPosition.y = fadeInStartPosition.y;
                        if (!mover.isBounds()) {
                            endPosition.x = region.getBounds().x - symbol.getImg().width;
                        } else {
                            endPosition.x = region.getBounds().x;
                        }
                    } else if (mover.getDirection() == Direction.Vertical) {
                        fadeInStartPosition.y = region.getBounds().y + (region.getBounds().height + fadeInStartPositionRandY);
                        startPosition.y = region.getBounds().y + region.getBounds().height - symbol.getImg().height;
                        endPosition.x = fadeInStartPosition.x;
                        if (!mover.isBounds()) {
                            endPosition.y = region.getBounds().y - symbol.getImg().height;
                        } else {
                            endPosition.y = region.getBounds().y;
                        }
                    }
                    break;
            }

            this.symbol.setPositionImmediately(fadeInStartPosition);
            this.viz.addVisSymbol(symbol);
        //}
        } else {
            Logging.getLogger().warning("Mover only works with PositioningTool 'Region'.");
        }
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof Event) {
            if (((Event) arg) == Event.PositionReached) {
                /*if (doFadeIn) {
                symbol.setFadeIn(true);
                doFadeIn = false;
                } else {*/
                if (mover.isBounds()) {
                    nextPosition = nextPosition == startPosition ? endPosition : startPosition;
                    symbol.toogleImage();
                    viz.translateSymbol(symbol.getSymbol(), nextPosition);
                    Logging.getLogger().finer("move symbol to " + nextPosition);
                } else {
                    symbol.setPositionImmediately(fadeInStartPosition);
                    symbol.setFadeIn(true);
                    //viz.translateSymbol(symbol.getSymbol(), endPosition);
                }
            //}
            } else if (((Event) arg) == Event.FadeInCompleted) {
                nextPosition = endPosition;
                viz.translateSymbol(symbol.getSymbol(), nextPosition);
            }
        }
    }

    public void start() {
        //symbol.setFadeIn(true);
        //symbol.addObserver(this);
        symbol.setFadeIn(true);
    //viz.translateSymbol(symbol.getSymbol(), fadeInStartPosition);
    }
}
