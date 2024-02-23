import java.util.*;

class Trie {
    class Node {
        public HashMap<Character, Node> children = new HashMap<Character, Node>();
        public boolean isEnd = false;

        public Node() {

        }
    }

    private Node root = new Node();

    public Trie() {

    }

    public void insert(String word) {
        Node current = this.root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node());
            }
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        Node current = this.root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node current = this.root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return true;
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
