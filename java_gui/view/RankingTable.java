package java_gui.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RankingTable extends JTable {

    DefaultTableModel model;

    public RankingTable() {
        model = new DefaultTableModel();
        model.addColumn("Bin ID");
        model.addColumn("Rank");
        model.addColumn("Probability");

        setModel(model);
    }

    public void addRow(Object[] row) {
        model.addRow(row);
    }

    public void clear() {
        model.setRowCount(0);
    }
}
