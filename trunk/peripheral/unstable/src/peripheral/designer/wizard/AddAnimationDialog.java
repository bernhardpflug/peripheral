/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * addAnimationDialog.java
 *
 * Created on 14.05.2009, 14:53:34
 */

package peripheral.designer.wizard;

import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import peripheral.designer.DesignerGUI;
import peripheral.designer.preview.PreviewDialog;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.symboladapter.AdapterTemplateFactory;
import peripheral.logic.symboladapter.SymbolAdapter;

/**
 *
 * @author Berni
 */
public class AddAnimationDialog extends javax.swing.JDialog {

    //refrence to created symbol adapter
    SymbolAdapter symbolAdapter;

    //flag that defines whether user completed creation
    private boolean completedFlag = false;

    //flag that defines whether this dialog should edit existing or create new adapter
    private boolean modifyFlag = false;

    //defines whether selected symboladapter allows setting rules
    private boolean ruleBasedAdapterFlag = false;

    //remember current panel
    private int currentIndex = 0;

    /** Creates new form addAnimationDialog */
    public AddAnimationDialog(DesignerGUI parent) {
        super(parent, false);
        initComponents();

        //add rule panel by hand as not supported by editor
        rulesRootPanel1 = new RulesRootPanel(this);
        rulesRootPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 4 : Define Rules for Animationbehaviour"));
        
        cardPanel.add(new javax.swing.JScrollPane(rulesRootPanel1),"card5");

        //set prevbutton initially disabled
        prevButton.setEnabled(false);

        //add window listener to inform frame if this dialog is closed
        //to be able to save new symboladapter
        this.addWindowListener(new MyWindowListener(parent,this));

    }

    public boolean completedCreation() {
        return completedFlag;
    }

    public boolean modifiedExisting() {
        return modifyFlag;
    }

    public SymbolAdapter getCreatedAdapter() {
        return symbolAdapter;
    }

    /**
     * In case already existing adapter should be modified
     * this method should be called directly after instancing this dialog
     * (before setVisible)
     * this causes disappearance of first panel and fill in of already defined
     * properties
     * @param copyToEditsymbolAdapter to edit
     */
    public void modifyExisting(SymbolAdapter copyToEdit) {

        //leave first panel out
        CardLayout cl = ((java.awt.CardLayout)cardPanel.getLayout());
        currentIndex++;
        cl.next(cardPanel);

        if (copyToEdit.getRequiredSteps().get(SymbolAdapter.RequiredStep.Rules).booleanValue()) {
            this.ruleBasedAdapterFlag = true;
        }

        modifyFlag = true;

        this.symbolAdapter = copyToEdit;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();
        selectAnimationPanel1 = new peripheral.designer.wizard.SelectAnimationPanel();
        preselectSensorPanel1 = new peripheral.designer.wizard.PreselectSensorPanel();
        createLocationsSymbolsPanel1 = new peripheral.designer.wizard.LocationsSymbolsPanel(this);
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cardPanel.setLayout(new java.awt.CardLayout());

        selectAnimationPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 1 : Select an Animation Template"));
        cardPanel.add(selectAnimationPanel1, "card4");

        preselectSensorPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 2 : Select Sensors"));
        cardPanel.add(preselectSensorPanel1, "card2");

        createLocationsSymbolsPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Step 3 : Create Point(s), Area(s) and their symbols"));
        cardPanel.add(createLocationsSymbolsPanel1, "card4");

        prevButton.setText("<");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        nextButton.setText(">");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(518, Short.MAX_VALUE)
                .add(prevButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(nextButton)
                .add(28, 28, 28))
            .add(cardPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(cardPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(prevButton)
                    .add(nextButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        CardLayout cl = ((java.awt.CardLayout)cardPanel.getLayout());

        //select symbol adapter
        if (currentIndex == 0) {
            prevButton.setEnabled(true);

            //only allow proceeding if an adapter has been selected
            SymbolAdapter selectedTemplate = this.selectAnimationPanel1.getSelectedAdapter();

            if (selectedTemplate != null) {

                //create instance for selected template to work on
                this.symbolAdapter = AdapterTemplateFactory.getInstance().createInstanceFor(selectedTemplate);

                if (symbolAdapter.getRequiredSteps().get(SymbolAdapter.RequiredStep.Rules).booleanValue()) {
                    this.ruleBasedAdapterFlag = true;
                }

                currentIndex++;
                cl.next(cardPanel);
            }
            else {

                JOptionPane.showMessageDialog(this, "Select an Animation in order to proceed","No Animation selected", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
        //preselect sensor
        else if (currentIndex == 1) {

            if (!this.ruleBasedAdapterFlag) {
                nextButton.setText("Finish");
            }

            //set action tool for location panel after selection of a symboladpater
            this.createLocationsSymbolsPanel1.setActionTool(symbolAdapter.getTool());

            //display preview dialog for position panel
            if (!PreviewDialog.getInstance().isVisible()) {
                PreviewDialog.getInstance().setVisible(true);
            }

            //register positioning panel to preview dialog for listening to drag events
            PreviewDialog.getInstance().addPreviewListener(createLocationsSymbolsPanel1);

            currentIndex++;
            cl.next(cardPanel);
        }
        //create points/areas
        else if (currentIndex == 2) {

            //unregister changelistener from preview dialog
            PreviewDialog.getInstance().removePreviewListener(createLocationsSymbolsPanel1);

            if (!this.ruleBasedAdapterFlag) {
                closeAnimationDialog();
            }
            else {
                nextButton.setText("Finish");

                //set symboladapter for rulespanel
                this.rulesRootPanel1.setSymbolAdapter(symbolAdapter);

                currentIndex++;
                cl.next(cardPanel);
            }
        }
        //rules panel
        else if (currentIndex == 3) {
            closeAnimationDialog();
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed

        CardLayout cl = ((java.awt.CardLayout)cardPanel.getLayout());

        //animation panel
        if (currentIndex == 0) {
            return;
        }
        //preselect sensor panel
        else if (currentIndex == 1) {

            if (this.modifyFlag) {
                return;
            }
            else {
                prevButton.setEnabled(false);
                currentIndex--;
                cl.previous(cardPanel);
            }
        }
        //points /areas panel
        else if (currentIndex == 2) {

            if (!this.ruleBasedAdapterFlag) {
                nextButton.setText(">");
            }

            //unregister changelistener from preview dialog
            PreviewDialog.getInstance().removePreviewListener(createLocationsSymbolsPanel1);

            currentIndex--;
            cl.previous(cardPanel);
        }
        //rulepanel
        else if (currentIndex == 3) {

            //register positioning panel to preview dialog for listening to drag events
            PreviewDialog.getInstance().addPreviewListener(createLocationsSymbolsPanel1);

            nextButton.setText(">");

            currentIndex--;
            cl.previous(cardPanel);
        }
    }//GEN-LAST:event_prevButtonActionPerformed

    private void closeAnimationDialog() {

        //indicate designer that symboladapter creation was successfull
        completedFlag = true;

        this.setVisible(false);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardPanel;
    private peripheral.designer.wizard.LocationsSymbolsPanel createLocationsSymbolsPanel1;
    private javax.swing.JButton nextButton;
    private peripheral.designer.wizard.PreselectSensorPanel preselectSensorPanel1;
    private javax.swing.JButton prevButton;
    private peripheral.designer.wizard.SelectAnimationPanel selectAnimationPanel1;
    // End of variables declaration//GEN-END:variables

    private peripheral.designer.wizard.RulesRootPanel rulesRootPanel1;

    class MyWindowListener implements WindowListener {

        DesignerGUI parent;
        AddAnimationDialog dialog;

        public MyWindowListener(DesignerGUI parent, AddAnimationDialog dialog) {
            this.parent = parent;
            this.dialog = dialog;
        }
        public void windowOpened(WindowEvent e) {
        }

        public void windowClosing(WindowEvent e) {
        }

        public void windowClosed(WindowEvent e) {

            //when closing reset positioning tools
            PreviewDialog.getInstance().setPositioningtoolsToPaint(new java.util.ArrayList<PositioningTool>(1));
            PreviewDialog.getInstance().updatePreview();

            //indicate designer that dialog closed
            parent.AddAnimationDialogClosed(dialog);
        }

        public void windowIconified(WindowEvent e) {
        }

        public void windowDeiconified(WindowEvent e) {
        }

        public void windowActivated(WindowEvent e) {
        }

        public void windowDeactivated(WindowEvent e) {
        }

    }
}
