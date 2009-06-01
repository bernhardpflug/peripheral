/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * preselectSensorPanel.java
 *
 * Created on 14.05.2009, 14:57:39
 */

package peripheral.designer.wizard;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorServer;

/**
 *
 * @author Berni
 */
public class PreselectSensorPanel extends javax.swing.JPanel {

    AddAnimationDialog parent;

    DefaultListModel availableModel;
    DefaultListModel selectedModel;

    ArrayList<Sensor> userDefinedSensors;

    /** Creates new form preselectSensorPanel */
    public PreselectSensorPanel(AddAnimationDialog parent) {
        initComponents();

        this.parent = parent;

        availableModel = new DefaultListModel();
        selectedModel = new DefaultListModel();

        this.selectedSensorList.setCellRenderer(new SensorCellRenderer());
        this.availableSensorList.setModel(availableModel);
        this.selectedSensorList.setModel(selectedModel);
        

    }

    /**
     * Must be called to fill lists (can't be done in constructor as symboladapter might not be known at this time
     */
    public void initPanel() {
        
        //sensorname
        this.symboladapterNameTextfield.setText(parent.getCreatedAdapter().getName());

        //preselected sensors
        initUserDefinedSensors();
        initAvailableList();
        initSelectedList();
    }

    public String getUserDefinedSymbolAdapterName() {
        return this.symboladapterNameTextfield.getText();
    }

    private void initUserDefinedSensors() {

        userDefinedSensors = new ArrayList<Sensor>();

        ArrayList<SensorServer> definedSensorServer = (ArrayList<SensorServer>) DisplayConfiguration.getInstance().getSensorServer();

        for (SensorServer server : definedSensorServer) {

            ArrayList<Sensor> sensors = server.getSensorList();

            for (Sensor sensor : sensors) {
                userDefinedSensors.add(sensor);
            }
        }

    }

    private void initAvailableList() {

        List<Sensor> alreadyPreselected = parent.getCreatedAdapter().getPreselectedSensors();

        for (Sensor sensor : userDefinedSensors) {

            //check if sensor is not already as preselected Sensors of symbol adpater
            if (!alreadyPreselected.contains(sensor)) {
                availableModel.addElement(sensor);
            }
        }
    }

    private void initSelectedList() {

        List<Sensor> alreadyPreselected = parent.getCreatedAdapter().getPreselectedSensors();

        for (Sensor sensor : alreadyPreselected) {
            selectedModel.addElement(sensor);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        symboladapterNameTextfield = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        removeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedSensorList = new javax.swing.JList();
        sensorList = new javax.swing.JScrollPane();
        availableSensorList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Name of Animation"));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(symboladapterNameTextfield, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(symboladapterNameTextfield, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Preselect Sensors for Properties"));

        removeButton.setText("<");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Available Sensors");

        jScrollPane1.setViewportView(selectedSensorList);

        sensorList.setViewportView(availableSensorList);

        jLabel2.setText("Selected Sensors");

        addButton.setText(">");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(sensorList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(addButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(removeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabel1))
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(78, 78, 78)
                        .add(addButton)
                        .add(53, 53, 53)
                        .add(removeButton))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .add(sensorList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))))
                .add(11, 11, 11))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(30, 30, 30)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 344, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        Object [] selectedElems = this.availableSensorList.getSelectedValues();
        if (selectedElems != null && selectedElems.length > 0) {

            for (int i=0; i < selectedElems.length; i++) {

                Sensor sensor = (Sensor)selectedElems[i];

                //remove from available and add to selected model
                availableModel.removeElement(sensor);
                selectedModel.addElement(sensor);

                //add to preselected sensors
                parent.getCreatedAdapter().getPreselectedSensors().add(sensor);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed

        Object [] selectedElems = this.selectedSensorList.getSelectedValues();

        if (selectedElems != null && selectedElems.length > 0) {

            for (int i=0; i < selectedElems.length; i++) {

                Sensor sensor = (Sensor)selectedElems[i];

                //only allow removing this sensor if it is contained by currently user defined sensors
                if (userDefinedSensors.contains(sensor)) {

                    //CHEck if sensor is used in user inputs
                    if (parent.getCreatedAdapter().isUsed(sensor)) {

                        //ask user if really wants to delete this sensor
                        Object[] options = {"YES","NO"};
                        String title = "Sensor is currently in use";
                        String text = "The sensor "+sensor+" is used by some properties of this animation\n" +
                                "If you remove it all properties with values of this sensor will be reset\n" +
                                "Remove anyway?";

                        int answer = JOptionPane.showOptionDialog(
                                parent,
                                text,
                                title,
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE,
                                null,
                                options,
                                options[1]);

                        if (answer == JOptionPane.YES_OPTION) {
                            availableModel.addElement(sensor);
                            selectedModel.removeElement(sensor);

                            //use special method to also delete dependencies
                            ArrayList<Sensor> list = new ArrayList<Sensor>();
                            list.add(sensor);
                            parent.getCreatedAdapter().removeSensorsWithDependencies(list);
                        }

                    }
                    //just remove
                    else {
                        availableModel.addElement(sensor);
                        selectedModel.removeElement(sensor);

                        //remove from preselected sensors
                        parent.getCreatedAdapter().getPreselectedSensors().remove(sensor);
                    }
                }
            }
        }
    }//GEN-LAST:event_removeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JList availableSensorList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JList selectedSensorList;
    private javax.swing.JScrollPane sensorList;
    private javax.swing.JTextField symboladapterNameTextfield;
    // End of variables declaration//GEN-END:variables

    class SensorCellRenderer extends javax.swing.JLabel implements javax.swing.ListCellRenderer {

        // This is the only method defined by ListCellRenderer.
        // We just reconfigure the JLabel each time we're called.
        public java.awt.Component getListCellRendererComponent(
                javax.swing.JList list,
                Object value, // value to display
                int index, // cell index
                boolean isSelected, // is the cell selected
                boolean cellHasFocus) // the list and the cell have the focus
        {
            String s = value.toString();
            setText(s);

            //call internal method that decides
            setForeground(getForeground(list,isSelected,value));

            if (isSelected) {
                setBackground(list.getSelectionBackground());

            } else {
                setBackground(list.getBackground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
            return this;
        }

        private java.awt.Color getForeground(javax.swing.JList list,boolean isSelected, Object value)
                throws IllegalArgumentException{

            if (value instanceof Sensor) {
                Sensor sensor = (Sensor)value;

                if (userDefinedSensors.contains(sensor)) {
                    
                    if (isSelected) {
                        return list.getSelectionForeground();
                    }
                    else {
                        return list.getForeground();
                    }
                }
                else {
                    
                    return java.awt.Color.RED;
                }
            }
            else {
                throw new IllegalArgumentException(value + " is not of class Sensor");
            }
        }
    }
}
