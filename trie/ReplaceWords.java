import java.util.*;

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie t = new Trie(dictionary);
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (String word : sentence.split(" ")) {
            sb.append(prefix);
            sb.append(t.getShortestWord(word));
            prefix = " ";
        }
        return sb.toString();
    }



    class Trie {
        class Node {
            public HashMap<Character, Node> children = new HashMap<Character, Node>();
            public boolean isWord = false;

            public Node() {

            }
        }

        private Node root;

        public Trie(List<String> dictionary) {
            this.root = new Node();
            for (String word : dictionary) {
                this.insert(word);
            }
        }

        public void insert(String word) {
            Node current = this.root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    current.children.put(c, new Node());
                }
                current = current.children.get(c);
            }
            current.isWord = true;
        }

        public String getShortestWord(String word) {
            Node current = this.root;
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return word;
                }
                current = current.children.get(c);
                sb.append(c);
                if (current.isWord) {
                    return sb.toString(); 
                }
            }
            return word;
        }

        private boolean search(String word, boolean isPrefix) {
            Node current = this.root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return false;
                }
                current = current.children.get(c);
            }
            return isPrefix ? true : current.isWord;
        }

        public boolean searchWord(String word) {
            return this.search(word, false);
        }

        public boolean startsWith(String prefix) {
            return this.search(prefix, true);
        }

    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
