/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.filter;

import java.util.BitSet;
import java.util.Random;
import peripheral.logic.symboladapter.SymbolAdapter;

/**
 *
 * @author Andi
 */
public abstract class RandomValuePickerFilter extends Filter {

    public RandomValuePickerFilter(SymbolAdapter adapter, String outputVarName) {
        super(adapter, outputVarName);
    }

    protected int[] getPickedIndizesNumbers(int nrToPick, boolean repeat, int from, int to) {
        int[] values = new int[nrToPick];
        BitSet pickedNumbers = new BitSet();
        Random rand = new Random();

        int cnt = 0;
        while (cnt < nrToPick) {
            int next = from + Math.abs(rand.nextInt()) % to;

            if (repeat) {
                values[cnt++] = next;
            } else {
                if (!pickedNumbers.get(next)) {
                    pickedNumbers.set(next);
                    ++cnt;
                }
            }
        }

        if (!repeat) {
            cnt = 0;
            for (int i = 0; i < to; i++) {
                if (pickedNumbers.get(i)) {
                    values[cnt++] = i;
                }
            }
        }

        return values;
    }
}
