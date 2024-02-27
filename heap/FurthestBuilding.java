class Solution {
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private void minHeapify(int index) {
        int leftIndex = this.left(index);
        int rightIndex = this.right(index);
        int smallest = index;

        if (leftIndex < this.heapSize && this.heap[leftIndex] < this.heap[index]) {
            smallest = leftIndex;
        }

        if (rightIndex < this.heapSize && this.heap[rightIndex] < this.heap[smallest]) {
            smallest = rightIndex;
        }

        if (smallest != index) {
            int temp = this.heap[index];
            this.heap[index] = this.heap[smallest];
            this.heap[smallest] = temp;
            this.minHeapify(smallest);
        }
    }

    private int[] heap;
    private int heapSize;

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;

        if (ladders == 0) {
            int consumedBricks = 0;
            for (int i = 0; i < n - 1; i++) {
                int dist = heights[i + 1] - heights[i];
                if (dist > 0) {
                    consumedBricks += dist;
                }
                if (consumedBricks > bricks) {
                    return i;
                }
            }
            return n - 1;
        }

        this.heapSize = ladders;
        this.heap = new int[this.heapSize];

        int buildingIndex = 0;
        int consumedLadders = 0;
        while (buildingIndex < n - 1 && consumedLadders < ladders) {
            int dist = heights[buildingIndex + 1] - heights[buildingIndex];
            if (dist > 0) {
                this.heap[consumedLadders] = dist;
                consumedLadders++;
            }
            buildingIndex++;
        }

        for (int i = (this.heapSize / 2) - 1; i >= 0; i--) {
            this.minHeapify(i);
        }

        int consumedBricks = 0;
        for (int i = buildingIndex; i < n - 1; i++) {
            int dist = heights[i + 1] - heights[i];
            if (dist > 0) {
                if (dist > this.heap[0]) {
                    consumedBricks += this.heap[0];
                    this.heap[0] = dist;
                    this.minHeapify(0);
                } else {
                    consumedBricks += dist;
                }
            }
            if (consumedBricks > bricks) {
                return i;
            }
        }

        return n - 1;

    }
}
