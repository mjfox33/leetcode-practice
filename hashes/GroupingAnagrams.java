import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<HashMap<Character, Integer>, ArrayList<String>> mainWorker = new HashMap<HashMap<Character, Integer>, ArrayList<String>>();

		for (String str : strs) {
			HashMap<Character, Integer> worker = new HashMap<Character, Integer>();
			for (char c : str.toCharArray()) {
				if (worker.containsKey(c)) {
					int val = worker.get(c);
					worker.replace(c, ++val);
				} else {
					worker.put(c, 1);
				}
			}

			if (!mainWorker.containsKey(worker)) {
				mainWorker.put(worker, new ArrayList<String>());
			}

			mainWorker.get(worker).add(str);
		}

		List<List<String>> answer = new ArrayList<List<String>>();

		for (ArrayList<String> e : mainWorker.values()) {
			answer.add(e);
		}

		return answer;
	}
}
