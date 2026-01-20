class Monitor:

    def __init__(self):
        self.events = []   # list of (time, bin_id)

    def record(self, time, bin_id):
        self.events.append((time, bin_id))
