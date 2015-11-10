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
		assertEquals("2000 103 123 4444 99", WeightSort.orderWeight("103 123 4444 99 2000"));
		assertEquals("11 11 2000 10003 22 123 1234000 44444444 9999", WeightSort.orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));
		assertEquals("59544965313", WeightSort.orderWeight("59544965313"));
	}

	@Test
	public void Caesar2Test() {
//		String u = "I should have known that you would have a perfect answer for me!!!";
//		List<String> v = Arrays.asList("ijJ tipvme ibw", "f lopxo uibu z", "pv xpvme ibwf ", "b qfsgfdu botx", "fs gps nf!!!");
//		assertEquals(v, CaesarTwo.encodeStr(u, 1));
//		assertEquals(u, CaesarTwo.decode(v));
//
//		u = "O CAPTAIN! my Captain! our fearful trip is done;";
//		v = Arrays.asList("opP DBQUBJ", "O! nz Dbqu", "bjo! pvs g", "fbsgvm usj", "q jt epof;");
//		assertEquals(v, CaesarTwo.encodeStr(u, 1));
//		assertEquals(u, CaesarTwo.decode(v));

		String u = "I have spread my dreams under your feet; Tread softly because you tread on my dreams. William B Yeats (1865-1939)";
		List<String> v = Arrays.asList("<[I have spread my dreams under your feet; Tread softly because you tread on my dreams. William B Yeats (1865-1939)");
		assertEquals(v, CaesarTwo.encodeStr(u, 25));
		assertEquals(u, CaesarTwo.decode(v));
	}

}
