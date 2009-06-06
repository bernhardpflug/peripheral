/*
 * Panel that takes UserInput, creates GUI elements for changing them
 * and returns the new defined values
 */
package peripheral.designer.property;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import peripheral.designer.ImageFileChooser;
import peripheral.logic.datatype.Directory;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.symboladapter.SymbolAdapter;
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

    public enum FileType {
            FILE,
            DIRECTORY
    }

    SymbolAdapter symbolAdapter;
    List<Value> userInputs;

    public PropertyPanel(SymbolAdapter symbolAdapter) {
        this(symbolAdapter, symbolAdapter.getNeededUserInput());
    }

    public PropertyPanel(SymbolAdapter symbolAdapter, List<UserInput> userInputs) {
        super(new GridLayout(1, 0));

        this.symbolAdapter = symbolAdapter;

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
     * requests all sensors from the sensorserver and returns all channels
     * with appropriate data type
     * @param allowedType type of SensorValue that should fit to sensorchannel
     * @return list with all sensorchannels with appropriate type
     */
    private List<SensorChannel> getAppropriateSensorChannels(Class allowedType) {

        ArrayList<SensorChannel> channels = new ArrayList<SensorChannel>();

        channels.add(SensorChannel.getDummy());

        ArrayList<Sensor> sensors = symbolAdapter.getPreselectedSensors();

        for (Sensor sensor : sensors) {

            channels.addAll(sensor.getSensorChannels(allowedType));
        }


        return channels;
    }

    /**
     * Model that converts the UserInput data into the table view
     */
    class PropertyTableModel extends AbstractTableModel {

        private String[] columnNames = {"Propertyname", "Propertyvalue"};

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
            } else if (col == 1) {
                Value val = userInputs.get(row);

                if (val instanceof ConstValue) {
                    return userInputs.get(row).getValue();
                } //Sensorvalue
                else {
                    return ((SensorValue) val).getSensorChannel();
                }
            } else {
                return "?";
            }
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell. Stupid JTable!
         */
        @Override
        public Class getColumnClass(int c) {
            //return getValueAt(0, c).getClass();
            return Object.class;
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        @Override
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
        @Override
        public void setValueAt(Object value, int row, int col) {
//            if (true) {
//                System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of " + value.getClass() + ")");
//            }

            Value val = userInputs.get(row);

            if (val instanceof ConstValue) {
                ((ConstValue) val).setValue(value);
            } //Sensorvalue
            else {
                ((SensorValue) val).setSensorChannel((SensorChannel) value);
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
                SensorValue sval = (SensorValue) val;

                JComboBox box = new JComboBox();

                for (SensorChannel satt : getAppropriateSensorChannels(sval.getValueType())) {
                    box.addItem(satt);
                }

                box.setSelectedItem(this.getValueAt(row, column));

                return new DefaultCellEditor(box);
            } //const value: depending whether text, enum, number or boolean
            else {

                ConstValue cval = (ConstValue) val;
                //System.out.println(cval.getValue().getClass().toString());


                if (cval.getValueType().equals(Directory.class)) {
                    //return special defined celleditor below that displays filechooser
                    //with directory selection only
                    return new FileTableCellEditor((Directory)cval.getValue(),FileType.DIRECTORY);
                }
                else if (cval.getValueType().equals(File.class)) {
                    //return special defined celleditor below that displays filechooser
                    //with image selection only
                    return new FileTableCellEditor((File)cval.getValue(),FileType.FILE);
                } //Boolean
                else if (cval.getValue() instanceof Boolean) {

                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected(((Boolean) val.getValue()).booleanValue());
                    return new DefaultCellEditor(checkBox);
                } //Number
                else if (cval.getValue() instanceof Float || cval.getValue() instanceof Double || cval.getValue() instanceof Long || cval.getValue() instanceof Short || cval.getValue() instanceof Integer) {
                    return new NumberEditor(cval.getValueType());
                } //enum
                else if (cval.getValue().getClass().isEnum()) {

                    //get all constants of this enumeration and display them in combo box
                    Object[] constants = cval.getValue().getClass().getEnumConstants();

                    JComboBox box = new JComboBox();

                    for (Object constant : constants) {
                        box.addItem(constant);
                    }

                    box.setSelectedItem(cval.getValue());

                    return new DefaultCellEditor(box);
                } //String
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
                    return super.getTableCellRendererComponent(table, ((SensorValue) val).getSensorChannel(), isSelected, hasFocus, row, column);
                } //ConstValue
                else {
                    ConstValue cval = (ConstValue) val;

                    if (cval.getValue() instanceof Boolean) {
                        JCheckBox checkBox = new JCheckBox();
                        checkBox.setSelected(((Boolean) cval.getValue()).booleanValue());
                        checkBox.setBackground(super.getBackground());

                        if (hasFocus) {
                            checkBox.setForeground(Color.BLUE);
                        } else {
                            checkBox.setForeground(Color.WHITE);
                        }

                        return checkBox;
                    } else if (cval.getValueType().equals(File.class) || cval.getValueType().equals(Directory.class)) {

                        String sval = "no file selected";

                        if (cval.getValueType().equals(Directory.class)) {
                            sval = "no directory selected";
                        }
                        
                        if (cval.getValue() != null) {
                            sval = ((File) cval.getValue()).getName();
                        }
                        return super.getTableCellRendererComponent(table, sval, isSelected, hasFocus, row, column);
                    } //for integer / string just display usual textfield
                    else {
                        return super.getTableCellRendererComponent(table, cval.getValue(), isSelected, hasFocus, row, column);
                    }
                }
            }
        }
    }

    /**
     * special cell editor that opens a file chooser to allow
     * file selection for file values
     */
    class FileTableCellEditor extends javax.swing.AbstractCellEditor implements TableCellEditor {

        private FileType type;
        private File alreadySelected;
        private File file;

        public FileTableCellEditor(File alreadySelected,FileType type) {
            this.type = type;
            this.alreadySelected = alreadySelected;
        }
        
        public Object getCellEditorValue() {

            if (type == FileType.FILE) {
                return file;
            }
            else {
                return new Directory(file.getPath());
            }
        }

        /**
         * creates a button that opens the dialog for file selection
         * @param table
         * @param value
         * @param isSelected
         * @param row
         * @param column
         * @return
         */
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton btn = new JButton("Choose file");
            btn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JFileChooser fc = null;

                    if (type == FileType.FILE) {
                        fc = new ImageFileChooser(true);
                    }
                    else {
                        fc = new JFileChooser();
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    }

                    if (alreadySelected != null) {
                        
                        fc.setCurrentDirectory(alreadySelected);
                        fc.setSelectedFile(alreadySelected);
                    }

                    if (fc.showOpenDialog(null) == fc.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                        fireEditingStopped();
                    }
                    else {
                        fireEditingCanceled();
                    }
                }
            });

            return btn;
        }

    }
}
