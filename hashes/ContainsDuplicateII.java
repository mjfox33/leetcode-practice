import java.util.HashMap;
import java.util.ArrayList;

class Solution {
	public boolean conatinsNearybyDuplicate(int[] nums, int k) {
		HashMap<Integer, ArrayList<Integer>> worker = new HashMap<Integer, ArrayList<Integer>>();

		for (int i = 0; i < nums.length; i++) {
			if (worker.containsKey(nums[i])) {
				for (int current : worker.get(nums[i])) {
					int val = Math.abs(i - current);
					if (val <= k) {
						return true;
					}
				}
				worker.get(nums[i]).add(i);
			} else {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(i);
				worker.put(nums[i], tmp);
			}
		}

		return false;
	}
}
