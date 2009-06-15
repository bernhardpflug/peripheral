package peripheral.viz;

import java.awt.Dimension;
import java.awt.Point;
import peripheral.logic.action.SymbolScaleAction.ScaleExtentHorizontal;
import peripheral.logic.action.SymbolScaleAction.ScaleExtentVertical;
import peripheral.logic.positioningtool.Region; 
import peripheral.logic.symboladapter.Symbol;




public interface Visualization {

    /**
     *  <p style="margin-top: 0">
     *    moves Symbol s to targetPosition. If Symbol s is within a region, the 
     *    bounds have to be checked.
     *      </p>
     *      <p style="margin-top: 0">
     *    
     *  <br>if Region: relative coordinates??
     *      </p>
     */
    public void translateSymbol (Symbol s, Point targetPosition);

    /**
     *  <p style="margin-top: 0">
     *    scales Symbol s with factorX/factorY. If Symbol s is within a region, 
     *    the bounds have to be checked.
     *      </p>
     */
    public void scaleSymbol (Symbol s, float factorX, float factorY, ScaleExtentHorizontal extentX, ScaleExtentVertical extentY);

    /**
     *  <p style="margin-top: 0">
     *    
     *      </p>
     */
    public void swapSymbol (Symbol s, String filename);

    /**
     *  <p style="margin-top: 0">
     *    shows Symbol s with fade-in effect
     *      </p>
     */
    public void showSymbol (Symbol s);

    /**
     *  <p style="margin-top: 0">
     *    hides Symbol s with fade-out effect
     *      </p>
     */
    public void hideSymbol (Symbol s);

    /**
     *  <p style="margin-top: 0">
     *    rotate Symbol at angle-degrees. calculations are always based on the 
     *    start-state.
     *  <br>
     *      </p>
     *      <p style="margin-top: 0">
     *    
     *  <br>e.g.
     *  <br>  rotate (s, 90) --&gt; rotate 90&#176;
     *  <br>  rotate (s, 90) --&gt; do nothing
     *  <br>  rotate (s, 180) --&gt; rotate to 180&#176;, not 270&#176;
     *      </p>
     */
    public void rotateSymbol (Symbol s, float angle);

    public void brightness (Symbol s, float amount);

    public void contrast (Symbol s, float amount);

    /**
     *  <p style="margin-top: 0">
     *        adds Symbol s to Region region.<br><br>if no Animator is specified, do a 
     *        simple fade-in, otherwise run the animations specified by the according 
     *        Animator.
     *      </p>
     */
    public void addSymbol (Symbol s, Region region);

    /**
     *  <p style="margin-top: 0">
     *        removes Symbol s (has to be contained within a region).<br><br>
     *      </p>
     *      <p style="margin-top: 0">
     *        if no Animator is specified, do a simple fade-out, otherwise run the 
     *        fade-out effect specified by the according Animator.
     *      </p>
     */
    public void removeSymbol (Symbol s);

    /**
     * instantiate Visualizer + setup
     *
     * @param theme
     * @param resolution
     */
    public void init (String backgroundImageFilename, Dimension resolution);


    //public Vector<VisSymbol> getSymbols();
    public void addVisSymbol (VisSymbol s);
}

