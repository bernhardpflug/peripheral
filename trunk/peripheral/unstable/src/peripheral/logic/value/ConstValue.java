package peripheral.logic.value;


public class ConstValue extends Value {

    private Object value;

    public ConstValue (String varName, Object value) {
        super(varName);
    }

    public Object getValue () {
        return value;
    }

    public void setValue (Object val) {
        this.value = val;
    }

}
