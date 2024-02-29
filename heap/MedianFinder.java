import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

class MedianFinder {
    public MedianFinder() {
        this._array = new ArrayList<Integer>();
    }

    private ArrayList<Integer> _array;

    public void addNum(int num) {
        this._array.add(num);
    }

    // TODO: rewrite this to use a min and max heap right now it is too slow for
    // really large arrays since the problem defines it as a stream constantly
    // calling add
    public double findMedian() {
        int n = this._array.size();
        int lowerMedianIndex = (int) Math.floor((n + 1) / 2.0);
        int lowerMedian = getIth(0, n - 1, lowerMedianIndex);

        // is n odd
        if ((n & 1) == 1) {
            return (double) lowerMedian;
        }

        int upperMedianIndex = (int) Math.ceil((n + 1) / 2.0);
        int upperMedian = getIth(0, n - 1, upperMedianIndex);
        return (upperMedian + lowerMedian) / 2.0;

    }

    private int getIth(int startIndex, int endIndex, int targetIndex) {
        if (startIndex == endIndex) {
            return this._array.get(startIndex);
        }

        int pivot = this.getPartition(startIndex, endIndex);
        int k = pivot - startIndex;

        if (targetIndex == k) {
            return this._array.get(pivot);
        } else if (targetIndex < k) {
            return getIth(startIndex, pivot + 1, targetIndex);
        }

        return getIth(pivot, endIndex, targetIndex - k);
    }

    private int getPartition(int startIndex, int endIndex) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(startIndex, endIndex + 1);
        int temp = this._array.get(endIndex);
        this._array.set(endIndex, this._array.get(pivotIndex));
        this._array.set(pivotIndex, temp);

        int pivot = this._array.get(endIndex);
        int low = startIndex - 1;

        for (int idx = startIndex; idx < endIndex; idx++) {
            if (this._array.get(idx) <= pivot) {
                low = low + 1;
                temp = this._array.get(low);
                this._array.set(low, this._array.get(idx));
                this._array.set(idx, temp);

            }
        }
        temp = this._array.get(low + 1);
        this._array.set(low + 1, this._array.get(endIndex));
        this._array.set(endIndex, temp);
        return low + 1;
    }
    /*
     *
     *
     *
     * Adding number 41
     * MaxHeap lo: [41] // MaxHeap stores the largest value at the top (index 0)
     * MinHeap hi: [] // MinHeap stores the smallest value at the top (index 0)
     * Median is 41
     * =======================
     * Adding number 35
     * MaxHeap lo: [35]
     * MinHeap hi: [41]
     * Median is 38
     * =======================
     * Adding number 62
     * MaxHeap lo: [41, 35]
     * MinHeap hi: [62]
     * Median is 41
     * =======================
     * Adding number 4
     * MaxHeap lo: [35, 4]
     * MinHeap hi: [41, 62]
     * Median is 38
     * =======================
     * Adding number 97
     * MaxHeap lo: [41, 35, 4]
     * MinHeap hi: [62, 97]
     * Median is 41
     * =======================
     * Adding number 108
     * MaxHeap lo: [41, 35, 4]
     * MinHeap hi: [62, 97, 108]
     * Median is 51.5
     *
     *
     *
     */

}
