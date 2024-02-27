import java.util.*;

class WordDictionary {
    private Trie _trie;

    public WordDictionary() {
        this._trie = new Trie();
    }

    public void addWord(String word) {
        this._trie.insert(word);

    }

    public boolean search(String word) {
        return this._trie.search(word);
    }

    class Trie {
        class Node {
            public HashMap<Character, Node> children;
            public boolean isWord = false;

            public Node() {
                this.children = new HashMap<Character, Node>();

            }
        }

        private Node _root;

        public Trie() {
            this._root = new Node();
        }

        public void insert(String word) {
            Node current = this._root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    current.children.put(c, new Node());
                }
                current = current.children.get(c);
            }
            current.isWord = true;
        }

        public boolean search(String word) {
            return this.dfsSearch(word, this._root);
        }

        private boolean dfsSearch(String word, Node node) {
            Node current = node == null ? this._root : node;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!current.children.containsKey(c)) {
                    if (c == '.') {
                        for (char cc : node.children.keySet()) {
                            Node child = node.children.get(cc);
                            if (this.dfsSearch(word.substring(i + 1), child)) {
                                return true;
                            }
                        }
                    }
                    return false;
                } else {
                    current = current.children.get(c);
                }
            }
            return current.isWord;
        }

    }
}
