package java_gui.controller;

import java.io.*;
import java.util.*;
import java_gui.model.BinResult;

public class PythonRunner {

    public static void runSimulation(String scenario) throws Exception {
        runSimulation(scenario, null);
    }

    public static void runSimulation(String scenario, String mode) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add("python_backend/main.py");
        command.add(scenario);
        if (mode != null) {
            command.add(mode);
        }

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process p = pb.start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("[PYTHON] " + line);
            }
        }

        int exitCode = p.waitFor();
        if (exitCode != 0) {
            System.err.println("Python script exited with error code: " + exitCode);
        }
    }

    public static List<BinResult> loadResults() throws Exception {
        List<BinResult> results = new ArrayList<>();
        File file = new File("output/results.csv");

        if (!file.exists()) {
            throw new FileNotFoundException("RESULTS FILE NOT FOUND: " + file.getAbsolutePath());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                
                results.add(new BinResult(
                    Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim()),
                    Double.parseDouble(parts[2].trim()),
                    Double.parseDouble(parts[3].trim())
                ));
            }
        }
        return results;
    }
}