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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import peripheral.designer.ImageFileChooser;
import peripheral.designer.preview.ChangeListener;
import peripheral.designer.preview.PreviewDialog;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.positioningtool.Point;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.positioningtool.ToolList;
import peripheral.logic.symboladapter.Symbol;

/**
 *
 * @author Berni
 */
public class LocationsSymbolsPanel extends javax.swing.JPanel implements ChangeListener{

    AddAnimationDialog parent;
    private JFileChooser fileChooser;

    //reference to ActionTool
    ActionTool actionTool;

    //reference that is set if positioning tool is dragged
    private PositioningTool currentlyDragging;

    private SymbolSelectionDialog symbolDialog;

    /** Creates new form createLocationsSymbolsPanel */
    public LocationsSymbolsPanel(AddAnimationDialog parent) {
        initComponents();
        
        this.parent = parent;

        //define editable models for lists
        this.LocationList.setModel(new DefaultListModel());
        this.symbolList.setModel(new DefaultListModel());
    }

    public void setActionTool(ActionTool actionTool) {
        this.actionTool = actionTool;

        fillLocationList();

        DefaultListModel model = (DefaultListModel)this.LocationList.getModel();

        if (!model.isEmpty()) {
            this.LocationList.setSelectedIndex(0);
        }

        //depending whether actionTool is toollist en/disable add remove buttons
        if (actionTool instanceof ToolList) {
            this.addLocationButton.setEnabled(true);
            this.removeLocationButton.setEnabled(true);
            this.priorityUpButton.setEnabled(true);
            this.priorityDownButton.setEnabled(true);
        }
        else {
            this.addLocationButton.setEnabled(false);
            this.removeLocationButton.setEnabled(false);
            this.priorityUpButton.setEnabled(false);
            this.priorityDownButton.setEnabled(false);
        }
    }

    //fill location list with data from symboladapter
    private void fillLocationList() {
        
        DefaultListModel model = (DefaultListModel)this.LocationList.getModel();

        model.clear();

        for (PositioningTool tool : actionTool.getElements()) {
            model.addElement(tool);
        }
        
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

        if ((pos instanceof Point) && ! (pos instanceof Line)) {

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
             
        }
        else if (pos instanceof Region) {
            Region region = (Region)pos;

            
            this.xLabel.setText("X");
            this.xTextField.setText(""+region.getBounds().x);

            this.yLabel.setText("Y");
            this.yTextField.setText(""+region.getBounds().y);

            this.widthLabel.setText("Width");
            this.widthTextField.setEnabled(true);
            this.widthTextField.setText(""+region.getBounds().width);

            this.heightLabel.setText("Height");
            this.heightTextField.setEnabled(true);
            this.heightTextField.setText(""+region.getBounds().height);
            
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

            if (((pos instanceof Point) && ! (pos instanceof Line))) {
                ((Point)pos).getPosition().x = value;
            }
            else if (pos instanceof Line) {
                ((Line)pos).getStartPoint().x = value;
                ((Line)pos).getPosition().x = value;
            }
            else if (pos instanceof Region) {
                ((Region)pos).getBounds().x = value;
            }
            
        }
        else if (field.getName().equals("yTextField")) {

            if (((pos instanceof Point) && ! (pos instanceof Line))) {
                ((Point)pos).getPosition().y = value;
            }
            else if (pos instanceof Line) {
                ((Line)pos).getStartPoint().y = value;
                ((Line)pos).getPosition().y = value;
            }
            else if (pos instanceof Region) {
                ((Region)pos).getBounds().y = value;
            }

        }
        else if (field.getName().equals("widthTextField")) {

            if (pos instanceof Line) {
                ((Line)pos).getEndPoint().x = value;
            }
            else if (pos instanceof Region) {
                ((Region)pos).getBounds().width = value;
            }

        }
        else if (field.getName().equals("heightTextField")) {

            if (pos instanceof Line) {
                ((Line)pos).getEndPoint().y = value;
            }
            else if (pos instanceof Region) {
                ((Region)pos).getBounds().height = value;
            }

        }

        PreviewDialog.getInstance().updatePreview();
    }

    public void dragStart(java.awt.Point origin) {

        if (currentlyDragging == null) {

            //go through all positioning tools and find first one that is dragable at given point
            for (java.util.Enumeration<PositioningTool> e = (Enumeration<PositioningTool>) ((DefaultListModel)this.LocationList.getModel()).elements(); e.hasMoreElements();) {

                PositioningTool tool = e.nextElement();

                if (tool.dragable(origin.x, origin.y)) {

                    currentlyDragging = tool;
                    currentlyDragging.dragStart(origin);
                    return;
                }
            }
        }
    }

    public void dragAction(java.awt.Point newPosition) {

        //drag start was successfull drag choosen positioningtool and update its coordinates in GUI
        if (currentlyDragging != null) {
            currentlyDragging.dragAction(newPosition);

            //if dragged symbol is currently selected update its GUI Coordinates
            if (currentlyDragging.equals(this.LocationList.getSelectedValue())) {
            this.updateGUICoordinates(currentlyDragging);
        }
        }
    }

    public void dragStop() {

        if (currentlyDragging != null) {
            currentlyDragging.dragStop();

            currentlyDragging = null;
        }
    }

    /**
     * method that checks wether all positioning tools of this adapter
     * have at least min number of symbols defined in symboladapter
     * @return
     */
    public boolean minNumberOfSymbolsCheck() {

        //handle toollist
        if (actionTool instanceof ToolList) {

            ToolList toollist = (ToolList) actionTool;

            for (PositioningTool tool : toollist.getElements()) {
                if (tool.getSymbols().size() < parent.getCreatedAdapter().getMinAllowedNumberOfSymbols()) {
                    return false;
                }
            }

            return true;
        }
        //handle spimple positioning tool
        else {
            return ((PositioningTool)actionTool).getSymbols().size() >= parent.getCreatedAdapter().getMinAllowedNumberOfSymbols();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        symbolList = new javax.swing.JList();
        displaySymbolCheckBox = new javax.swing.JCheckBox();

        LocationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Point(s), Area(s)"));

        LocationList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        LocationList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                LocationListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(LocationList);

        addLocationButton.setText("+");
        addLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLocationButtonActionPerformed(evt);
            }
        });

        removeLocationButton.setText("-");
        removeLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLocationButtonActionPerformed(evt);
            }
        });

        xLabel.setText("X");

        yLabel.setText("Y");

        widthLabel.setText("Width");

        heightLabel.setText("Height");

        priorityUpButton.setText("UP");
        priorityUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priorityUpButtonActionPerformed(evt);
            }
        });

        priorityDownButton.setText("DOWN");
        priorityDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priorityDownButtonActionPerformed(evt);
            }
        });

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
        removeSymbolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSymbolButtonActionPerformed(evt);
            }
        });

        symbolList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        symbolList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                symbolListValueChanged(evt);
            }
        });
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
                            .add(addSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(removeSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
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
                        .add(removeSymbolButton))
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
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, symbolPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, LocationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LocationListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_LocationListValueChanged


        if (this.LocationList.getSelectedValue() != null) {

            PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();

            //fill symbols
            fillSymbolList(pos);


            //if displayd symbol isn't null select it in symbols list
            if (pos.getDisplayedSymbol() != null) {
                this.symbolList.setSelectedValue(pos.getDisplayedSymbol(), true);
            }

            //update values in textfields
            this.updateGUICoordinates(pos);

            this.displaySymbolCheckBox.setSelected(pos.isSymbolDisplayed());

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

            PositioningTool pos = (PositioningTool) this.LocationList.getSelectedValue();

            //only allow add if adapter allows more symbols than already created
            if (pos.getSymbols().size() < parent.getCreatedAdapter().getMaxAllowedNumberOfSymbols() ||
                    parent.getCreatedAdapter().getMaxAllowedNumberOfSymbols() == -1) {


                //if two images are allowed open own dialog for selection
                if (parent.getCreatedAdapter().areOrientedSymbolsAllowed()) {

                    if (symbolDialog == null) {
                        symbolDialog = new SymbolSelectionDialog(parent, true);
                    }
                    //reset selection to be able to use same dialog several times
                    //to remember file path
                    symbolDialog.resetSelection();

                    symbolDialog.setLocationRelativeTo(this);

                    //run modal dialog
                    symbolDialog.setVisible(true);

                    //create symbol with set files of dialog
                    if (symbolDialog.getLeftFile() != null) {
                        Symbol symbol = new Symbol(symbolDialog.getLeftFile(), symbolDialog.getRightFile(), pos, parent.getCreatedAdapter());

                        pos.getSymbols().add(symbol);

                        fillSymbolList(pos);

                        //select new one as selected
                        this.symbolList.setSelectedValue(symbol, true);

                        //display symbol in preview if checkbox enabled
                        PreviewDialog.getInstance().updatePreview();
                    }

                } //else display just filedialog
                else {
                    if (fileChooser == null) {
                        fileChooser = new ImageFileChooser();
                    }

                    fileChooser.setDialogTitle("Select an image file for the symbol");

                    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                        Symbol symbol = new Symbol(fileChooser.getSelectedFile(), pos, parent.getCreatedAdapter());

                        pos.getSymbols().add(symbol);

                        fillSymbolList(pos);

                        //select new one as selected
                        this.symbolList.setSelectedValue(symbol, true);

                        //display symbol in preview if checkbox enabled
                        PreviewDialog.getInstance().updatePreview();
                    }
                }
            }
            //number of symbol check
            else {
                JOptionPane.showMessageDialog(this,
                        "No more symbols allowed for this animationtype","Max number of symbols reached", JOptionPane.WARNING_MESSAGE);
            }

        }
        //position check
        else {
            JOptionPane.showMessageDialog(this,
                    "Select a Position to add the Symbol to","No Position selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_addSymbolButtonActionPerformed

    private void displaySymbolCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_displaySymbolCheckBoxStateChanged

        if (this.LocationList.getSelectedValue() != null) {

            PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();

            //setPreviewSymbol(pos);
            pos.setSymbolDisplayed(this.displaySymbolCheckBox.isSelected());

            PreviewDialog.getInstance().updatePreview();
        }
    }//GEN-LAST:event_displaySymbolCheckBoxStateChanged

    private void symbolListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_symbolListValueChanged

        //change displayed symbol to currently selected

        PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();

        //only change selected symbol if new symbol is selected, don't if currently selected is null
        //occurs with location changes
        if (this.symbolList.getSelectedValue() != null) {
            pos.setDisplayedSymbol((Symbol)this.symbolList.getSelectedValue());
            PreviewDialog.getInstance().updatePreview();
        }
    }//GEN-LAST:event_symbolListValueChanged

    private void removeSymbolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSymbolButtonActionPerformed

        if (this.symbolList.getSelectedValue() != null) {

            //if symbol is selected there must be also an position tool selected
            PositioningTool pos = (PositioningTool)this.LocationList.getSelectedValue();
            Symbol symbol = (Symbol)this.symbolList.getSelectedValue();

            //delete symbol from positioning tool
            pos.getSymbols().remove(symbol);

            //update symbols list
            this.fillSymbolList(pos);

            //Check if currently deleted is displayed symbol
            if (pos.getDisplayedSymbol().equals(symbol)) {

                //if so check if list is empty afterwards
                if (pos.getSymbols().isEmpty()) {
                    //if so set selected symbol to null
                    pos.setDisplayedSymbol(null);
                }
                else {
                    //just set new selected symbol, valueChanged handels set of displayedSymbol
                    this.symbolList.setSelectedValue((Symbol)pos.getSymbols().get(0),true);
                }

                //update preview as this affects changes in preview
                PreviewDialog.getInstance().updatePreview();
            }

        }

    }//GEN-LAST:event_removeSymbolButtonActionPerformed

    private void addLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLocationButtonActionPerformed

        //only if toollist allow add
        if (actionTool instanceof ToolList) {

            ToolList toollist = (ToolList) actionTool;

            PositioningTool newTool = null;

            if (toollist.getType().equals(Point.class)) {
                newTool = new Point();
            }
            else if (toollist.getType().equals(Line.class)) {
                newTool = new Line();
            }
            else if (toollist.getType().equals(Region.class)) {
                newTool = new Region();
            }

            toollist.getElements().add(newTool);

            fillLocationList();

            //set new one as selected
            this.LocationList.setSelectedValue(newTool, true);
        }
        
    }//GEN-LAST:event_addLocationButtonActionPerformed

    private void removeLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLocationButtonActionPerformed

        if (this.LocationList.getSelectedValue() != null && actionTool instanceof ToolList) {

            PositioningTool toDel = (PositioningTool)this.LocationList.getSelectedValue();

            ToolList toollist = (ToolList)actionTool;

            //don't allow deletion if there is only one item left
            if (toollist.getElements().size() > 1) {

                int delIndex = toollist.getElements().indexOf(toDel);

                //element to set selected after refill
                Object toSelect;

                //if deleted is not at first position select upper item
                if (delIndex > 0) {
                    toSelect = toollist.getElements().get(delIndex -1);
                } else {
                    //usually check for greater one to select but in here there must be always one element left
                    toSelect = toollist.getElements().get(1);

                }
                toollist.getElements().remove(toDel);

                fillLocationList();

                //after refill select before calculated element to select
                this.LocationList.setSelectedValue(toSelect, true);
            }
        }
    }//GEN-LAST:event_removeLocationButtonActionPerformed

    private void priorityUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityUpButtonActionPerformed

        if (this.LocationList.getSelectedValue() != null && actionTool instanceof ToolList) {

            PositioningTool tool = (PositioningTool)this.LocationList.getSelectedValue();

            List elements = ((ToolList)actionTool).getElements();

            int position = elements.indexOf(tool);

            if (position > 0) {

                //change this item with previous one
                elements.set(position, elements.get(position-1));
                elements.set(position-1, tool);
            }

            fillLocationList();

            this.LocationList.setSelectedValue(tool, true);
        }
    }//GEN-LAST:event_priorityUpButtonActionPerformed

    private void priorityDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityDownButtonActionPerformed

        if (this.LocationList.getSelectedValue() != null && actionTool instanceof ToolList) {

            PositioningTool tool = (PositioningTool)this.LocationList.getSelectedValue();

            List elements = ((ToolList)actionTool).getElements();

            int position = elements.indexOf(tool);

            if (position < elements.size() - 1) {

                //change this item with next one
                elements.set(position, elements.get(position+1));
                elements.set(position+1, tool);
            }

            fillLocationList();

            this.LocationList.setSelectedValue(tool, true);
        }
    }//GEN-LAST:event_priorityDownButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList LocationList;
    private javax.swing.JPanel LocationPanel;
    private javax.swing.JButton addLocationButton;
    private javax.swing.JButton addSymbolButton;
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
     *
     * NO LONGER USED
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

                    panel.updatePositionToolCoordinates(ftf);

                    return true;
                    
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
