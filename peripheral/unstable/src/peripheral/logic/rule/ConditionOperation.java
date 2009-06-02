package peripheral.logic.rule;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class ConditionOperation implements Serializable {

    protected Condition condition;

    public ConditionOperation(Condition condition) {
        this.condition = condition;
    }

    public abstract boolean eval(Condition cond);

    public abstract String getOperationName();

    public abstract java.awt.Component getRightSideComponent();

    public abstract void saveValuesFromRightSideComponent();

    public String toString() {
        return getOperationName();
    }

    /**
     * This method is used by the serialization process. It calls
     * saveValuesFromRightSideComponent() to extract the values set
     * in this component, so that there is no need to serialize a
     * swing component.
     *
     * The method then calls defaultWriteObject() on the ObjectOutput-
     * Stream to proceed with the standard serialization process.
     *
     * @param out
     * @throws java.io.IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        saveValuesFromRightSideComponent();

        out.defaultWriteObject();
    }
}

