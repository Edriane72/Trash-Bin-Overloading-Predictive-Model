package java_gui.controller;

import java.io.*;
import java.util.*;
import java_gui.model.BinResult;

public class PythonRunner {

    // ================= RUN SIMULATION =================
    public static void runSimulation(String scenario) throws Exception {
        runSimulation(scenario, "run");
    }

    public static void runSimulation(String scenario, String mode) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                "python",
                "python_backend/main.py",
                scenario,
                mode
        );

        pb.redirectErrorStream(true);
        Process p = pb.start();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream())
        );

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("[PYTHON] " + line);
        }

        p.waitFor();
    }

    // ================= LOAD RESULTS =================
    public static List<BinResult> loadResults() throws Exception {

        // 🔥 CORRECT PATH — THIS MATCHES YOUR PYTHON OUTPUT
        File file = new File("output/results.csv");

        if (!file.exists()) {
            throw new FileNotFoundException("RESULTS FILE NOT FOUND: " + file.getAbsolutePath());
        }

        List<BinResult> results = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            int binId = Integer.parseInt(parts[0]);
            int rank = Integer.parseInt(parts[1]);
            double probability = Double.parseDouble(parts[2]);
            double fill = Double.parseDouble(parts[3]);

            results.add(new BinResult(binId, rank, probability, fill));
        }

        br.close();
        return results;
    }
}
