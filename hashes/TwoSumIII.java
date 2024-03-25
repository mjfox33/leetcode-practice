import java.util.HashMap;

class TwoSum {
	private HashMap<Integer, Integer> _worker;

	public TwoSum() {
		_worker = new HashMap<>();
	}

	public void add(int number) {
		int val = _worker.getOrDefault(number, 0);
		val++;
		_worker.put(number, val);
	}

	public boolean find(int value) {
		boolean result = false;
		for (Integer num : _worker.keySet()) {
			int diff = value - num;
			if (num == diff) {
				result = result || _worker.get(num) > 1;
			} else {
				if (_worker.containsKey(diff)) {
					result = true;
				}
			}
			if (result == true) {
				return true;
			}
		}
		return result;
	}
}
