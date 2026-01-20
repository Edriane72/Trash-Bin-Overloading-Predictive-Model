package java_gui.controller;

import java.util.List;

import java_gui.view.ControlPanel;
import java_gui.view.RankingTable;
import java_gui.model.BinResult;

public class MainController {

    ControlPanel control;
    RankingTable table;

    public MainController(ControlPanel control, RankingTable table) {
        this.control = control;
        this.table = table;

        control.runBTN.addActionListener(e -> runSimulation());
    }

    void runSimulation() {
        try {
            PythonRunner.runSimulation();
            table.clear();

            List<BinResult> results = PythonRunner.loadResults();

            for (BinResult r : results) {
                table.addRow(new Object[]{
                        r.binId,
                        r.rank,
                        r.probability
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
