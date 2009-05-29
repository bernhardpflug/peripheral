/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.rule;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.SensorValue;

/**
 *
 * @author Andi
 */
public class ConditionTest {

    public SensorChannel channel1,  channel2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();

        test.createDummySensors();

        Condition cond = new Condition();
        SensorValue val = new SensorValue(new SymbolAdapter(), "testVal", test.channel1, String.class);
        cond.setLeftSideOp(val);
        for (ConditionOperation ops : cond.getAvailableOperations()) {
            System.out.println(ops.toString());
        }
        JFrame dlg = new JFrame();
        dlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dlg.setSize(300, 300);
        final ConditionOperation co = cond.getAvailableOperations().get(6);
        Component comp = co.getRightSideComponent();
        //comp.setLocation(10, 10);
        comp.setPreferredSize(new Dimension(300, 30));
        dlg.getContentPane().setLayout(new FlowLayout());
        dlg.getContentPane().add(comp);
        JButton btn = new JButton();
        btn.setText("Save values");
        btn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        co.saveValuesFromRightSideComponent();
                    }
                });
        dlg.getContentPane().add(btn);

        dlg.pack();
        dlg.setVisible(true);
    }

    private void createDummySensors() {
        // Create SensorChannel channel1
        channel1 = new SensorChannel(34, "admin:Dummy Sensor:Dummy Stim:dummychannel1", null);

        // Create SensorChannel channel1
        channel2 = new SensorChannel(32, "admin:Dummy Sensor:Dummy Stim:dummychannel2", null);

        // Create Metadata for channel1
        TreeMap<String, String> metadata1 = channel1.getMetadata();
        metadata1.put("shortname", "AirTemp");
        metadata1.put("description", "Air Temperature");
        metadata1.put("datatype", "Integer8");
        metadata1.put("units", "Degree Celcius");
        metadata1.put("location", "In your face bitch");
//		metadata1.put("upperlimit", "");
//		metadata1.put("lowerlimit", "");

        // Create Metadata for channel2
        TreeMap<String, String> metadata2 = channel2.getMetadata();
        metadata2.put("shortname", "SkyCover");
        metadata2.put("description", "Percentage of Sky Cover");
        metadata2.put("datatype", "String");
        metadata2.put("units", "Percent");
        metadata2.put("location", "In your face bitch");
        metadata2.put("upperlimit", "100");
        metadata2.put("lowerlimit", "0");
    }
}
