/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * rulesPanel.java
 *
 * Created on 14.05.2009, 20:04:57
 */

package peripheral.designer.wizard;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import peripheral.logic.rule.Rule;
import peripheral.logic.symboladapter.SymbolAdapter;

/**
 *
 * @author Berni
 */
public class RulesRootPanel extends javax.swing.JPanel {


    private Window parent;

    public GridBagLayout layout;

    private SymbolAdapter symbolAdapter;
    private JPanel buttonPanel;

    private ArrayList<RulePanel> rulePanels;

    /** Creates new form rulesPanel */
    public RulesRootPanel(Window parent) {
        initComponents();


        this.parent = parent;

        layout = new GridBagLayout();

        this.setLayout(layout);

        buttonPanel = createButtonPanel();

        rulePanels = new ArrayList<RulePanel>();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 597, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 380, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void setSymbolAdapter(SymbolAdapter symbolAdapter) {

        this.symbolAdapter = symbolAdapter;
        
        rulePanels.clear();
        
        if (symbolAdapter.getRules().size() == 0) {
            addNewRule();
        }
        
        createConditionPanels();
        
        parent.validate();
    }

    private void createConditionPanels() {

        for (Rule rule : symbolAdapter.getRules()) {

            RulePanel rulePanel = new RulePanel(rule, parent);
            rulePanels.add(rulePanel);
            
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);

        JButton addRuleButton = new JButton("Add Rule");
        addRuleButton.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
                addNewRule();
                
            }
        });

        buttonPanel.setSize(100,20);
        addRuleButton.setSize(100, 20);
        buttonPanel.add(addRuleButton);

        return buttonPanel;
    }
    /**
     * called every time a rulePanel is repainted
     */
    public void windowChanged() {

        this.removeAll();

        this.createLayout();

        this.validate();

        parent.validate();
    }

    private void createLayout() {

        int i=0;

        GridBagConstraints gbc;

        for (RulePanel rulePanel : rulePanels) {

            Dimension panelMinDim = rulePanel.getMinimumDisplaySize();

            gbc = makegbc(0,i*3+1,1,3,GridBagConstraints.BOTH,(int)panelMinDim.getWidth(),(int)panelMinDim.getHeight());

            layout.setConstraints(rulePanel, gbc);

            this.add(rulePanel);

            i++;
        }

        //add button panel at least
        gbc = makegbc(0,i*3+1,1,1,GridBagConstraints.NONE,(int)buttonPanel.getWidth(),(int)buttonPanel.getHeight());
        layout.setConstraints(buttonPanel, gbc);
        this.add(buttonPanel);

    }

    private void addNewRule() {

        Rule newRule = new Rule();

        symbolAdapter.getRules().add(newRule);

        RulePanel rulePanel = new RulePanel(newRule, parent);
        rulePanels.add(rulePanel);

        this.windowChanged();
    }

    public void removeRulePanel(RulePanel rulePanel) {

        rulePanels.remove(rulePanel);
        symbolAdapter.getRules().remove(rulePanel.getRule());

        windowChanged();
    }

    /**
     * creates the settings for a component which should be displayed in GridBagLayout
     * @param x startcoordinate of component
     * @param y startcoordinate of component
     * @param width how many cells
     * @param height how many cells
     * @return
     */
    private GridBagConstraints makegbc (int x, int y, int width, int height,int fill, int ipadx, int ipady) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = fill;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        //Defines the border of each element
        gbc.insets = new Insets(10, 10, 10, 10);

        return gbc;
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


        //Create and set up the content pane.
        RulesRootPanel newContentPane = new RulesRootPanel(frame);
        newContentPane.setSymbolAdapter(new SymbolAdapter());
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(new JScrollPane(newContentPane));

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
