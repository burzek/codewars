package codewars;

import static org.junit.Assert.assertEquals;

/**
 * @author boris.brinza
 */
public class RomanNumeralsEncoder {

	public String solution(int n) {
		StringBuilder ret = new StringBuilder();
		ret.append(solutionInternal(n, 1000, new String[] {"M","MM","MMM"}));
		ret.append(solutionInternal(n, 100, new String[] {"C","CC","CCC","CD", "D", "DC", "DCC", "DCCC", "CM"}));
		ret.append(solutionInternal(n, 10, new String[] {"X","XX","XXX","XL", "L", "LX", "LXX", "LXXX", "XC"}));
		ret.append(solutionInternal(n, 1, new String[] {"I","II","III","IV", "V", "VI", "VII", "VIII", "IX"}));
		return ret.toString();
	}

	public String solutionInternal(int n, int divider, String[] mapping) {
		int test = n % (divider * 10);
		if (n  < divider || test == 0) {
			return "";
		}

		return mapping[(test / divider) - 1];
	}

	public static void main(String[] args) {
		RomanNumeralsEncoder rne = new RomanNumeralsEncoder();
		assertEquals("solution(2435) should equal to MMCDXXXV", "MMCDXXXV", rne.solution(2435));
		assertEquals("solution(1) should equal to I", "I", rne.solution(1));
		assertEquals("solution(4) should equal to IV", "IV", rne.solution(4));
		assertEquals("solution(6) should equal to VI", "VI", rne.solution(6));
		assertEquals("solution(91) should equal to XCI", "XCI", rne.solution(91));
	}

}
