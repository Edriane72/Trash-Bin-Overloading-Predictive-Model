def compute_ranking(all_runs):

    counts = {}

    for run in all_runs:
        run_sorted = sorted(run, key=lambda x: x[0])

        for rank, (_, bin_id) in enumerate(run_sorted, start=1):
            if bin_id not in counts:
                counts[bin_id] = [0, 0]  # first_place, total_runs

            if rank == 1:
                counts[bin_id][0] += 1

            counts[bin_id][1] += 1

    results = []

    for bin_id in sorted(counts.keys()):
        firsts, total = counts[bin_id]
        prob = firsts / total if total > 0 else 0

        results.append({
            "bin": bin_id,
            "probability": round(prob, 3)
        })

    # sort by probability
    results.sort(key=lambda x: x["probability"], reverse=True)

    ranked = []
    rank = 1

    for r in results:
        ranked.append({
            "bin": r["bin"],
            "rank": rank,
            "probability": r["probability"]
        })
        rank += 1

    return ranked
