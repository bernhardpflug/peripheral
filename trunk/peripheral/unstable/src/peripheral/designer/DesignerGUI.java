/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DesignerGUI.java
 *
 * Created on 14.05.2009, 14:26:13
 */
package peripheral.designer;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import peripheral.designer.preview.PreviewDialog;
import peripheral.designer.property.PropertyPanel;
import peripheral.designer.wizard.AddAnimationDialog;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.Value;

/**
 *
 * @author Berni
 */
public class DesignerGUI extends javax.swing.JFrame {

    //remember current panel
    private int currentIndex = 0;

    //reference original adapter where copy has been modified in dialog
    private SymbolAdapter modifying;

    /** Creates new form DesignerGUI */
    public DesignerGUI() {
        initComponents();

        //be informed about file selection to display image preview
        jFileChooser1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("ApproveSelection")) {
                    handleFilePreview(jFileChooser1.getSelectedFile());
                }
            }
        });
        //define model that allows to modify list data
        this.defAnimationsList.setModel(new DefaultListModel());


        //fill list with already defined animations
        fillAnimationList();
    }

    private void handleFilePreview(File selectedFile) {

        //in case of first display set location beside current window
        if (PreviewDialog.getInstance().getLocation().x == 0) {
            PreviewDialog.getInstance().setLocation((int) (this.getBounds().getX() + this.getBounds().getWidth() + 5), (int) (this.getBounds().getY()));
        }

        if (!PreviewDialog.getInstance().isVisible()) {
            PreviewDialog.getInstance().setVisible(true);
        }

        PreviewDialog.getInstance().setBackgroundImage(selectedFile);

    }

    private void fillAnimationList() {

        for (SymbolAdapter symbolAdapter : DisplayConfiguration.getInstance().getAdapter()) {

            ((DefaultListModel) this.defAnimationsList.getModel()).addElement(symbolAdapter);
        }
    }

    /**
     * is called from dialog if it closed to save created adapter
     * must be done this way as dialog may not be modal because of preview window
     * @param dialog
     */
    public void AddAnimationDialogClosed(AddAnimationDialog dialog) {

        if (dialog.completedCreation()) {
            SymbolAdapter created = dialog.getCreatedAdapter();

            DefaultListModel model = (DefaultListModel) this.defAnimationsList.getModel();

            //in case existing has been modified overwrite existing with new created
            if (dialog.modifiedExisting()) {

                int index = model.indexOf(this.modifying);

                //overwrite index of dataList and Gui list with new value
                model.set(index, created);
                DisplayConfiguration.getInstance().getAdapter().set(index, created);

            } //else add new one to lists
            else {

                model.addElement(created);
                DisplayConfiguration.getInstance().getAdapter().add(created);
            }

            //selected created element
            this.defAnimationsList.setSelectedValue(created, true);

        }

        //update preview dialog - display all positioningtools of all symboladapter in this view
        previewAllSymbolAdapters();

        //refresh properties list
        this.defAnimationsListValueChanged(null);
    }

    /**
     * method that displays all created symboladpaters in preview dialog
     */
    private void previewAllSymbolAdapters() {

        ArrayList<PositioningTool> tools = new ArrayList<PositioningTool>();

        for (SymbolAdapter adapter : DisplayConfiguration.getInstance().getAdapter()) {
            tools.addAll(adapter.getTool().getElements());
        }

        PreviewDialog.getInstance().setPositioningtoolsToPaint(tools);
        PreviewDialog.getInstance().updatePreview();
    }

    /**
     * Checks if there are sensorvalues (userinputs) in some symboladpaters that contain either
     * empty (SensorChannel.DUMMY) or no longer available sensorchannels
     * @return true if no invalid data wos found, else false
     */
    private boolean checkForInvalidUserInputValues() {

        //get all created symboladapter
        ArrayList<SymbolAdapter> adapters = (ArrayList<SymbolAdapter>) DisplayConfiguration.getInstance().getAdapter();

        for (SymbolAdapter adapter : adapters) {

            ArrayList<Value> invalid = adapter.getInvalidUserInputValues();

            if (!invalid.isEmpty()) {

                //pick first one out of list and display it in message
                Value invalidValue = invalid.get(0);

                JOptionPane.showMessageDialog(this,
                        invalidValue.getVarName() + " property of adapter " + adapter.getName() + "\n" +
                        "has an invalid state.\n" +
                        "Set an valid value to proceed",
                        "Invalid property found", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        SensorPanel = new peripheral.designer.SensorPanel();
        BackgroundPanel = new javax.swing.JPanel();
        jFileChooser1 = new peripheral.designer.ImageFileChooser();
        AnimationsPanel = new javax.swing.JPanel();
        DefAnimationsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        defAnimationsList = new javax.swing.JList();
        AnimationPropertiesPanel = new javax.swing.JPanel();
        addAnimationButton = new javax.swing.JButton();
        removeAnimationButton = new javax.swing.JButton();
        editAnimationButton = new javax.swing.JButton();
        priorityUpButton = new javax.swing.JButton();
        priorityDownButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ApplicationMenu = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        exitMenu = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        previewItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PDC - Peripheral Display Creator");

        nextButton.setText("next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        prevButton.setText("prev");
        prevButton.setEnabled(false);
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout buttonPanelLayout = new org.jdesktop.layout.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(584, Short.MAX_VALUE)
                .add(prevButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(nextButton)
                .add(29, 29, 29))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonPanelLayout.createSequentialGroup()
                .add(buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(nextButton)
                    .add(prevButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        cardPanel.setLayout(new java.awt.CardLayout());

        SensorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 1 : Select Sensorserver"));
        cardPanel.add(SensorPanel, "card2");

        BackgroundPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 2 : Select Background Image of the Scene"));

        jFileChooser1.setControlButtonsAreShown(false);
        jFileChooser1.setFileFilter(new ImageFilter());

        org.jdesktop.layout.GroupLayout BackgroundPanelLayout = new org.jdesktop.layout.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(BackgroundPanelLayout.createSequentialGroup()
                .add(85, 85, 85)
                .add(jFileChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 541, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(BackgroundPanelLayout.createSequentialGroup()
                .add(63, 63, 63)
                .add(jFileChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 304, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        cardPanel.add(BackgroundPanel, "card3");

        AnimationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 3 : Define Animations for the Scene"));

        DefAnimationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Defined Animations"));

        defAnimationsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "item1", "item2" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        defAnimationsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        defAnimationsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                defAnimationsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(defAnimationsList);

        org.jdesktop.layout.GroupLayout DefAnimationsPanelLayout = new org.jdesktop.layout.GroupLayout(DefAnimationsPanel);
        DefAnimationsPanel.setLayout(DefAnimationsPanelLayout);
        DefAnimationsPanelLayout.setHorizontalGroup(
            DefAnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, DefAnimationsPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DefAnimationsPanelLayout.setVerticalGroup(
            DefAnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(DefAnimationsPanelLayout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 347, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AnimationPropertiesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("AnimationProperties"));
        AnimationPropertiesPanel.setLayout(new java.awt.BorderLayout());

        addAnimationButton.setText("+");
        addAnimationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAnimationButtonActionPerformed(evt);
            }
        });

        removeAnimationButton.setText("-");
        removeAnimationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAnimationButtonActionPerformed(evt);
            }
        });

        editAnimationButton.setText("...");
        editAnimationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAnimationButtonActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Tip: The order in the list defines the order of painting the symbols of an adapter");

        org.jdesktop.layout.GroupLayout AnimationsPanelLayout = new org.jdesktop.layout.GroupLayout(AnimationsPanel);
        AnimationsPanel.setLayout(AnimationsPanelLayout);
        AnimationsPanelLayout.setHorizontalGroup(
            AnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(AnimationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(AnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(AnimationsPanelLayout.createSequentialGroup()
                        .add(DefAnimationsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(AnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(addAnimationButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(removeAnimationButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(editAnimationButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(priorityDownButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(priorityUpButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(32, 32, 32)
                        .add(AnimationPropertiesPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel1))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        AnimationsPanelLayout.setVerticalGroup(
            AnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(AnimationsPanelLayout.createSequentialGroup()
                .add(AnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(AnimationsPanelLayout.createSequentialGroup()
                        .add(36, 36, 36)
                        .add(addAnimationButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(removeAnimationButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(editAnimationButton)
                        .add(18, 18, 18)
                        .add(priorityUpButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(priorityDownButton))
                    .add(AnimationsPanelLayout.createSequentialGroup()
                        .add(28, 28, 28)
                        .add(AnimationsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, AnimationPropertiesPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, DefAnimationsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 383, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 14, Short.MAX_VALUE)
                .add(jLabel1))
        );

        cardPanel.add(AnimationsPanel, "card4");

        getContentPane().add(cardPanel, java.awt.BorderLayout.CENTER);

        FileMenu.setText("File");

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        FileMenu.add(exitMenu);

        ApplicationMenu.add(FileMenu);

        viewMenu.setText("View");

        previewItem.setText("show Preview");
        previewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewItemActionPerformed(evt);
            }
        });
        viewMenu.add(previewItem);

        ApplicationMenu.add(viewMenu);

        setJMenuBar(ApplicationMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAnimationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAnimationButtonActionPerformed

        AddAnimationDialog aaD = new AddAnimationDialog(this);
        aaD.setLocationRelativeTo(this);
        aaD.setVisible(true);

        //remove all positioningtools from preview
        PreviewDialog.getInstance().setPositioningtoolsToPaint(null);
        PreviewDialog.getInstance().updatePreview();
    }//GEN-LAST:event_addAnimationButtonActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed

        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_exitMenuActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        CardLayout cl = ((java.awt.CardLayout) cardPanel.getLayout());

        //Sensorpanel
        if (currentIndex == 0) {
            prevButton.setEnabled(true);

            //if file is already set display it
            File alreadyExisting = DisplayConfiguration.getInstance().getBackgroundImageFile();

            if (alreadyExisting != null) {
                this.jFileChooser1.setCurrentDirectory(alreadyExisting);
                this.jFileChooser1.setSelectedFile(alreadyExisting);
            }

            currentIndex++;
            cl.next(cardPanel);
        } //background panel
        else if (currentIndex == 1) {

            //Save selected image in display configuration
            File selectedFile = this.jFileChooser1.getSelectedFile();

            if (selectedFile != null && PreviewDialog.isExtentionSupported(selectedFile)) {

                DisplayConfiguration.getInstance().setBackgroundImage(PreviewDialog.getInstance().getBackgroundImage());
                DisplayConfiguration.getInstance().setBackgroundImageFile(selectedFile);

                nextButton.setText("Save");
                currentIndex++;
                cl.next(cardPanel);
            } else {

                JOptionPane.showMessageDialog(this, "Please select a supported image file\n" +
                        "Supported are jpg, bmp, gif, png files", "Unable to proceed", JOptionPane.ERROR_MESSAGE);

            }

        } //animation panel
        else if (currentIndex == 2) {

            //only allow continuing if there are no corrupt symboladapter
            if (this.checkForInvalidUserInputValues()) {

                //TODO open save dialog
                JFileChooser saveFileChooser = new JFileChooser();

                if (saveFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = saveFileChooser.getSelectedFile();

                    if (selectedFile.getName().endsWith(".zip")) {

                        try {
                            DisplayConfiguration.getInstance().save(saveFileChooser.getSelectedFile().getAbsolutePath());
                            this.setVisible(false);
                            this.dispose();
                            System.exit(0);
                        }
                        catch (java.io.IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(this, "INTERNAL ERROR\nConfiguration couldn't be saved" +
                                    "\nSee console output for further information",
                                "Failed to save file", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "File must use extension '.zip'",
                                "Invalid file extension", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }

    }//GEN-LAST:event_nextButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        CardLayout cl = ((java.awt.CardLayout) cardPanel.getLayout());

        //Sensorpanel
        if (currentIndex == 0) {
            return;
        } //background panel
        else if (currentIndex == 1) {
            prevButton.setEnabled(false);
        } //animation panel
        else if (currentIndex == 2) {
            nextButton.setText("next");
        }

        currentIndex--;
        cl.previous(cardPanel);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void defAnimationsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_defAnimationsListValueChanged

        if (defAnimationsList.getSelectedValue() != null) {

            //PROPERTYPANEL
            //in case of list selection change display properties of selected
            //symbol adapter
            PropertyPanel propertyPanel = new PropertyPanel((SymbolAdapter) defAnimationsList.getSelectedValue());

            AnimationPropertiesPanel.removeAll();
            AnimationPropertiesPanel.add(propertyPanel, java.awt.BorderLayout.CENTER);

            this.validate();

            //-- deleted this because preview displays all symboladapter, not only selected one;
            //-- is now handled in animationDialogClosed
            //PREVIEW DIALOG
            //get all Positioningtools of currently selected Symboladapter
//            ActionTool actionTool = ((SymbolAdapter) defAnimationsList.getSelectedValue()).getTool();
//
//            PreviewDialog.getInstance().setPositioningtoolsToPaint(actionTool.getElements());
//            PreviewDialog.getInstance().updatePreview();
        } else {

            //PROPERTYPANEL
            AnimationPropertiesPanel.removeAll();
            AnimationPropertiesPanel.add(new JLabel("No Animation selected"), java.awt.BorderLayout.CENTER);

            this.validate();

            //PREVIEW DIALOG
            //if no symboladapter is selected display just background
//            PreviewDialog.getInstance().setPositioningtoolsToPaint(null);
//            PreviewDialog.getInstance().updatePreview();
        }
    }//GEN-LAST:event_defAnimationsListValueChanged

    private void editAnimationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAnimationButtonActionPerformed

        SymbolAdapter editAdapter = ((SymbolAdapter) defAnimationsList.getSelectedValue());

        if (editAdapter != null) {

            AddAnimationDialog aaD = new AddAnimationDialog(this);

            //let user work on a copy of current adapter, only if editing is commited replace it with new one (see AddAnimatonDialogClosed)
            aaD.modifyExisting(editAdapter.createCopy());

            //set reference that can be read after dialog close
            this.modifying = editAdapter;

            aaD.setLocationRelativeTo(this);
            aaD.setVisible(true);
        }
    }//GEN-LAST:event_editAnimationButtonActionPerformed

    private void removeAnimationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAnimationButtonActionPerformed

        if (this.defAnimationsList.getSelectedValue() != null) {

            SymbolAdapter toDel = (SymbolAdapter) this.defAnimationsList.getSelectedValue();
            DisplayConfiguration.getInstance().getAdapter().remove(toDel);

            DefaultListModel model = (DefaultListModel) this.defAnimationsList.getModel();
            int delIndex = model.indexOf(toDel);

            //if deleted is not at first position select upper item
            if (delIndex > 0) {
                this.defAnimationsList.setSelectedIndex(delIndex - 1);
            } else {
                if (model.size() > 1) {
                    this.defAnimationsList.setSelectedIndex(1);
                }
            }
            model.removeElement(toDel);

            //update preview
            this.previewAllSymbolAdapters();
        }
    }//GEN-LAST:event_removeAnimationButtonActionPerformed

    private void priorityUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityUpButtonActionPerformed

        if (this.defAnimationsList.getSelectedValue() != null) {
            SymbolAdapter adapter = (SymbolAdapter) this.defAnimationsList.getSelectedValue();

            int position = this.defAnimationsList.getSelectedIndex();

            //only allow priority increase if not at first position
            if (position > 0) {

                //for both lists just swap current element with bordering element, so there is no problem with indizes changes

                //change in datalist
                List<SymbolAdapter> adapterList = DisplayConfiguration.getInstance().getAdapter();

                SymbolAdapter destination = adapterList.get(position - 1);

                //swap elements
                adapterList.set(position - 1, adapter);
                adapterList.set(position, destination);

                //change in JList
                DefaultListModel model = (DefaultListModel) this.defAnimationsList.getModel();

                model.set(position - 1, adapter);
                model.set(position, destination);

                //select originally selected value as selected
                this.defAnimationsList.setSelectedValue(adapter, true);
            }
        }
    }//GEN-LAST:event_priorityUpButtonActionPerformed

    private void priorityDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityDownButtonActionPerformed

        if (this.defAnimationsList.getSelectedValue() != null) {

            SymbolAdapter adapter = (SymbolAdapter) this.defAnimationsList.getSelectedValue();

            int position = this.defAnimationsList.getSelectedIndex();

            List<SymbolAdapter> adapterList = DisplayConfiguration.getInstance().getAdapter();

            //only allow priority decrease if not at last position
            if (position < adapterList.size() - 1) {

                //for both lists just swap current element with bordering element, so there is no problem with indizes changes

                //change in datalist
                SymbolAdapter destination = adapterList.get(position + 1);

                //swap elements
                adapterList.set(position + 1, adapter);
                adapterList.set(position, destination);

                //change in JList
                DefaultListModel model = (DefaultListModel) this.defAnimationsList.getModel();

                model.set(position + 1, adapter);
                model.set(position, destination);

                //select originally selected value as selected
                this.defAnimationsList.setSelectedValue(adapter, true);
            }
        }
    }//GEN-LAST:event_priorityDownButtonActionPerformed

    private void previewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewItemActionPerformed

        PreviewDialog.getInstance().setVisible(true);
    }//GEN-LAST:event_previewItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    //UIManager.setLookAndFeel(new com.jgoodies.plaf.plastic.Plastic3DLookAndFeel());
                } catch (Exception e) {
                }

                //on mac os enable menu bar at screen top
                if (System.getProperty("os.name").equals("Mac OS X")) {
                    System.setProperty("apple.laf.useScreenMenuBar", "true");
                }

                //display startup dialog
                StartUpDialog dialog = new StartUpDialog();
                dialog.setLocationRelativeTo(null);
                StartUpDialog.StartOption option = dialog.showStartUpDialog();

                if (option != null) {
                    if (option.equals(StartUpDialog.StartOption.NEW)) {
                        //no preconfigurations in here
                    } else if (option.equals(StartUpDialog.StartOption.EXISTING)) {
                        //let displayconfiguration load its settings
                        DisplayConfiguration.load(dialog.getConfigFile().getAbsolutePath());
                    }

                    DesignerGUI gui = new DesignerGUI();
                    gui.setLocationRelativeTo(null);
                    gui.setVisible(true);
                } else {
                    System.exit(0);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AnimationPropertiesPanel;
    private javax.swing.JPanel AnimationsPanel;
    private javax.swing.JMenuBar ApplicationMenu;
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JPanel DefAnimationsPanel;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JPanel SensorPanel;
    private javax.swing.JButton addAnimationButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JList defAnimationsList;
    private javax.swing.JButton editAnimationButton;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JMenuItem previewItem;
    private javax.swing.JButton priorityDownButton;
    private javax.swing.JButton priorityUpButton;
    private javax.swing.JButton removeAnimationButton;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
