import java.util.HashSet;

class Solution {
	public int singleNumber(int[] nums) {
		HashSet<Integer> worker = new HashSet<Integer>();
		for (int num : nums) {
			if (worker.contains(num)) {
				worker.remove(num);
			} else {
				worker.add(num);
			}
		}

		for (int num : worker) {
			return num;
		}

		return -1;
	}
}
