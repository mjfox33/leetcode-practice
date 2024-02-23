import math
class Solution:
    def connectSticks(self, sticks: list[int]) -> int:
        self.heapSize = len(sticks)
        if self.heapSize == 1:
            return 0
        
        if self.heapSize == 2:
            return sticks[0] + sticks[1]
        
        self.heap = [ sticks[i] for i in range(self.heapSize) ]
        for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
            self.minHeapify(i)
        
        runningCost = 0    
        while self.heapSize > 1:
            y = self.getMin()
            x = self.getMin()
            runningCost = runningCost + y + x
            self.heap[self.heapSize] = y + x
            self.heapSize += 1
            for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
                self.minHeapify(i)
        
        if self.heapSize == 1:
            return self.heap[0]
        
        y = self.getMax()
        x = self.getMax()
        
        return runningCost + y + x
        
    def getMin(self):
        result = self.heap[0]
        self.heap[0] = self.heap[self.heapSize - 1]
        self.minHeapify(0) 
        self.heapSize -= 1
        return result
        
    def parent(self, i):
        return math.floor((i - 1) / 2)
    
    def left(self, i):
        return 2 * i + 1
    
    def right(self, i):
        return 2 * i + 2
    
    def minHeapify(self, index):
        leftIndex = self.left(index)
        rightIndex = self.right(index)
        
        smallest = index
        
        if leftIndex < self.heapSize and self.heap[leftIndex] < self.heap[index]:
            smallest = leftIndex
        
        if rightIndex < self.heapSize and self.heap[rightIndex] < self.heap[smallest]:
            smallest = rightIndex
        
        if smallest != index:    
            self.heap[index], self.heap[smallest] = self.heap[smallest], self.heap[index]
            self.minHeapify(smallest)

input = [ 2, 4, 3]
s = Solution()
s.connectSticks(input)
