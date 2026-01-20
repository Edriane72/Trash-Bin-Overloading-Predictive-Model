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

        control.runBTN.addActionListener(e -> simulate());
        control.hourBTN.addActionListener(e -> advanceHour());
        control.clearBTN.addActionListener(e -> clearAll());
        control.collectBTN.addActionListener(e -> collectFull());
    }

    // ================= RUN SIMULATION =================
    void simulate() {
        try {
            String scenario = control.getScenario();

            PythonRunner.runSimulation(scenario);
            refreshTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================= ADVANCE 1 HOUR =================
    void advanceHour() {
        try {
            String scenario = control.getScenario();

            PythonRunner.runSimulation(scenario, "hour");
            refreshTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================= CLEAR ALL =================
    void clearAll() {
        try {
            PythonRunner.runSimulation("normal", "clear");
            refreshTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================= COLLECT FULL =================
    void collectFull() {
        try {
            PythonRunner.runSimulation("normal", "collect");
            refreshTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================= REFRESH TABLE =================
    void refreshTable() throws Exception {

        List<BinResult> results = PythonRunner.loadResults();

        table.clear();

        for (BinResult r : results) {
            table.addRow(
                    r.binId,
                    r.rank,
                    r.probability,
                    r.fill
            );
        }
    }
}
