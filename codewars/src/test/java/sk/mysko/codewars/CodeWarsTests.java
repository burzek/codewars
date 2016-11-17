package sk.mysko.codewars;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author boris.brinza
 */
public class CodeWarsTests {
	@Test
	public void Weight4WeightTest() {
		assertEquals("2000 103 123 4444 99", sk.mysko.codewars.WeightSort.orderWeight("103 123 4444 99 2000"));
		assertEquals("11 11 2000 10003 22 123 1234000 44444444 9999", sk.mysko.codewars.WeightSort.orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));
		assertEquals("59544965313", sk.mysko.codewars.WeightSort.orderWeight("59544965313"));
	}

	@Test
	public void Caesar2Test() {
		String u = "I should have known that you would have a perfect answer for me!!!";
		List<String> v = Arrays.asList("ijJ tipvme ibw", "f lopxo uibu z", "pv xpvme ibwf ", "b qfsgfdu botx", "fs gps nf!!!");
		assertEquals(v, sk.mysko.codewars.CaesarTwo.encodeStr(u, 1));
		assertEquals(u, sk.mysko.codewars.CaesarTwo.decode(v));

		u = "O CAPTAIN! my Captain! our fearful trip is done;";
		v = Arrays.asList("opP DBQUBJ", "O! nz Dbqu", "bjo! pvs g", "fbsgvm usj", "q jt epof;");
		assertEquals(v, sk.mysko.codewars.CaesarTwo.encodeStr(u, 1));
		assertEquals(u, sk.mysko.codewars.CaesarTwo.decode(v));
	}


	@Test
	public void ZeroOrInfinityTest() {
		double DELTA = 1e-10;

		assertEquals(1.2125, sk.mysko.codewars.GoingToZeroOrInfinity.going(523), DELTA);
		assertEquals(1.2125, sk.mysko.codewars.GoingToZeroOrInfinity.going(6), DELTA);
		assertEquals(1.173214, sk.mysko.codewars.GoingToZeroOrInfinity.going(7), DELTA);

	}
}
