package java_gui.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Predictive Bin Overflow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use proper layout BEFORE adding components
        setLayout(new BorderLayout(10, 10));

        // Panels
        ControlPanel control = new ControlPanel();
        RankingTable table = new RankingTable();

        // Force control panel to have visible height
        control.setPreferredSize(new Dimension(800, 120));

        // Add components
        add(control, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // Wire controller
        new java_gui.controller.MainController(control, table);

        // Let Swing compute correct size
        pack();

        // Then enforce a minimum size so it never collapses
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
