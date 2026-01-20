package java_gui.model;

public class BinResult {

    public int binId;
    public int rank;
    public double probability;

    public BinResult(int binId, int rank, double probability) {
        this.binId = binId;
        this.rank = rank;
        this.probability = probability;
    }
}