import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	public String[] findRestaurant(String[] list1, String[] list2) {
		int minSum = 2001;
		ArrayList<String> result = new ArrayList<String>();
		HashMap<String, Integer> worker = new HashMap<String, Integer>();
		for (int i = 0; i < list1.length; i++) {
			worker.put(list1[i], i);
		}

		for (int i = 0; i < list2.length; i++) {
			if (worker.containsKey(list2[i])) {
				int check = i + worker.get(list2[i]);
				if (check < minSum) {
					minSum = check;
					result = new ArrayList<String>();
					result.add(list2[i]);
				} else if (check == minSum) {
					result.add(list2[i]);
				}
			}
		}

		String[] a = new String[result.size()];
		result.toArray(a);
		return a;

	}
}
