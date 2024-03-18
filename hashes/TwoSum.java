import java.util.HashMap;

class Solution {
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> worker = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int missing = target - nums[i];
			if (worker.containsKey(missing)) {
				int x = worker.get(missing);
				return new int[] { i, x };
			}

			worker.put(nums[i], i);
		}
		return new int[] { 0, 1 };
	}
}
