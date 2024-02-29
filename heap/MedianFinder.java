import java.util.ArrayList;

class MedianFinder {
    public MedianFinder() {
        _minHeap = new Heap(false);
        _maxHeap = new Heap(true);
    }

    private Heap _minHeap;
    private Heap _maxHeap;

    public void addNum(int num) {
        _maxHeap.addNum(num);

        _minHeap.addNum(_maxHeap.pop());

        if (_maxHeap.size < _minHeap.size) {
            _maxHeap.addNum(_minHeap.pop());
        }
    }

    public double findMedian() {
        return _maxHeap.size > _minHeap.size ? _maxHeap.top() : ((double) _maxHeap.top() + _minHeap.top()) * 0.5;
    }

    class Heap {
        public ArrayList<Integer> array;
        public int size;
        public boolean isMaxHeap;

        public Heap(boolean isMax) {
            array = new ArrayList<Integer>();
            size = 0;
            isMaxHeap = isMax;
        }

        private int parent(int index) {
            return (index - 1) / 2;
        }

        private int left(int index) {
            return 2 * index + 1;
        }

        private int right(int index) {
            return 2 * index + 2;
        }

        public int top() {
            return size == 0 ? 0 : array.get(0);
        }

        public void addNum(int num) {
            array.add(num);
            int index = size;
            size++;

            while (index > 0) {
                boolean shouldSwap = false;
                int parentIndex = parent(index);
                int current = array.get(index);
                int parent = array.get(parentIndex);

                if (isMaxHeap) {
                    if (current > parent) {
                        shouldSwap = true;
                    }
                } else {
                    if (current < parent) {
                        shouldSwap = true;
                    }
                }

                if (!shouldSwap) {
                    break;
                }

                array.set(parentIndex, current);
                array.set(index, parent);
                index = parentIndex;
            }
        }

        public int pop() {
            if (size == 1) {
                size--;
                return array.remove(0);
            }

            int result = array.get(0);
            array.set(0, array.remove(size - 1));
            size--;

            if (isMaxHeap) {
                maxHeapify(0);
            } else {
                minHeapify(0);
            }

            return result;
        }

        private void maxHeapify(int index) {
            int leftIndex = left(index);
            int rightIndex = right(index);
            int largest = index;

            if (leftIndex < size && array.get(leftIndex) > array.get(index)) {
                largest = leftIndex;
            }

            if (rightIndex < size && array.get(rightIndex) > array.get(largest)) {
                largest = rightIndex;
            }

            if (largest != index) {
                int temp = array.get(index);
                array.set(index, array.get(largest));
                array.set(largest, temp);
                maxHeapify(largest);
            }
        }

        private void minHeapify(int index) {
            int leftIndex = left(index);
            int rightIndex = right(index);
            int smallest = index;

            if (leftIndex < size && array.get(leftIndex) < array.get(index)) {
                smallest = leftIndex;
            }

            if (rightIndex < size && array.get(rightIndex) < array.get(smallest)) {
                smallest = rightIndex;
            }

            if (smallest != index) {
                int temp = array.get(index);
                array.set(index, array.get(smallest));
                array.set(smallest, temp);
                minHeapify(smallest);
            }
        }
    }
}
