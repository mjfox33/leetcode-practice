import math
class Solution:
    def lastStoneWeight(self, stones: list[int]) -> int:
        self.heapSize = len(stones)
        
        self.heap = [ stones[i] for i in range(self.heapSize) ]
        
        for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
            self.maxHeapify(i)
        
        while self.heapSize > 1:
            y = self.getMax()
            x = self.getMax()
            if x == y:
                continue
            self.heap[self.heapSize] = y - x
            self.heapSize += 1
            for i in range(math.floor(self.heapSize / 2) - 1, -1, -1):
                self.maxHeapify(i)
        
        if self.heapSize == 0:
            return 0
        
        if self.heapSize == 1:
            return self.heap[0]
        
        y = self.getMax()
        x = self.getMax()
        
        return 0 if x == y else y - x
    
    def getMax(self):
        result = self.heap[0]
        self.heap[0] = self.heap[self.heapSize - 1]
        self.maxHeapify(0) 
        self.heapSize -= 1
        return result
        
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
        if rightIndex < self.heapSize and self.heap[rightIndex] > self.heap[largest]:
            largest = rightIndex
            
        if largest != index:
            self.heap[index], self.heap[largest] = self.heap[largest], self.heap[index]
            self.maxHeapify(largest)

input = [9, 3, 2, 10]
s = Solution()
s.lastStoneWeight(input)