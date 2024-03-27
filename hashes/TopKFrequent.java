import java.util.HashMap;

class Solution {
	// solve using two hashmaps
	private HashMap<Integer, Integer> _worker;

	public int[] topKFrequent(int[] nums, int k) {
		_worker = new HashMap<>();
		for (int num : nums) {
			_worker.put(num, 1 + _worker.getOrDefault(num, 0));
		}

		int[] result = _worker.keySet().stream().mapToInt(x -> x).toArray();
		quicksort(result, 0, result.length - 1);

		int[] finalResult = new int[k];
		int p1 = 0;
		int p2 = result.length - 1;
		while (p1 < k) {
			finalResult[p1++] = result[p2--];
		}
		return finalResult;
	}

	private void quicksort(int[] keys, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}

		int newPivot = partition(keys, startIndex, endIndex);
		quicksort(keys, startIndex, newPivot - 1);
		quicksort(keys, newPivot + 1, endIndex);
	}

	private int partition(int[] keys, int startIndex, int endIndex) {
		int pivot = _worker.get(keys[endIndex]);
		int low = startIndex - 1;
		for (int j = startIndex; j < endIndex; j++) {
			if (_worker.get(keys[j]) <= pivot) {
				low = low + 1;
				int temp = keys[low];
				keys[low] = keys[j];
				keys[j] = temp;
			}
		}
		int temp = keys[low + 1];
		keys[low + 1] = keys[endIndex];
		keys[endIndex] = temp;
		return low + 1;
	}
}
