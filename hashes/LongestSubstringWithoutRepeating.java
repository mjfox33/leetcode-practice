import java.util.HashSet;

class Solution {
	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		if (n < 2) {
			return n;
		}

		HashSet<Character> worker = new HashSet<>();
		int left = 0;
		int right = 1;
		int maxLength = 1;
		char[] chars = s.toCharArray();

		worker.add(chars[left]);

		while (left < n - 1) {
			// while there is a new character add it
			while (right < n && !worker.contains(chars[right])) {
				worker.add(chars[right++]);
			}

			// update the size if needed
			if (worker.size() > maxLength) {
				maxLength = worker.size();
			}

			if (right == n) {
				break;
			}

			while (left < right && worker.contains(chars[right])) {
				worker.remove(chars[left++]);
			}

		}

		return maxLength;

	}
}
