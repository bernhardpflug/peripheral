/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.datatype;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andi
 */
public class Primitives {

    public static boolean isAssignable (Class dest, Class src){
        List<Class> primitivesOrder = new ArrayList<Class>();

        primitivesOrder.add(Short.class);
        primitivesOrder.add(Integer.class);
        primitivesOrder.add(Long.class);
        primitivesOrder.add(Float.class);
        primitivesOrder.add(Double.class);

        return primitivesOrder.indexOf(dest) >= primitivesOrder.indexOf(src);
    }


}
