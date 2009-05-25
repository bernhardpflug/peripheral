/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * createLocationsSymbolsPanel.java
 *
 * Created on 14.05.2009, 15:33:34
 */

package peripheral.designer.wizard;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JOptionPane;
import peripheral.designer.ImageFileChooser;
import peripheral.designer.ImageFilter;
import peripheral.designer.preview.PreviewDialog;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;

/**
 *
 * @author Berni
 */
public class LocationsSymbolsPanel extends javax.swing.JPanel {

    AddAnimationDialog parent;
    private JFileChooser fileChooser;

    /** Creates new form createLocationsSymbolsPanel */
    public LocationsSymbolsPanel(AddAnimationDialog parent) {
        initComponents();
        
        this.parent = parent;

        //define editable models for lists
        this.LocationList.setModel(new DefaultListModel());
        this.symbolList.setModel(new DefaultListModel());

        fillLocationList();
    }

    //fill location list with data from symboladapter
    private void fillLocationList() {
        SymbolAdapter symbolAdapter = parent.getCreatedAdapter();

        DefaultListModel model = (DefaultListModel)this.LocationList.getModel();

        model.clear();

        model.addElement(new Point());

        //for (PositioningTool pos : symbolAdapter)

        //TODO where to save positioning tools
    }

    private void fillSymbolList(PositioningTool pos) {

        DefaultListModel model = (DefaultListModel)this.symbolList.getModel();

        model.clear();

        if (pos != null) {
            for (Symbol symbol : pos.getSymbols()) {

                model.addElement(symbol);
            }
        }
    }

    private void updateGUICoordinates(PositioningTool pos) {

        if (pos instanceof Point) {

            Point point = (Point)pos;

            this.xLabel.setText("X");
            this.xTextField.setText(""+point.getPosition().x);

            this.yLabel.setText("Y");
            this.yTextField.setText(""+point.getPosition().y);

            this.widthLabel.setText("    -    ");
            this.widthTextField.setEnabled(false);
            this.widthTextField.setText("");

            this.heightLabel.setText("    -    ");
            this.heightTextField.setEnabled(false);
            this.heightTextField.setText("");
        }
        else if (pos instanceof Line) {
            Line line = (Line)pos;

            //abklären ob wir datatype klassen verwenden
            /*
            this.xLabel.setText("X1");
            this.xTextField.setText(""+line.getStartPoint().x);

            this.yLabel.setText("Y1");
            this.yTextField.setText(""+line.getStartPoint().y);

            this.widthLabel.setText("X2");
            this.widthTextField.setEnabled(true);
            this.widthTextField.setText(""+line.getEndPoint().x);

            this.heightLabel.setText("Y2");
            this.heightTextField.setEnabled(true);
            this.heightTextField.setText(""+line.getEndPoint().y);
             * */
        }
        else if (pos instanceof Region) {
            Region region = (Region)pos;

            /*
            this.xLabel.setText("X");
            this.xTextField.setText(""+region.getBounds());

            this.yLabel.setText("Y");
            this.yTextField.setText(""+line.getStartPoint().y);

            this.widthLabel.setText("Width");
            this.widthTextField.setEnabled(true);
            this.widthTextField.setText(""+line.getEndPoint().x);

            this.heightLabel.setText("Height");
            this.heightTextField.setEnabled(true);
            this.heightTextField.setText(""+line.getEndPoint().y);
             * */
        }
    }

    /**
     * gets invoked by boundsVerifier if input was correct and positiontool
     * value should be updated
     * @param field
     */
    public void updatePositionToolCoordinates(JFormattedTextField field) {

        int value = Integer.parseInt(field.getText());

        PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();

        if (field.getName().equals("xTextField")) {

            if (pos instanceof Point) {
                ((Point)pos).getPosition().x = value;
            }
            else if (pos instanceof Line) {
                //TODO
            }
            else if (pos instanceof Region) {
                //TODO
            }
            
        }
        else if (field.getName().equals("yTextField")) {

            if (pos instanceof Point) {
                ((Point)pos).getPosition().y = value;
            }
            else if (pos instanceof Line) {
                //TODO
            }
            else if (pos instanceof Region) {
                //TODO
            }

        }

        PreviewDialog.getInstance().updatePreview();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LocationPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LocationList = new javax.swing.JList();
        addLocationButton = new javax.swing.JButton();
        removeLocationButton = new javax.swing.JButton();
        xLabel = new javax.swing.JLabel();
        yLabel = new javax.swing.JLabel();
        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        priorityUpButton = new javax.swing.JButton();
        priorityDownButton = new javax.swing.JButton();
        xTextField = new javax.swing.JFormattedTextField();
        yTextField = new javax.swing.JFormattedTextField();
        widthTextField = new javax.swing.JFormattedTextField();
        heightTextField = new javax.swing.JFormattedTextField();
        symbolPanel = new javax.swing.JPanel();
        addSymbolButton = new javax.swing.JButton();
        removeSymbolButton = new javax.swing.JButton();
        alterSymbolButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        symbolList = new javax.swing.JList();
        displaySymbolCheckBox = new javax.swing.JCheckBox();

        LocationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Point(s), Area(s)"));

        LocationList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                LocationListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(LocationList);

        addLocationButton.setText("+");

        removeLocationButton.setText("-");

        xLabel.setText("X");

        yLabel.setText("Y");

        widthLabel.setText("Width");

        heightLabel.setText("Height");

        priorityUpButton.setText("UP");

        priorityDownButton.setText("DOWN");

        xTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        xTextField.setInputVerifier(new BoundsVerifier(this));
        xTextField.setName("xTextField"); // NOI18N

        yTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        yTextField.setInputVerifier(new BoundsVerifier(this));
        yTextField.setName("yTextField"); // NOI18N

        widthTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        widthTextField.setInputVerifier(new BoundsVerifier(this));
        widthTextField.setName("widthTextField"); // NOI18N

        heightTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        heightTextField.setInputVerifier(new BoundsVerifier(this));
        heightTextField.setName("heightTextField"); // NOI18N

        org.jdesktop.layout.GroupLayout LocationPanelLayout = new org.jdesktop.layout.GroupLayout(LocationPanel);
        LocationPanel.setLayout(LocationPanelLayout);
        LocationPanelLayout.setHorizontalGroup(
            LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(LocationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(LocationPanelLayout.createSequentialGroup()
                        .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, yLabel)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, xLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(yTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(xTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(widthLabel)
                            .add(heightLabel))
                        .add(8, 8, 8)
                        .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(heightTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(widthTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(2, 2, 2))
                    .add(LocationPanelLayout.createSequentialGroup()
                        .add(jScrollPane1, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(priorityUpButton)
                    .add(LocationPanelLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(priorityDownButton))
                    .add(removeLocationButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(addLocationButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        LocationPanelLayout.setVerticalGroup(
            LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(LocationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(LocationPanelLayout.createSequentialGroup()
                        .add(addLocationButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(removeLocationButton)
                        .add(9, 9, 9)
                        .add(priorityUpButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(priorityDownButton)
                        .add(109, 109, 109))
                    .add(LocationPanelLayout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(xLabel)
                    .add(widthLabel)
                    .add(widthTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(xTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(LocationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(yLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(heightLabel)
                    .add(yTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(heightTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        symbolPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Symbols for selected Location"));

        addSymbolButton.setText("+");
        addSymbolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSymbolButtonActionPerformed(evt);
            }
        });

        removeSymbolButton.setText("-");

        alterSymbolButton.setText("...");

        jScrollPane2.setViewportView(symbolList);

        displaySymbolCheckBox.setSelected(true);
        displaySymbolCheckBox.setText("display symbol in preview");
        displaySymbolCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                displaySymbolCheckBoxStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout symbolPanelLayout = new org.jdesktop.layout.GroupLayout(symbolPanel);
        symbolPanel.setLayout(symbolPanelLayout);
        symbolPanelLayout.setHorizontalGroup(
            symbolPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(symbolPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(symbolPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, symbolPanelLayout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(symbolPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, symbolPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(addSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(removeSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, alterSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(displaySymbolCheckBox))
                .addContainerGap())
        );
        symbolPanelLayout.setVerticalGroup(
            symbolPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(symbolPanelLayout.createSequentialGroup()
                .add(symbolPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, symbolPanelLayout.createSequentialGroup()
                        .add(58, 58, 58)
                        .add(addSymbolButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(removeSymbolButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(alterSymbolButton))
                    .add(symbolPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(displaySymbolCheckBox))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(LocationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 337, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(29, 29, 29)
                .add(symbolPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(symbolPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(LocationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LocationListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_LocationListValueChanged


        if (this.LocationList.getSelectedValue() != null) {

            PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();

            //fill symbols
            fillSymbolList(pos);

            //update values in textfields
            this.updateGUICoordinates(pos);

            //save current state of checkbox to new selected position tool
            pos.setSymbolDisplayed(this.displaySymbolCheckBox.isSelected());

            //give preview dialog list with all positioningtools to paint
            ArrayList list = new ArrayList();
            list.add(pos);
            PreviewDialog.getInstance().setPositioningtoolsToPaint(list);
            PreviewDialog.getInstance().updatePreview();
        }
        else {
            fillSymbolList(null);
        }
    }//GEN-LAST:event_LocationListValueChanged

    private void addSymbolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSymbolButtonActionPerformed

        if (this.LocationList.getSelectedValue() != null) {

            PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();

            if (fileChooser == null) {
                fileChooser = new ImageFileChooser();
            }

            fileChooser.setDialogTitle("Select an image file for the symbol");

            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                Symbol symbol = new Symbol(fileChooser.getSelectedFile());
                pos.getSymbols().add(symbol);

                fillSymbolList(pos);

                //display symbol in preview if checkbox enabled
                PreviewDialog.getInstance().updatePreview();
            }


        }
        else {
            JOptionPane.showMessageDialog(this, "Select a Position to add the Symbol to","No Position selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_addSymbolButtonActionPerformed

    private void displaySymbolCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_displaySymbolCheckBoxStateChanged

        if (this.LocationList.getSelectedValue() != null) {

            PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();

            pos.setSymbolDisplayed(this.displaySymbolCheckBox.isSelected());

            PreviewDialog.getInstance().updatePreview();
        }
    }//GEN-LAST:event_displaySymbolCheckBoxStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList LocationList;
    private javax.swing.JPanel LocationPanel;
    private javax.swing.JButton addLocationButton;
    private javax.swing.JButton addSymbolButton;
    private javax.swing.JButton alterSymbolButton;
    private javax.swing.JCheckBox displaySymbolCheckBox;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JFormattedTextField heightTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton priorityDownButton;
    private javax.swing.JButton priorityUpButton;
    private javax.swing.JButton removeLocationButton;
    private javax.swing.JButton removeSymbolButton;
    private javax.swing.JList symbolList;
    private javax.swing.JPanel symbolPanel;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JFormattedTextField widthTextField;
    private javax.swing.JLabel xLabel;
    private javax.swing.JFormattedTextField xTextField;
    private javax.swing.JLabel yLabel;
    private javax.swing.JFormattedTextField yTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * Class that allows to verify whether inputted value
     * is within bounds of background image
     */
    class BoundsVerifier extends InputVerifier {

        private LocationsSymbolsPanel panel;

        public BoundsVerifier(LocationsSymbolsPanel panel) {
            this.panel = panel;
        }
        public boolean verify(JComponent input) {
            if (input instanceof JFormattedTextField) {
                JFormattedTextField ftf = (JFormattedTextField) input;

                try {
                    int value = Integer.parseInt(ftf.getText());

                    if (ftf.getName().equals("xTextField")) {
                        if (value >= 0 && value <= DisplayConfiguration.getInstance().getWidth()) {
                            panel.updatePositionToolCoordinates(ftf);
                            return true;
                        } else {
                            return false;
                        }
                    } else if (ftf.getName().equals("yTextField")) {
                        if (value >= 0 && value <= DisplayConfiguration.getInstance().getHeight()) {
                            panel.updatePositionToolCoordinates(ftf);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    else if (ftf.getName().equals("widthTextField")) {
                        if (value >= 0 && value <= DisplayConfiguration.getInstance().getWidth()) {
                            panel.updatePositionToolCoordinates(ftf);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    else if (ftf.getName().equals("heightTextField")) {
                        if (value >= 0 && value <= DisplayConfiguration.getInstance().getHeight()) {
                            panel.updatePositionToolCoordinates(ftf);
                            return true;
                        } else {
                            return false;
                        }
                    }
                } catch (NumberFormatException ex) {
                    return false;
                }
            }

            return true;
        }

        public boolean shouldYieldFocus(JComponent input) {
          return verify(input);
      }
  }

}
