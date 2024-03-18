class MyHashSet {
	private int _primeHash;
	private LinkedList[] _hash;

	public MyHashSet() {
		_primeHash = 179;
		_hash = new LinkedList[_primeHash];
	}

	private int getBase(int key) {
		return key % _primeHash;
	}

	public void add(int key) {
		int base = getBase(key);
		if (_hash[base] == null) {
			_hash[base] = new LinkedList();
		}

		if (contains(key)) {
			return;
		}

		_hash[base].insert(key);
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
			nil = new Node(0);
			nil.next = nil;
			nil.prev = nil;
		}

		private Node search(int key) {
			nil.value = key;

			Node current = nil.next;

			while (current.value != key) {
				current = current.next;
			}

			return current;
		}

		public boolean contains(int key) {
			Node current = search(key);
			return current != nil;
		}

		public void insert(int key) {
			Node current = new Node(key);
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
		public int value;

		public Node(int val) {
			value = val;
		}
	}
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
