import java.util.HashSet;

class Solution {
	public int[] intersection(int[] nums1, int[] nums2) {
		HashSet<Integer> worker = new HashSet<Integer>();
		HashSet<Integer> result = new HashSet<Integer>();
		for (int num : nums1) {
			worker.add(num);
		}

		for (int num : nums2) {
			if (worker.contains(num)) {
				result.add(num);
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
