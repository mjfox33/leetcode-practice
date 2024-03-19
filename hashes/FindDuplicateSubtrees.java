import java.util.*;

class Solution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	private String _empty = "";

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> answer = new LinkedList<>();
		traverse(root, new HashMap<>(), answer);
		return answer;
	}

	private String traverse(TreeNode node, Map<String, Integer> count, List<TreeNode> answer) {
		if (node == null) {
			return _empty;
		}

		String nodeToString = "(" + traverse(node.left, count, answer) + ")" + node.val +
				"(" + traverse(node.right, count, answer) + ")";

		int val = count.getOrDefault(nodeToString, 0);

		count.put(nodeToString, ++val);

		if (count.get(nodeToString) == 2) {
			answer.add(node);
		}

		return nodeToString;
	}
}
