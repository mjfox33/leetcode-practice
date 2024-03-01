class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        array = nums;
        mergeSort(0, nums.length - 1);
        return array;
    }

    private int[] array;

    private void merge(int startLeft, int endLeft, int endRight) {
        int nLeft = endLeft - startLeft + 1;
        int nRight = endRight - endLeft;

        int[] left = new int[nLeft];
        int[] right = new int[nRight];

        for (int i = 0; i < nLeft; i++) {
            left[i] = array[startLeft + i];
        }
        for (int i = 0; i < nRight; i++) {
            right[i] = array[endLeft + i + 1];
        }

        int pLeft = 0;
        int pRight = 0;
        int pMain = startLeft;

        while (pLeft < nLeft && pRight < nRight) {
            if (left[pLeft] <= right[pRight]) {
                array[pMain] = left[pLeft];
                pLeft++;
            } else {
                array[pMain] = right[pRight];
                pRight++;
            }
            pMain++;
        }

        while (pLeft < nLeft) {
            array[pMain] = left[pLeft];
            pMain++;
            pLeft++;
        }

        while (pRight < nRight) {
            array[pMain] = right[pRight];
            pMain++;
            pRight++;
        }
    }

    private void mergeSort(int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            mergeSort(startIndex, middleIndex);
            mergeSort(middleIndex + 1, endIndex);
            merge(startIndex, middleIndex, endIndex);
        }
    }
}
