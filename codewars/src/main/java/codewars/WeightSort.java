package sk.mysko.codewars;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author boris.brinza
 */
public class WeightSort {

	private static int getSummaryWeight(long w) {
		int sum = 0;
		while (w > 0) {
			sum += (w % 10);
			w /= 10;
		}
		return sum;
	}

	public static String orderWeight(String strng) {
		String[] weights = strng.split(" ");
		Arrays.sort(weights, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int cmp = getSummaryWeight(Long.parseLong(o1)) - getSummaryWeight(Long.parseLong(o2));
				return cmp == 0 ? o1.compareTo(o2) : cmp;
			}
		});

		StringBuilder ret = new StringBuilder();
		for (String s : weights) {
			ret = ret.append(s).append(" ");
		}
		return ret.substring(0, ret.length() - 1);
	}


}
