import random
import csv

from model.bin import TrashBin
from model.monitor import Monitor
from processing.ranking import compute_ranking

def run_simulation():

    # Create some bins (3 bins for demo)
    bins = [
        TrashBin(1, 120),
        TrashBin(2, 120),
        TrashBin(3, 120)
    ]

    monitor = Monitor()
    time = 0

    # Simple simulation loop
    while True:
        time += random.randint(1, 5)

        chosen_bin = random.choice(bins)
        volume = random.randint(10, 40)

        overflow = chosen_bin.add_waste(volume)

        if overflow:
            monitor.record(time, chosen_bin.bin_id)

        # Stop once all bins have overflowed at least once
        if len(monitor.events) >= len(bins):
            break

    # Compute ranking
    results = compute_ranking(monitor.events)

    # Save results for Java
    with open("python_backend/output/results.csv", "w", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["BinID", "Rank", "Probability"])

        for r in results:
            writer.writerow([r["bin"], r["rank"], r["probability"]])

    print("Simulation finished.")
    print("Results saved to python_backend/output/results.csv")

if __name__ == "__main__":
    run_simulation()
