class TrashBin:

    def __init__(self, bin_id, capacity):
        self.bin_id = bin_id
        self.capacity = capacity
        self.level = 0

    def add_waste(self, volume):
        self.level += volume

        if self.level >= self.capacity:
            return True   # overflow happened

        return False
