import java.util.*;

class MapSum {
    class Node {
        public HashMap<Character, Node> children; 
        public boolean isWord;
        public int sumBelow;
        public int endValue;

        public Node(int value) {
            this.sumBelow = value;
            this.endValue = 0;
            this.isWord = false;
            this.children = new HashMap<Character, Node>();
        }
    }

    private Node root;

    public MapSum() {
        this.root = new Node(0);
    }

    public void insert(String key, int val) {
        int old = this.getWordValue(key);
        Node current = this.root;
        for (char c : key.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node(val));
                current = current.children.get(c);
            } else {
                current = current.children.get(c);
                current.sumBelow += (val - old);
            }            
        }
        current.endValue = val;
        current.isWord = true;
    }

    private int getWordValue(String word) {
        Node current = this.root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return 0;
            }
            current = current.children.get(c);
        }
        return current.isWord ? current.endValue : 0;
    }

    public int sum(String prefix) {
        Node current = this.root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return 0;
            }
            current = current.children.get(c);
        }
        return current.sumBelow;
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
