import csv
import sys
import os
import random
from processing.ranking import compute_ranking

RUNS = 200
CAPACITY = 120

BIN_BASE_FLOW = {
    1: 1.3, 2: 1.2, 3: 1.4, 4: 1.1,
    5: 0.9, 6: 0.8, 7: 0.7, 8: 1.0, 9: 0.85
}

OPEN_BINS = {1, 2, 3, 4, 8}
COVERED_BINS = {5, 6, 7, 9}
FOOD_BINS = {3, 6, 9}


def scenario_weight(bin_id, scenario):
    w = 1.0
    if scenario == "sunny":
        w *= 1.6 if bin_id in OPEN_BINS else 0.9
    elif scenario == "rain":
        w *= 0.4 if bin_id in OPEN_BINS else 1.7
    elif scenario == "night":
        w *= 0.3 if bin_id in FOOD_BINS else 0.6
    elif scenario == "morning":
        w *= 2.0 if bin_id in FOOD_BINS else 1.1
    return w


def load_state():
    state = {i: 0 for i in range(1, 10)}
    if os.path.exists("output/state.csv"):
        with open("output/state.csv") as f:
            for r in csv.reader(f):
                state[int(r[0])] = float(r[1])
    return state


def save_state(state):
    with open("output/state.csv", "w", newline="") as f:
        w = csv.writer(f)
        for k in state:
            w.writerow([k, state[k]])


def clear_state():
    state = {i: 0 for i in range(1, 10)}
    save_state(state)


def collect_full(state):
    # Empty only the bins that are already full
    for b in state:
        if state[b] >= CAPACITY:
            state[b] = 0
    save_state(state)


def advance_one_hour(state, scenario):

    for bin_id in state:

        if state[bin_id] >= CAPACITY:
            continue

        base = BIN_BASE_FLOW[bin_id]
        weight = scenario_weight(bin_id, scenario)

        arrivals = base * weight * random.uniform(3, 6)
        added = arrivals * random.uniform(1, 3) 

        state[bin_id] += added
        state[bin_id] = min(state[bin_id], CAPACITY)

    save_state(state)


def run_simulation(scenario):

    state = load_state()
    all_runs = []

    for _ in range(RUNS):
        run = []

        for bin_id in range(1, 10):

            if state[bin_id] >= CAPACITY:
                run.append((9999, bin_id))
                continue

            base = BIN_BASE_FLOW[bin_id]
            weight = scenario_weight(bin_id, scenario)

            arrivals = base * weight * random.uniform(3, 6)
            new_trash = arrivals * random.uniform(1, 3)

            total = state[bin_id] + new_trash
            overflow_time = max(0.01, (CAPACITY - total) / arrivals)

            run.append((overflow_time, bin_id))

        all_runs.append(run)

    results = compute_ranking(all_runs)

    os.makedirs("output", exist_ok=True)

    with open("output/results.csv", "w", newline="") as f:
        w = csv.writer(f)
        w.writerow(["BinID", "Rank", "Probability", "Fill"])

        for r in results:
            fill = round((state[r["bin"]] / CAPACITY) * 100, 1)
            w.writerow([r["bin"], r["rank"], r["probability"], fill])

    print("[PYTHON] Scenario:", scenario)
    print("[PYTHON] Simulation finished.")
    print("[PYTHON] Results saved to output/results.csv")


if __name__ == "__main__":

    scenario = "normal"
    mode = "run"

    if len(sys.argv) > 1:
        scenario = sys.argv[1]
    if len(sys.argv) > 2:
        mode = sys.argv[2]

    os.makedirs("output", exist_ok=True)

    state = load_state()

    if mode == "clear":
        clear_state()
        print("[PYTHON] Trash cleared.")

    elif mode == "collect":
        collect_full(state)
        print("[PYTHON] Full bins collected.")

    elif mode == "hour":
        advance_one_hour(state, scenario)
        run_simulation(scenario)

    else:  # normal run
        run_simulation(scenario)
