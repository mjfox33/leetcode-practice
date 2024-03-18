import java.util.HashSet;

class Solution {
	public boolean conatinsDuplicate(int[] nums) {
		HashSet<Integer> worker = new HashSet<Integer>();
		for (int num : nums) {
			if (worker.contains(num)) {
				return true;
			}
			worker.add(num);
		}
		return false;
	}
}
