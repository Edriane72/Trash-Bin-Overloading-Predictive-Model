package java_gui.controller;

import java.io.*;
import java.util.*;

import java_gui.model.BinResult;

public class PythonRunner {

    public static void runSimulation() throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                "python",
                "../python_backend/main.py"
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor();
    }

    public static List<BinResult> loadResults() throws Exception {

        List<BinResult> list = new ArrayList<>();

        BufferedReader br = new BufferedReader(
                new FileReader("../python_backend/output/results.csv")
        );

        String line = br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            int binId = Integer.parseInt(parts[0]);
            int rank = Integer.parseInt(parts[1]);
            double prob = Double.parseDouble(parts[2]);

            list.add(new BinResult(binId, rank, prob));
        }

        br.close();
        return list;
    }
}
