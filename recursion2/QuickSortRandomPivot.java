import java.util.concurrent.ThreadLocalRandom;

class Solution {
    public int[] sortArray(int[] nums) {
        array = nums;
        sort(0, array.length - 1);
    }

    private int[] array;

    private void sort(int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int newPivot = partition(startIndex, endIndex);

        sort(startIndex, newPivot - 1);

        sort(newPivot + 1, endIndex);
    }

    private int partition(int startIndex, int endIndex) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(startIndex, endIndex + 1);
        int temp = array[endIndex];
        array[endIndex] = array[pivotIndex];
        array[pivotIndex] = temp;
        int pivot = array[endIndex];
        int low = startIndex - 1;
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] <= pivot) {
                low = low + 1;
                temp = array[low];
                array[low] = array[i];
                array[i] = temp;
            }
        }
        temp = array[low + 1];
        array[low + 1] = array[endIndex];
        array[endIndex] = temp;
        return low + 1;
    }
}
