/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.datatype;

import java.io.Serializable;

/**
 *
 * @author Andi
 */
public class Percentage implements Serializable {
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

    @Override
    public String toString() {
        return String.valueOf(val*100) + "%";
    }


}
