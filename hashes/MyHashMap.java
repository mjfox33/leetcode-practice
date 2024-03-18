class MyHashMap {
	private int _primeHash;
	private LinkedList[] _hash;

	public MyHashMap() {
		_primeHash = 179;
		_hash = new LinkedList[_primeHash];
	}

	private int getBase(int key) {
		return key % _primeHash;
	}

	public void put(int key, int value) {
		int base = getBase(key);
		if (_hash[base] == null) {
			_hash[base] = new LinkedList();
		}

		if (contains(key)) {
			_hash[base].search(key).value = value;
			return;
		}

		_hash[base].insert(key, value);
	}

	public int get(int key) {
		int base = getBase(key);
		if (_hash[base] == null || !_hash[base].contains(key)) {
			return -1;
		}

		return _hash[base].search(key).value;

	}

	public void remove(int key) {
		int base = getBase(key);
		if (_hash[base] == null) {
			return;
		}

		_hash[base].delete(key);
	}

	public boolean contains(int key) {
		int base = getBase(key);
		if (_hash[base] == null) {
			return false;
		}
		return _hash[base].contains(key);
	}

	private class LinkedList {
		public Node nil;

		public LinkedList() {
			nil = new Node(0, 0);
			nil.next = nil;
			nil.prev = nil;
		}

		private Node search(int key) {
			nil.key = key;

			Node current = nil.next;

			while (current.key != key) {
				current = current.next;
			}

			return current;
		}

		public boolean contains(int key) {
			Node current = search(key);
			return current != nil;
		}

		public void insert(int key, int value) {
			Node current = new Node(key, value);
			current.next = nil.next;
			current.prev = nil;
			nil.next.prev = current;
			nil.next = current;
		}

		public void delete(int key) {
			Node current = search(key);

			if (current == nil) {
				return;
			}

			current.prev.next = current.next;
			current.next.prev = current.prev;
		}
	}

	private class Node {
		public Node next;
		public Node prev;
		public int key;
		public int value;

		public Node(int key, int val) {
			this.value = val;
			this.key = key;
		}
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
