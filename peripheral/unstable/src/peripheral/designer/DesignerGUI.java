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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import peripheral.designer.preview.PreviewDialog;
import peripheral.designer.property.PropertyPanel;
import peripheral.designer.wizard.AddAnimationDialog;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.UserInput;

/**
 *
 * @author Berni
 */
public class DesignerGUI extends javax.swing.JFrame {

    //remember current panel
    private int currentIndex = 0;
    private int lastIndex = 4;

    private PreviewDialog prevDialog;


    /** Creates new form DesignerGUI */
    public DesignerGUI() {
        initComponents();
        initPreviewBehaviour();

        //define model that allows to modify list data
        this.defAnimationsList.setModel(new DefaultListModel());

        createDummyData();
        
        //fill list with already defined animations
        fillAnimationList();
    }

    private void createDummyData() {
        SymbolAdapter slider1 = new SymbolAdapter();
        slider1.setName("RuleSlider1");
        slider1.getNeededUserInput().add(new UserInput("ui1","what the hell", new ConstValue("EnableSmoothing",new Boolean(true))));
        slider1.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue("LocationX",new Integer(0))));
        slider1.getNeededUserInput().add(new UserInput("ui2","what the hell", new ConstValue("LocationY",new Integer(0))));

        slider1.getRequiredSteps().put(AddAnimationDialog.RULEPANEL, new Boolean(true));

        DisplayConfiguration.getInstance().getAdapter().add(slider1);

        SymbolAdapter mover1 = new SymbolAdapter();
        mover1.setName("ContinousMover1");
        mover1.getRequiredSteps().put(AddAnimationDialog.RULEPANEL, new Boolean(false));
        DisplayConfiguration.getInstance().getAdapter().add(mover1);


    }

    private void initPreviewBehaviour() {

        //We will be interested in files only, but we need to allow it to choose both
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser1.addPropertyChangeListener(new PropertyChangeListener() {

            //To prevent reentry
            private boolean handlingEvent = false;

            public void propertyChange(PropertyChangeEvent e) {

                //Prevent reentry
                if (handlingEvent) {
                    return;
                } else //Mark it as handling the event
                {
                    handlingEvent = true;
                }

                String propertyName = e.getPropertyName();

                //We are interested in both event types
                if (propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) ||
                        propertyName.equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {

                    File selectedFile = (File) e.getNewValue();
                    if (selectedFile != null) {
                        if (selectedFile.isDirectory()) {
                            //Allow the user to navigate directories with single click
                            jFileChooser1.setCurrentDirectory(selectedFile);
                        } else {
                            jFileChooser1.setSelectedFile(selectedFile);
                            if (jFileChooser1.getSelectedFile() != null) //Accept it
                            {
                                jFileChooser1.approveSelection();

                                handleFilePreview(selectedFile);
                            }
                        }
                    }
                }

                //Allow new events to be processed now
                handlingEvent = false;
            }
        });
    }

    private void handleFilePreview (File selectedFile) {

        //active preview
        if (prevDialog == null) {
            prevDialog = new PreviewDialog(this,false);
            prevDialog.setLocation((int)(this.getBounds().getX()+this.getBounds().getWidth()+5), (int)(this.getBounds().getY()));
        }

        if (!prevDialog.isVisible()) {
            prevDialog.setVisible(true);
        }
        prevDialog.setBackgroundImage(selectedFile);
    }

    private void fillAnimationList() {

        for (SymbolAdapter symbolAdapter : DisplayConfiguration.getInstance().getAdapter()) {

            ((DefaultListModel)this.defAnimationsList.getModel()).addElement(symbolAdapter);
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

        buttonPanel = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        SensorPanel = new javax.swing.JPanel();
        BackgroundPanel = new javax.swing.JPanel();
        jFileChooser1 = new javax.swing.JFileChooser();
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
        SavePanel = new javax.swing.JPanel();
        ApplicationMenu = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        exitMenu = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addContainerGap(554, Short.MAX_VALUE)
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

        org.jdesktop.layout.GroupLayout SensorPanelLayout = new org.jdesktop.layout.GroupLayout(SensorPanel);
        SensorPanel.setLayout(SensorPanelLayout);
        SensorPanelLayout.setHorizontalGroup(
            SensorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 721, Short.MAX_VALUE)
        );
        SensorPanelLayout.setVerticalGroup(
            SensorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 435, Short.MAX_VALUE)
        );

        cardPanel.add(SensorPanel, "card2");

        BackgroundPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 2 : Select Background Image of the Scene"));

        jFileChooser1.setControlButtonsAreShown(false);

        org.jdesktop.layout.GroupLayout BackgroundPanelLayout = new org.jdesktop.layout.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(BackgroundPanelLayout.createSequentialGroup()
                .add(85, 85, 85)
                .add(jFileChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 541, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(BackgroundPanelLayout.createSequentialGroup()
                .add(63, 63, 63)
                .add(jFileChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 304, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
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
                .addContainerGap(8, Short.MAX_VALUE))
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

        editAnimationButton.setText("...");
        editAnimationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAnimationButtonActionPerformed(evt);
            }
        });

        priorityUpButton.setText("UP");

        priorityDownButton.setText("DOWN");

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
                .addContainerGap(29, Short.MAX_VALUE))
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
                            .add(org.jdesktop.layout.GroupLayout.LEADING, AnimationPropertiesPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, DefAnimationsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 383, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel1))
        );

        cardPanel.add(AnimationsPanel, "card4");

        SavePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 4 : Save this Configuration"));

        org.jdesktop.layout.GroupLayout SavePanelLayout = new org.jdesktop.layout.GroupLayout(SavePanel);
        SavePanel.setLayout(SavePanelLayout);
        SavePanelLayout.setHorizontalGroup(
            SavePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 721, Short.MAX_VALUE)
        );
        SavePanelLayout.setVerticalGroup(
            SavePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 435, Short.MAX_VALUE)
        );

        cardPanel.add(SavePanel, "card5");

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

        EditMenu.setText("Edit");
        ApplicationMenu.add(EditMenu);

        setJMenuBar(ApplicationMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAnimationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAnimationButtonActionPerformed

        AddAnimationDialog aaD = new AddAnimationDialog(this);
        aaD.setVisible(true);

        if (aaD.completedCreation()) {
            DisplayConfiguration.getInstance().getAdapter().add(aaD.getCreatedAdapter());
            ((DefaultListModel)this.defAnimationsList.getModel()).addElement(aaD.getCreatedAdapter());
        }
    }//GEN-LAST:event_addAnimationButtonActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed

        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_exitMenuActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        CardLayout cl = ((java.awt.CardLayout)cardPanel.getLayout());

        //Sensorpanel
        if (currentIndex == 0) {
            prevButton.setEnabled(true);

            currentIndex++;
            cl.next(cardPanel);
        }
        //background panel
        else if (currentIndex == 1) {

            //Save selected image in display configuration
            File selectedFile = this.jFileChooser1.getSelectedFile();

            if (selectedFile != null && PreviewDialog.isExtentionSupported(selectedFile)) {

                DisplayConfiguration.getInstance().setBackgroundImage(this.prevDialog.getBackgroundImage());

                currentIndex++;
                cl.next(cardPanel);
            }
            else {

//                JOptionPane.showMessageDialog(this, "Please select a supported image file\n" +
//                        "Supported are jpg, bmp, gif, png files", "Unable to proceed", JOptionPane.ERROR_MESSAGE);
                currentIndex++;
                cl.next(cardPanel);
            }
            
        }
        //animation panel
        else if (currentIndex == 2) {

            nextButton.setText("Finish");
            currentIndex++;
            cl.next(cardPanel);
        }
        //save panel
        else if (currentIndex == 3) {

            this.setVisible(false);
            this.dispose();
        }
        
    }//GEN-LAST:event_nextButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        CardLayout cl = ((java.awt.CardLayout)cardPanel.getLayout());

        //Sensorpanel
        if (currentIndex == 0) {
            return;
        }
        //background panel
        else if (currentIndex == 1) {
            prevButton.setEnabled(false);
        }
        //animation panel
        else if (currentIndex == 2) {

        }
        //save panel
        else if (currentIndex == 3) {
            nextButton.setText("next");
        }

        currentIndex--;
        cl.previous(cardPanel);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void defAnimationsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_defAnimationsListValueChanged

        if (defAnimationsList.getSelectedValue() != null) {
            //in case of list selection change display properties of selected
            //symbol adapter
            List<UserInput> userInputs = ((SymbolAdapter)defAnimationsList.getSelectedValue()).getNeededUserInput();

            PropertyPanel propertyPanel = new PropertyPanel(userInputs);

            AnimationPropertiesPanel.removeAll();
            AnimationPropertiesPanel.add(propertyPanel, java.awt.BorderLayout.CENTER);

            this.validate();
        }
        else {
            AnimationPropertiesPanel.removeAll();
            AnimationPropertiesPanel.add(new JLabel("No Animation selected"), java.awt.BorderLayout.CENTER);

            this.validate();
        }
    }//GEN-LAST:event_defAnimationsListValueChanged

    private void editAnimationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAnimationButtonActionPerformed

        SymbolAdapter editAdapter = ((SymbolAdapter)defAnimationsList.getSelectedValue());

        if (editAdapter != null) {

            AddAnimationDialog aaD = new AddAnimationDialog(this);
            aaD.modifyExisting(editAdapter);
            aaD.setVisible(true);

            //in case editing canceled remove item from list (to support editing cancel symboladpater must be cloneable)
            if (!aaD.completedCreation()) {
                
                DisplayConfiguration.getInstance().getAdapter().remove(aaD.getCreatedAdapter());
                ((DefaultListModel)this.defAnimationsList.getModel()).removeElement(aaD.getCreatedAdapter());
                
            }
        }
    }//GEN-LAST:event_editAnimationButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DesignerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AnimationPropertiesPanel;
    private javax.swing.JPanel AnimationsPanel;
    private javax.swing.JMenuBar ApplicationMenu;
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JPanel DefAnimationsPanel;
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JPanel SavePanel;
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
    private javax.swing.JButton priorityDownButton;
    private javax.swing.JButton priorityUpButton;
    private javax.swing.JButton removeAnimationButton;
    // End of variables declaration//GEN-END:variables

}