import java.util.*;

class Solution {
	public int firstUniqChar(String s) {
		HashMap<Character, Integer> worker = new HashMap<Character, Integer>();
		char[] chars = s.toCharArray();
		int n = chars.length;
		for (int i = 0; i < n; i++) {
			if (worker.containsKey(chars[i])) {
				worker.replace(chars[i], n + 1);
			} else {
				worker.put(chars[i], i);
			}
		}

		int min = n;
		for (int current : worker.values()) {
			if (current < min) {
				min = current;
			}
		}

		return min == n ? -1 : min;
	}
}
