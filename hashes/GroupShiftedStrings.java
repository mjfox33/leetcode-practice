import java.util.*;

class Solution {
	public List<List<String>> groupStrings(String[] strings) {
		HashMap<String, List<String>> worker = new HashMap<>();
		for (String str : strings) {
			String hash = getHash(str);
			if (!worker.containsKey(hash)) {
				worker.put(hash, new ArrayList<>());
			}
			worker.get(hash).add(str);
		}

		List<List<String>> answer = new ArrayList<>();
		for (List<String> group : worker.values()) {
			answer.add(group);
		}
		return answer;
	}

	private String getHash(String str) {
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < chars.length - 1; i++) {
			char key = (char) ((chars[i + 1] - chars[i] + 26) % 26 + 'a');
			sb.append(key);
		}

		return sb.toString();
	}
}
