def compute_ranking(events):

    # Sort by time (earliest overflow first)
    events.sort()

    results = []
    rank = 1
    total = len(events)

    for time, bin_id in events:
        results.append({
            "bin": bin_id,
            "rank": rank,
            "probability": round(1.0 / total, 2)
        })
        rank += 1

    return results
