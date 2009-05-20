/*
 * Panel that takes UserInput, creates GUI elements for changing them
 * and returns the new defined values
 */
package peripheral.designer.property;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorAttribute;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.SensorValue;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;


/**
 *
 * @author Berni
 *
 * SENSORATTRIBUTE BENÖTIGT TO_STRING DIE SENSORX.PARAMY GIBT
 */
public class PropertyPanel extends JPanel {

    List<Value> userInputs;
    List<SensorAttribute> sensorTemplate;

    public PropertyPanel(List<UserInput> userInputs) {

        super(new GridLayout(1,0));

        //TODO DELETE DELETE
        sensorTemplate = new ArrayList<SensorAttribute>();
        SensorAttribute sa = new SensorAttribute();
        sa.setSensor(new Sensor());
        sensorTemplate.add(sa);
        sa = new SensorAttribute();
        sa.setSensor(new Sensor());
        sensorTemplate.add(new SensorAttribute());


        this.userInputs = new ArrayList<Value>();

        //sort out const and sensor values for display
        for (UserInput uInput : userInputs) {

            if (uInput.getValue() instanceof ConstValue || uInput.getValue() instanceof SensorValue) {
                this.userInputs.add(uInput.getValue());
            }
        }

        PropertyJTable table = new PropertyJTable(new PropertyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(300, 70));

        table.setDefaultRenderer(Object.class, new PropertyCellRenderer());
        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);


        add(scrollPane);


    }
    
    /**
     * Model that converts the UserInput data into the table view
     */
    class PropertyTableModel extends AbstractTableModel {
        
        private String [] columnNames = {"Propertyname", "Propertyvalue"};
        
        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            return userInputs.size();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {

            //name
            if (col == 0) {
                return userInputs.get(row).getVarName();
            }
            else if (col == 1) {
                Value val = userInputs.get(row);
                
                if (val instanceof ConstValue) {
                    return userInputs.get(row).getValue();
                }
                //Sensorvalue
                else {
                    return ((SensorValue)val).getSensorAttribute();
                }
            }
            else {
                return "?";
            }
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell. Stupid JTable!
         */
        public Class getColumnClass(int c) {
            //return getValueAt(0, c).getClass();
            return Object.class;
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (true) {
                System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of " + value.getClass() + ")");
            }

            Value val = userInputs.get(row);
            
            if (val instanceof ConstValue) {
                ((ConstValue)val).setValue(value);
            }
            //Sensorvalue
            else {
                ((SensorValue)val).setSensorAttribute((SensorAttribute)value);
            }
            
            fireTableCellUpdated(row, col);
        }
    }

    /**
     * Table with customized Cell editor to display combo- and checkboxes
     */
    class PropertyJTable extends JTable {

        public PropertyJTable(AbstractTableModel model) {
            super(model);
        }

        @Override
        public TableCellEditor getCellEditor(int row, int column) {

            Value val = userInputs.get(row);

            //in case of sensorvalue list all available sensorattributes
            if (val instanceof SensorValue) {
                SensorValue sval = (SensorValue)val;

                JComboBox box = new JComboBox();

                for (SensorAttribute satt : sensorTemplate) {
                    box.addItem(satt);
                }

                box.setSelectedItem(this.getValueAt(row, column));

                return new DefaultCellEditor(box);
            }
            //const value: depending whether text /  integer or boolean
            else {

                ConstValue cval = (ConstValue)val;

                if (cval.getValue() instanceof Boolean) {

                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected(((Boolean)val.getValue()).booleanValue());
                    return new DefaultCellEditor(checkBox);
                }
                else if (cval.getValue() instanceof Integer) {
                    return new IntegerEditor(0,100);
                }
                //String
                else {
                    return new DefaultCellEditor(new JTextField());
                }
            }
        }
    }

    /**
     * Renderer that is responsible for painting cells if they aren't currently
     * selected for editing
     */
    class PropertyCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(
                            JTable table, Object color,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {

            Value val = userInputs.get(row);

            if (column == 0) {

                return super.getTableCellRendererComponent(table, val.getVarName(), isSelected, hasFocus, row, column);

            } else {
                if (val instanceof SensorValue) {

                    //In case of sensor value just display attribute
                    return super.getTableCellRendererComponent(table, ((SensorValue) val).getSensorAttribute(), isSelected, hasFocus, row, column);
                } //ConstValue
                else {
                    ConstValue cval = (ConstValue) val;

                    if (cval.getValue() instanceof Boolean) {
                        JCheckBox checkBox = new JCheckBox();
                        checkBox.setSelected(((Boolean) cval.getValue()).booleanValue());
                        checkBox.setBackground(super.getBackground());
                        
                        if (hasFocus) {
                            checkBox.setForeground(Color.BLUE);
                        }
                        else {
                            checkBox.setForeground(Color.WHITE);
                        }

                        return checkBox;
                    } //for integer / string just display usual textfield
                    else {
                        return super.getTableCellRendererComponent(table, cval.getValue(), isSelected, hasFocus, row, column);
                    }
                }
            }
        }
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        javax.swing.JFrame frame = new javax.swing.JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        UserInput ui1 = new UserInput("ui1","what the hell", new ConstValue("constVal1",new Boolean(true)));
        SensorAttribute sa = new SensorAttribute();
        sa.setSensor(new Sensor());
        UserInput ui2 = new UserInput("ui2","what the f***", new SensorValue("sensorVal1",sa));
        UserInput ui3 = new UserInput("ui3","what the hell", new ConstValue("constVal2",new Boolean(true)));
        UserInput ui4 = new UserInput("ui4","what the hell", new ConstValue("constVal3","test1"));
        UserInput ui5 = new UserInput("ui5","what the hell", new ConstValue("constVal4",new Integer(5)));
        UserInput ui6 = new UserInput("ui6","what the hell", new ConstValue("constVal5",new Integer(10)));
        UserInput ui7 = new UserInput("ui7","what the hell", new ConstValue("constVal6",new Integer(5)));
        UserInput ui8 = new UserInput("ui8","what the hell", new ConstValue("constVal7","test2"));
        UserInput ui9 = new UserInput("ui9","what the hell", new ConstValue("constVal8",new Boolean(true)));
        UserInput ui10 = new UserInput("ui10","what the hell", new ConstValue("constVal9",new Boolean(true)));

        ArrayList list = new ArrayList();
        list.add(ui1);
        list.add(ui2);
        list.add(ui3);
        list.add(ui4);
        list.add(ui5);
        list.add(ui6);
        list.add(ui7);
        list.add(ui8);
        list.add(ui9);
        list.add(ui10);


        //Create and set up the content pane.
        PropertyPanel newContentPane = new PropertyPanel(list);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });
    }
}
