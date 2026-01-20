package java_gui.view;

import javax.swing.*;

public class ControlPanel extends JPanel {

    public JComboBox<String> scenarioBox;
    public JButton runBTN, hourBTN, clearBTN, collectBTN;

    public ControlPanel() {

        setLayout(null);

        JLabel label = new JLabel("Scenario:");
        label.setBounds(50, 20, 100, 25);
        add(label);

        scenarioBox = new JComboBox<>(new String[]{
            "normal", "sunny", "rain", "night", "morning"
        });
        scenarioBox.setBounds(130, 20, 150, 25);
        add(scenarioBox);

        runBTN = new JButton("Run Simulation");
        runBTN.setBounds(50, 60, 150, 30);
        add(runBTN);

        hourBTN = new JButton("+1 Hour");
        hourBTN.setBounds(210, 60, 120, 30);
        add(hourBTN);

        clearBTN = new JButton("Clear All");
        clearBTN.setBounds(340, 60, 120, 30);
        add(clearBTN);

        collectBTN = new JButton("Collect Full");
        collectBTN.setBounds(470, 60, 140, 30);
        add(collectBTN);
    }

    public String getScenario() {
        return scenarioBox.getSelectedItem().toString();
    }
}

