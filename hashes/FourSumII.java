import java.util.*;

class Solution {
	public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		int result = 0;

		HashMap<Integer, Integer> worker = new HashMap<>();
		for (int a : nums1) {
			for (int b : nums2) {
				int val = worker.getOrDefault(a + b, 0);
				val++;
				worker.put(a + b, val);
			}
		}

		for (int c : nums3) {
			for (int d : nums4) {
				result += worker.getOrDefault(-(c + d), 0);
			}
		}

		return result;

	}
}
