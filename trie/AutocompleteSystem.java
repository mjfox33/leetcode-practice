import java.util.*;
import java.lang.StringBuilder;

class AutocompleteSystem {
    private Trie _trie;
    private StringBuilder _sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this._sb = new StringBuilder();
        this._trie = new Trie();

        // NOTE: Constraints n == sentences.length == times.length
        int n = sentences.length;

        for (int i = 0; i < n; i++) {
            this._trie.insertOrUpdate(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<String>();

        // add the new sentence or update the old count and bail
        if (c == '#') {
            this._trie.insertOrUpdate(this._sb.toString(), 1);
            this._sb.setLength(0);
            return result;
        }

        this._sb.append(c);
        // TODO: get the list of sentences at this point
        List<String> results = this._trie.getSentences(this._sb.toString());
        if (results.size() < 4) {
            return results;
        }
        Collections.sort(results);
        return results.subList(0, 3);
    }

    class Trie {
        class Node {
            public HashMap<Character, Node> children;
            public boolean isSentence;
            public int count;

            public Node() {
                this.children = new HashMap<Character, Node>();
                this.isSentence = false;
                this.count = 0;
            }
        }

        private Node _root;

        public Trie() {
            this._root = new Node();
        }

        public void insertOrUpdate(String sentence, int count) {
            Node current = this._root;
            for (char c : sentence.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    current.children.put(c, new Node());
                }
                current = current.children.get(c);
            }
            current.count = current.isSentence ? current.count++ : count;
            current.isSentence = true;
        }

        public List<String> getSentences(String prefix) {
            List<String> result = new ArrayList<String>();
            Node current = this._root;
            StringBuilder sb = new StringBuilder();

            for (char c : prefix.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return result;
                }
                current = current.children.get(c);
                sb.append(c);
            }

            if (current.isSentence) {
                result.add(sb.toString());
            }

            Stack<Map.Entry<Character, Node>> stack = new Stack<Map.Entry<Character, Node>>();
            for (Map.Entry<Character, Node> kvp : current.children.entrySet()) {
                stack.push(kvp);
            }

            while (!stack.empty()) {
                Map.Entry<Character, Node> entry = stack.pop();
                sb.append(entry.getKey());

                current = entry.getValue();

                if (current.isSentence) {
                    result.add(sb.toString());
                }

                for (Map.Entry<Character, Node> kvp : current.children.entrySet()) {
                    stack.push(kvp);
                }

                sb.setLength(sb.length() - 1);
            }

        }

    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insertOrUpdate(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
