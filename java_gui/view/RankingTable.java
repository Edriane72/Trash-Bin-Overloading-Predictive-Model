package java_gui.view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class RankingTable extends JTable {

    DefaultTableModel model;

    public RankingTable() {

        model = new DefaultTableModel(
            new Object[]{"Bin ID", "Rank", "Probability", "Fill %"}, 0
        );

        setModel(model);
        setRowHeight(25);

        getColumnModel().getColumn(3).setCellRenderer(new FillRenderer());
    }

    public void clear() {
        model.setRowCount(0);
    }

    public void addRow(int id, int rank, double prob, double fill) {
        model.addRow(new Object[]{
            id,
            rank,
            String.format("%.2f", prob),
            String.format("%.1f%%", fill)
        });
    }

    // ---------- Color full / warning bins ----------
    class FillRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            double fill = Double.parseDouble(value.toString().replace("%", ""));

            if (fill >= 100) {
                c.setBackground(Color.RED);
            } else if (fill >= 80) {
                c.setBackground(Color.ORANGE);
            } else {
                c.setBackground(Color.WHITE);
            }

            return c;
        }
    }
}
