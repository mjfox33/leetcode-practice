import math


class Solution:
    def kClosest(self, points: list[list[int]], k: int) -> list[list[int]]:
        n = len(points)

        if n == k:
            return points

        self.heapSize = k

        # calculate the distances for each point
        self.dists = [
            points[i][0] * points[i][0] + points[i][1] * points[i][1]
            for i in range(len(points))
        ]

        # setup the heap
        self.heap = [i for i in range(k)]

        for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
            self.maxHeapify(i)

        if n == k:
            return self.array[0]

        for i in range(k, n):
            if self.dists[i] < self.dists[self.heap[0]]:
                self.heap[0] = i
                self.maxHeapify(0)

        return [[points[self.heap[i]][0], points[self.heap[i]][1]] for i in range(k)]

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

        if (
            leftIndex < self.heapSize
            and self.dists[self.heap[leftIndex]] > self.dists[self.heap[index]]
        ):
            largest = leftIndex
        if (
            rightIndex < self.heapSize
            and self.dists[self.heap[rightIndex]] > self.dists[self.heap[largest]]
        ):
            largest = rightIndex

        if largest != index:
            self.heap[index], self.heap[largest] = self.heap[largest], self.heap[index]
            self.maxHeapify(largest)
