import java.util.HashSet;

class Solution {
	public int numJewelsInStones(String jewels, String stones) {
		HashSet<Character> jewelsSet = new HashSet<>();
		for (char c : jewels.toCharArray()) {
			jewelsSet.add(c);
		}

		int result = 0;
		for (char c : stones.toCharArray()) {
			if (jewelsSet.contains(c)) {
				result++;
			}
		}

		return result;
	}
}
