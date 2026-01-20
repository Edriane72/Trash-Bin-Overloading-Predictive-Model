package java_gui.view;

import javax.swing.*;
import java_gui.controller.MainController;

public class MainFrame extends JFrame {

    ControlPanel control;
    RankingTable table;

    public MainFrame() {
        setTitle("Predictive Bin Overflow");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        addLabel("Predictive Bin Overflow Ranking", 140, 20);

        control = new ControlPanel();
        control.setBounds(0, 50, 500, 60);
        add(control);

        table = new RankingTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 380, 220);
        add(scrollPane);

        new MainController(control, table);

        setVisible(true);
    }

    void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 300, 20);
        add(lbl);
    }
}

