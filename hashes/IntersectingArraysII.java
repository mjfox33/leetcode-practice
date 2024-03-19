import java.util.HashMap;
import java.util.ArrayList;

class Solution {
	public int[] intersection(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> worker = new HashMap<Integer, Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int num : nums1) {
			if (worker.containsKey(num)) {
				int val = worker.get(num);
				worker.replace(num, ++val);
			} else {
				worker.put(num, 1);
			}
		}

		for (int num : nums2) {
			if (worker.containsKey(num)) {
				result.add(num);
				int val = worker.get(num);
				if (val == 1) {
					worker.remove(num);
				} else {
					worker.replace(num, --val);
				}
			}
		}

		int[] answer = new int[result.size()];
		int pointer = 0;
		for (int num : result) {
			answer[pointer] = num;
			pointer++;
		}

		return answer;
	}

}
