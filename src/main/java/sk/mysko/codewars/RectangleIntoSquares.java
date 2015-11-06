package sk.mysko.codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author boris.brinza
 */
public class RectangleIntoSquares {

	public List<Integer> sqInRect(int lng, int wdth) {
		List<Integer> ret = new ArrayList<>();

		if (lng == wdth) {
			return null;
		}

		while (lng != 0 || wdth != 0) {
			if (wdth > lng) {
				wdth -= lng;
				ret.add(lng);
			} else if (wdth < lng) {
				lng -= wdth;
				ret.add(wdth);
			} else {
				ret.add(lng);
				lng = 0;
				wdth = 0;

			}
		}

		return ret;
	}

	public static void main(String[] args) {
		List<Integer> res = new ArrayList<Integer>(Arrays.asList(3, 2, 1, 1));
		for (int r : res) {
			assertEquals(res, new RectangleIntoSquares().sqInRect(5, 3));
		}
		assertEquals(null, new RectangleIntoSquares().sqInRect(5, 5));
	}

}
