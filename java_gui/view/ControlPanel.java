package java_gui.view;

import javax.swing.*;

public class ControlPanel extends JPanel {

    public JButton runBTN;

    public ControlPanel() {
        setLayout(null);

        runBTN = new JButton("Run Simulation");
        runBTN.setBounds(170, 10, 150, 40);
        add(runBTN);
    }
}
