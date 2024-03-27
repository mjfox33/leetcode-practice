import java.util.HashMap;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        _worker = new HashMap<>();
        for (int num : nums) {
            _worker.put(num, 1 + _worker.getOrDefault(num, 0));
        }

        _heapSize = k;
        _heap = new int[k];

        int p1 = 0;

        for (int key : _worker.keySet()) {
            // init heap with the first k elements
            if (p1 < k) {
                _heap[p1++] = key;
                continue;
            }

            // minHeapify the heap so it's set up correctly
            if (p1 == k) {
                for (int i = _heapSize / 2 - 1; i >= 0; i--) {
                    minHeapify(i);
                }
            }

            if (_worker.get(key) > _worker.get(_heap[0])) {
                _heap[0] = key;
                minHeapify(0);
            }
        }

        return _heap;
    }

    private int _heapSize;
    private int[] _heap;

    private HashMap<Integer, Integer> _worker;

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private void minHeapify(int index) {
        int leftIndex = left(index);
        int rightIndex = right(index);
        int smallest = index;

        if (leftIndex < _heapSize && _worker.get(_heap[leftIndex]) < _worker.get(_heap[index])) {
            smallest = leftIndex;
        }

        if (rightIndex < _heapSize && _worker.get(_heap[rightIndex]) < _worker.get(_heap[smallest])) {
            smallest = rightIndex;
        }

        if (smallest != index) {
            int temp = _heap[index];
            _heap[index] = _heap[smallest];
            _heap[smallest] = temp;

            minHeapify(smallest);
        }
    }
}
