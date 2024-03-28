import java.util.*;

class ValidWordAbbr {
	private HashMap<String, HashSet<String>> _worker;

	public ValidWordAbbr(String[] dictionary) {
		_worker = new HashMap<String, HashSet<String>>();
		for (String word : dictionary) {
			String abbr = getAbbr(word);
			HashSet<String> hs = _worker.getOrDefault(abbr, new HashSet<>());
			hs.add(word);
			_worker.put(abbr, hs);
		}
	}

	private String getAbbr(String word) {
		char[] chars = word.toCharArray();
		int n = chars.length;
		if (n < 3) {
			return word;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(chars[0]);
		sb.append(n - 2);
		sb.append(chars[n - 1]);
		return sb.toString();
	}

	public boolean isUnique(String word) {
		String abbr = getAbbr(word);
		HashSet<String> hs = _worker.get(abbr);
		if (hs == null) {
			return true;
		}

		if (hs.size() == 1 && hs.contains(word)) {
			return true;
		}

		return false;
	}
}
