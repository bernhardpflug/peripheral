
package peripheral.designer.property;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Berni
 *
 * This editor should be used for Integer, Float, Double, Long and Short
 *
 * After each edit value is casted to class given in Constructor
 */
public class NumberEditor extends DefaultCellEditor{

    JFormattedTextField ftf;
    NumberFormat numberFormat;

    private Number minimum, maximum;
    private boolean BoundsCheckFlag;

    Class valueClass;

    public NumberEditor(Class valueClass) {
        super(new JFormattedTextField());

        BoundsCheckFlag = false;

        this.valueClass = valueClass;

        init();
    }

    /**
     * Constructor taking minimum and maximum to check bounds
     * @param valueClass
     * @param minimum value of SAME type like value
     * @param maximum value of SAME type like value
     */
    public NumberEditor(Class valueClass, Number minimum, Number maximum) {
        super(new JFormattedTextField());

        BoundsCheckFlag = true;

        this.minimum = minimum;
        this.maximum = maximum;

        this.valueClass = valueClass;

        init();
    }

    private void init() {

        ftf = (JFormattedTextField) getComponent();

        //Set up the editor for the number cells.
        if (valueClass.equals(Integer.class)) {
            numberFormat = NumberFormat.getIntegerInstance();
        }
        else {
            numberFormat = new DecimalFormat("#.0######");
            
        }
        
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setValueClass(valueClass);

        if (BoundsCheckFlag) {
            numberFormatter.setMinimum((Comparable)minimum);
            numberFormatter.setMaximum((Comparable)maximum);
        }

        ftf.setFormatterFactory(
                new DefaultFormatterFactory(numberFormatter));
        //ftf.setValue(minimum);
        //ftf.setValue(new Float(5.0f));
        ftf.setHorizontalAlignment(JTextField.TRAILING);
        ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

        //React when the user presses Enter while the editor is
        //active.  (Tab is handled as specified by
        //JFormattedTextField's focusLostBehavior property.)
        ftf.getInputMap().put(KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER, 0),
                "check");
        ftf.getActionMap().put("check", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (!ftf.isEditValid()) { //The text is invalid.
                    if (userSaysRevert()) { //reverted
                        ftf.postActionEvent(); //inform the editor
                    }
                } else {
                    try {              //The text is valid,
                        ftf.commitEdit();     //so use it.
                        ftf.postActionEvent(); //stop editing
                    } catch (java.text.ParseException exc) {
                    }
                }
            }
        });
    }

    //Override to invoke setValue on the formatted text field.
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected,
            int row, int column) {
        JFormattedTextField ftf =
                (JFormattedTextField) super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
        ftf.setValue(value);
        return ftf;
    }

    //Override to ensure that the value remains an Integer.
    public Object getCellEditorValue() {
        JFormattedTextField ftf = (JFormattedTextField) getComponent();
        Object o = ftf.getValue();

        if (o instanceof Number) {
            if (valueClass.equals(Float.class)) {
                System.out.println(((Number) o).floatValue());
                return new Float(((Number) o).floatValue());
            } else if (valueClass.equals(Double.class)) {
                return new Double(((Number) o).doubleValue());
            } else if (valueClass.equals(Long.class)) {
                return new Long(((Number) o).longValue());
            } else if (valueClass.equals(Short.class)) {
                return new Short(((Number) o).shortValue());
            } else if (valueClass.equals(Integer.class)) {
                return new Integer(((Number) o).intValue());
            } else {
                throw new NumberFormatException(valueClass + " is not supported; supported are Float,Double, Long, Short, Integer");
            }
        }
        else {
            throw new IllegalArgumentException("Can't use "+o+"; only subclasses of Number allowed");
        }
    }

    //Override to check whether the edit is valid,
    //setting the value if it is and complaining if
    //it isn't.  If it's OK for the editor to go
    //away, we need to invoke the superclass's version
    //of this method so that everything gets cleaned up.
    public boolean stopCellEditing() {
        JFormattedTextField ftf = (JFormattedTextField) getComponent();
        if (ftf.isEditValid()) {
            try {
                ftf.commitEdit();
            } catch (java.text.ParseException exc) {
            }

        } else { //text is invalid
            if (!userSaysRevert()) { //user wants to edit
                return false; //don't let the editor go away
            }
        }
        return super.stopCellEditing();
    }

    /**
     * Lets the user know that the text they entered is
     * bad. Returns true if the user elects to revert to
     * the last good value.  Otherwise, returns false,
     * indicating that the user wants to continue editing.
     */
    protected boolean userSaysRevert() {
        Toolkit.getDefaultToolkit().beep();
        ftf.selectAll();
        Object[] options = {"Edit",
            "Revert"};
        String text = "The value must be of type "+this.valueClass.getCanonicalName();

        if (BoundsCheckFlag) {
            text += " between " + minimum + " and " + maximum ;
        }

        int answer = JOptionPane.showOptionDialog(
                SwingUtilities.getWindowAncestor(ftf),
                text,
                "Invalid Text Entered",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[1]);

        if (answer == 1) { //Revert!
            ftf.setValue(ftf.getValue());
            return true;
        }
        return false;
    }
}
