import math
class Solution:
    def kWeakestRows(self, mat, k) -> list[int]:
        self.values = [ sum(mat[i]) for i in range(len(mat)) ]
        self.heap = [ i for i in range(k) ]
        self.heapSize = k
        
        for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
            self.maxHeapify(i)
            
        for i in range(k, len(mat)):            
            if self.values[i] < self.values[self.heap[0]]:
                self.heap[0] = i
                self.maxHeapify(0)
        
        index = k - 1
        self.result = [ None for _ in range(k) ]
        while index >= 0:
            self.result[index] = self.heap[0]
            self.heap[0] = self.heap[self.heapSize - 1]
            self.heapSize -= 1
            index -= 1
            for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
                self.maxHeapify(i)

        return self.heap
    
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
        
        if leftIndex < self.heapSize and self.values[self.heap[leftIndex]] > self.values[self.heap[index]]:
            largest = leftIndex
        if rightIndex < self.heapSize and self.values[self.heap[rightIndex]] > self.values[self.heap[largest]]:
            largest = rightIndex
            
        if largest != index:
            self.heap[index], self.heap[largest] = self.heap[largest], self.heap[index]
            self.maxHeapify(largest)


class Solution2:
    def kWeakestRows(self, mat, k) -> list[int]:
        maxColumns = 100
        rows = len(mat)
        self.values = [ sum(mat[i]) for i in range(rows) ]
        self.worker = [ [] for _ in range(maxColumns) ]
        for i in range(rows):
            self.worker[self.values[i]].append(i)
        result = []
        runningCount = 0
        index = 0
        while runningCount < k:
            currentCount = len(self.worker[index])
            if currentCount == 0:
                index += 1
                continue
            
            for j in range(currentCount):
                if runningCount == k:
                    break
                result.append(self.worker[index][j])
                runningCount += 1
            index += 1
                
        return result
    
input = [
    [1,1,0,0,0],
    [1,1,1,1,0],
    [1,0,0,0,0],
    [1,1,0,0,0],
    [1,1,1,1,1]
]
k = 3
s = Solution2()
s.kWeakestRows(input, k)