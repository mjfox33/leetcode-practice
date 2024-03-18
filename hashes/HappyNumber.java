import java.util.HashSet;

class Solution {
	public boolean isHappy(int n) {

		HashSet<Integer> seen = new HashSet<Integer>();

		while (!seen.contains(n) && n != 1) {
			seen.add(n);
			int worker = 0;

			while (n > 0) {
				int temp = n % 10;
				worker += temp * temp;
				n = n / 10;
			}

			n = worker;
		}

		return (n == 1);
	}
}
