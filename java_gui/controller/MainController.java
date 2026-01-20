package java_gui.controller;

import java.util.List;
import javax.swing.SwingUtilities;
import java_gui.view.ControlPanel;
import java_gui.view.RankingTable;
import java_gui.model.BinResult;

public class MainController {

    ControlPanel control;
    RankingTable table;

    public MainController(ControlPanel control, RankingTable table) {
        this.control = control;
        this.table = table;

        control.runBTN.addActionListener(e -> runAsync(() -> simulate()));
        control.hourBTN.addActionListener(e -> runAsync(() -> advanceHour()));
        control.clearBTN.addActionListener(e -> runAsync(() -> clearAll()));
        control.collectBTN.addActionListener(e -> runAsync(() -> collectFull()));
    }
    
    private void runAsync(Runnable task) {
        new Thread(() -> {
            try {
                task.run();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    void simulate() {
        try {
            String scenario = control.getScenario();
            PythonRunner.runSimulation(scenario);
            refreshTable();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void advanceHour() {
        try {
            String scenario = control.getScenario();
            PythonRunner.runSimulation(scenario, "hour");
            PythonRunner.runSimulation(scenario);
            refreshTable();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    // collects every bin (changes after clicking next hour)
    void clearAll() {
        try {
            String scenario = control.getScenario(); 

            // FIX: Changed "normal" to scenario variable
            PythonRunner.runSimulation(scenario, "clear");

            // Recompute ranking for the actual scenario
            PythonRunner.runSimulation(scenario);

            refreshTable();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    // collect full bins (changes after clicking next hour)
    void collectFull() {
        try {
            String scenario = control.getScenario();

            PythonRunner.runSimulation(scenario, "collect");

            PythonRunner.runSimulation(scenario);

            refreshTable();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void refreshTable() throws Exception {
        List<BinResult> results = PythonRunner.loadResults();

        SwingUtilities.invokeLater(() -> {
            table.clear();
            for (BinResult r : results) {
                table.addRow(r.binId, r.rank, r.probability, r.fill);
            }
        });
    }
}