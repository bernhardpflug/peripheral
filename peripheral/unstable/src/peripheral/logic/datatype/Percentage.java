/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.datatype;

/**
 *
 * @author Andi
 */
public class Percentage {
    private double val = 0;

    public Percentage(double val) {
        setVal(val);
    }

    public Percentage() {
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
        if (val > 1.0){
            this.val = 1.0;
        }
    }
}
