package sk.mysko.codewars;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @author boris.brinza
 */
public class GoingToZeroOrInfinity {

	static BigDecimal recfact(long start, long n) {
		long i;
		if (n <= 16) {
			BigDecimal r = new BigDecimal(start);
			for (i = start + 1; i < start + n; i++) {
				r = r.multiply(new BigDecimal(i));
			}
			return r;
		}
		i = n / 2;
		return recfact(start, i).multiply(recfact(start + i, n - i));
	}


	public static double going(int n) {
		BigDecimal sumFak = BigDecimal.ZERO;
		BigDecimal lastFak = BigDecimal.ZERO;
		for (int i = 1; i <= n; i++) {
			lastFak = recfact(1, i);
			sumFak = sumFak.add(lastFak);
		}
		return sumFak.divide(lastFak).setScale(6, RoundingMode.DOWN).doubleValue();

	}
}
