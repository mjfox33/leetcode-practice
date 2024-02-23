import math


class Solution:
    def kthSmallest(self, matrix: list[list[int]], k: int) -> int:
        n = len(matrix)

        if k <= n:
            return matrix[0][k - 1]

        self.heapSize = k

        # setup the heap
        self.heap = [matrix[i // n][(i % n) - 1] for i in range(k)]

        for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
            self.maxHeapify(i)

        for i in range(k, n * n):
            row = i // n
            col = (i % n) - 1
            val = self.matrix[row][col]
            if val < self.heap[0]:
                self.heap[0] = val
                self.maxHeapify(0)

        return self.heap[0]

    def parent(self, i):
        return math.floor((i - 1) / 2)

    def left(self, i):
        return 2 * i + 1

    def right(self, i):
        return 2 * i + 2

    def maxHeapify(self, index):
        leftIndex = self.left(index)
        rightIndex = self.right(index)
        largest = index

        if leftIndex < self.heapSize and self.heap[leftIndex] > self.heap[index]:
            largest = leftIndex
        if (
            rightIndex < self.heapSize
            and self.heap[rightIndex] > self.Heap.array[largest]
        ):
            largest = rightIndex

        if largest != index:
            self.heap[index], self.heap[largest] = self.heap[largest], self.heap[index]
            self.maxHeapify(largest)


points = [[1, 3], [-2, 2]]
k = 1

s = Solution()
print(s.kClosest(points, k))
