/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.rule;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import peripheral.logic.Logging;

/**
 *
 * @author Andi
 */
public class SimpleCondition extends ConditionOperation {

    public enum Operation {

        Equal, Unequal, Less, LessOrEqual, Greater, GreaterOrEqual
    }
    private Operation type;
    private transient JFormattedTextField rightSideComponent = null;
    private Object rightSideOp;

    public SimpleCondition(Condition condition, Operation type) {
        super(condition);
        this.type = type;
    }

    public boolean eval(Condition cond) {
        Object op1 = cond.getLeftSideOp().getValue();

        if (type == Operation.Equal || type == Operation.Unequal) {
            return evalEquality(op1, rightSideOp);
        }

        return evalOrder(op1, rightSideOp);
    }

    private boolean evalEquality(Object op1, Object op2) {
        switch (type) {
            case Equal:
                return op1.equals(op2);
            case Unequal:
                return !op1.equals(op2);
        }
        return false;
    }

    private boolean evalOrder(Object op1, Object op2) {
        if (!(op1 instanceof Comparable) || !(op2 instanceof Comparable)) {
            return false;
        }

        int compareResult = ((Comparable) op1).compareTo(op2);

        switch (type) {
            case Less:
                return compareResult < 0;
            case LessOrEqual:
                return compareResult <= 0;
            case Greater:
                return compareResult > 0;
            case GreaterOrEqual:
                return compareResult >= 0;
        }

        return false;
    }

    @Override
    public String getOperationName() {
        switch (type) {
            case Equal:
                return "gleich";
            case Unequal:
                return "ungleich";
            case Less:
                return "kleiner";
            case LessOrEqual:
                return "kleiner oder gleich";
            case Greater:
                return "größer";
            case GreaterOrEqual:
                return "größer oder gleich";
        }

        return "UnsupportedOperation";
    }

    public java.awt.Component getRightSideComponent() {
        if (rightSideComponent == null) {
            Object value = null;
            try {
                Class type = condition.getLeftSideOp().getSensorChannel().getDatatype();
                String s = "";
                if (condition.isLeftSideOpTypeNumber()){
                    s = "0";
                }
                value = type.getConstructor(String.class).newInstance(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rightSideComponent = new JFormattedTextField(value);
            rightSideComponent.setPreferredSize(new Dimension(50, 10));
        }
        return rightSideComponent;
    }

    @Override
    public void saveValuesFromRightSideComponent() {
        if (rightSideComponent != null) {
            rightSideOp = rightSideComponent.getValue();
            Logging.getLogger().finer("saved value " + rightSideOp + " to rightSideOp");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SimpleCondition) {
            return ((SimpleCondition) obj).type == this.type;
        }
        return false;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        saveValuesFromRightSideComponent();

        out.writeObject(rightSideOp);

        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        //saveValuesFromRightSideComponent();

        rightSideOp = in.readObject();

        in.defaultReadObject();
    }
}
