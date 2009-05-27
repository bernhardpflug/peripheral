/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PreviewDialog.java
 *
 * Created on 14.05.2009, 20:15:54
 */

package peripheral.designer.preview;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import peripheral.logic.positioningtool.PositioningTool;

/**
 *
 * @author Berni
 */
public class PreviewDialog extends javax.swing.JDialog implements ChangeListener{

    private static final String [] supportedFileEndings = {"jpg","jpeg","gif","png","bmp","tif","tiff"};

    private static PreviewDialog instance;

    private ArrayList<ChangeListener> previewListeners;

    public static PreviewDialog getInstance() {

        if (instance == null) {
            instance = new PreviewDialog();
        }

        return instance;
    }
    /** Creates new form PreviewDialog */
    public PreviewDialog() {
        super();

        this.setModal(false);

        previewPanel1 = new PreviewPanel(this);
        getContentPane().add(previewPanel1,java.awt.BorderLayout.CENTER);
        previewPanel1.setPreferredSize(new Dimension(400,300));
        initComponents();

        previewListeners = new ArrayList();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        southPanel = new javax.swing.JPanel();
        xLabel = new javax.swing.JLabel();
        xValueLabel = new javax.swing.JLabel();
        yLabel = new javax.swing.JLabel();
        yValueLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        xLabel.setText("X:");
        southPanel.add(xLabel);

        xValueLabel.setText("-");
        southPanel.add(xValueLabel);

        yLabel.setText("Y:");
        southPanel.add(yLabel);

        yValueLabel.setText("-");
        southPanel.add(yValueLabel);

        getContentPane().add(southPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setBackgroundImage(File sourceFile) {
        previewPanel1.setBackgroundImage(sourceFile);
    }

    public java.awt.image.BufferedImage getBackgroundImage() {
        return previewPanel1.getBackgroundImage();
    }

    public static boolean isExtentionSupported(File sourceFile) {

        for (String support : supportedFileEndings) {
            if (sourceFile.getName().toLowerCase().endsWith(support)) {
                return true;
            }
        }

        return false;
    }

    public void setPositioningtoolsToPaint(List<PositioningTool> symbols) {
        previewPanel1.setPositioningtoolsToPaint(symbols);
    }

    public List getPositioningtoolsToPaint() {
        return previewPanel1.getPositioningtoolsToPaint();
    }

    public void setMouseCoords(String x, String y) {
        xValueLabel.setText(x);
        yValueLabel.setText(y);
    }

    public void updatePreview() {
        previewPanel1.repaint();
        this.repaint();
    }

    /*
     *  CHANGE LISTENER METHODS
     */

    public void addPreviewListener(ChangeListener l) {
        System.out.println("adding "+l);
        this.previewListeners.add(l);
    }

    public void removePreviewListener(ChangeListener l) {
        System.out.println("removing "+l+" with state "+this.previewListeners.remove(l));
        
    }

    /*
     * EVENT METHODS
     * */

    public void dragStart(Point origin) {
        for (ChangeListener l : previewListeners) {
            l.dragStart(origin);
        }
    }

    public void dragAction(Point newPosition) {
        for (ChangeListener l : previewListeners) {
            l.dragAction(newPosition);
        }
    }

    public void dragStop() {
        for (ChangeListener l : previewListeners) {
            l.dragStop();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel southPanel;
    private javax.swing.JLabel xLabel;
    private javax.swing.JLabel xValueLabel;
    private javax.swing.JLabel yLabel;
    private javax.swing.JLabel yValueLabel;
    // End of variables declaration//GEN-END:variables

    private PreviewPanel previewPanel1;
}
