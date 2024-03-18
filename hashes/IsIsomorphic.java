import java.util.HashMap;

class Solution {
	public boolean isIsomorphic(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		HashMap<Character, Character> worker = new HashMap<Character, Character>();
		char[] first = s.toCharArray();
		char[] second = t.toCharArray();

		for (int i = 0; i < first.length; i++) {
			if (worker.containsKey(first[i])) {
				if (second[i] != worker.get(first[i])) {
					return false;
				}
			} else {
				if (worker.containsValue(second[i])) {
					return false;
				} else {
					worker.put(first[i], second[i]);
				}
			}
		}

		return true;

	}
}
