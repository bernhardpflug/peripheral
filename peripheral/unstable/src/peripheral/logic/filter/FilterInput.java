/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.filter;

import java.io.Serializable;
import peripheral.logic.value.VarValue;

/**
 *
 * @author Andi
 */
public class FilterInput implements Serializable {
    private VarValue value;
    private String name;
    private Class [] allowedTypes;

    public FilterInput(String name, Class [] allowedTypes){
        this.name = name;
        this.allowedTypes = allowedTypes;
    }

    public String getName() {
        return name;
    }

    public Class[] getAllowedTypes() {
        return allowedTypes;
    }

    public VarValue getValue() {
        return value;
    }

    public void setValue(VarValue value) {
        this.value = value;
    }
}
