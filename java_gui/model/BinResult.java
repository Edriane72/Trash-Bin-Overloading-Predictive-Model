package java_gui.model;

public class BinResult {

    public int binId;
    public int rank;
    public double probability;
    public double fill;

    public BinResult(int binId, int rank, double probability, double fill) {
        this.binId = binId;
        this.rank = rank;
        this.probability = probability;
        this.fill = fill;
    }
}
