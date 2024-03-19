import java.util.HashMap;

class Logger {
	private HashMap<String, Integer> _map;
	private int _allowedDiff;

	public Logger() {
		_map = new HashMap<String, Integer>();
		_allowedDiff = 9;
	}

	public boolean shouldPrintMessage(int timestamp, String message) {
		if (!_map.containsKey(message)) {
			_map.put(message, timestamp + _allowedDiff);
			return true;
		}

		int allowed = _map.get(message);
		if (timestamp > allowed) {
			_map.replace(message, timestamp + _allowedDiff);
			return true;
		}

		return false;
	}
}
