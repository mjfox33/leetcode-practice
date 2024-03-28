import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

class RandomizedSet {
	private HashSet<Integer> _worker;

	public RandomizedSet() {
		_worker = new HashSet<>();
	}

	public boolean insert(int val) {
		return _worker.add(val);
	}

	public boolean remove(int val) {
		return _worker.remove(val);
	}

	public int getRandom() {
		Integer[] temp = _worker.toArray(new Integer[0]);
		int minimum = 0;
		int maximum = temp.length;
		int rnd = ThreadLocalRandom.current().nextInt(minimum, maximum);

		int result = temp[rnd];
		return result;
	}
}
