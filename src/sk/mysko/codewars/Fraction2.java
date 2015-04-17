package sk.mysko.codewars;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertEquals;

/**
 * Precise fractions pt. 2 - conversion
 * @author boris.brinza
 */
public class Fraction2 {

	private int gcd(int a, int b) {
		while (a != b) {
			if (a > b) {
				a -= b;
			} else {
				b -= a;
			}
		}
		return a;
	}


	public static String toDecimal(String fraction) {
		Matcher fractionParts = Pattern.compile("^([0-9]+)?(?:$| |^)(?:([0-9]+)/([0-9]+))?$").matcher(fraction);

		if (!fractionParts.matches()) {
			return "0";
		}

		int divisor = 0,
				wholes = 0,
				remainder = 0;
		String decimal = "";
		HashMap<Integer, Integer> divisionsDone = new HashMap<Integer, Integer>();

		if (fractionParts.group(1) != null) {
			wholes = Integer.parseInt(fractionParts.group(1));
		}
		if (fractionParts.group(3) != null) {
			divisor = Integer.parseInt(fractionParts.group(3));
		}
		if (divisor != 0 && fractionParts.group(2) != null) {
			int numerator = Integer.parseInt(fractionParts.group(2));
			wholes += numerator / divisor;
			remainder = numerator % divisor;
		}

		while (remainder != 0) {
			remainder *= 10;
			if (divisionsDone.get(remainder) != null) {
				decimal = decimal.substring(0, divisionsDone.get(remainder))
						+ "(" + decimal.substring(divisionsDone.get(remainder)) + ")";
				break;
			}
			divisionsDone.put(remainder, decimal.length());
			decimal += (remainder / divisor);
			remainder %= divisor;
		}

		return wholes + (decimal.equals("") ? "" : "." + decimal);
	}

	public String fromDecimal(String fraction) {
		int decimalPos = fraction.indexOf('.');
		if (decimalPos == -1) {
			return fraction;
		}

		int nom = 0;
		int den = 0;

		int repPos = fraction.indexOf('(');
		int repPosEnd = fraction.indexOf(')');
		String repString = repPos == -1 ? "" : fraction.substring(repPos + 1, repPosEnd);
		String noRepString = repPos == -1 ? fraction : fraction.substring(0, repPos);
		int base = 1;
		for (int i = 0; i < noRepString.length() - decimalPos - 1; i++) {
			base *= 10;
		}

		if (repPos == -1) {		//no repeating parts
			den = base;
			nom = Integer.parseInt(fraction.substring(0, decimalPos)) * den + Integer.parseInt(noRepString.substring(decimalPos + 1));
		} else {
			int multiplier = (int) Math.pow(10, (repPos - decimalPos - 1));
			int multiplierRep = multiplier * (int) Math.pow(10, repPosEnd - repPos - 1);
			den = multiplierRep - multiplier;

			String p1 = noRepString.substring(decimalPos + 1) + repString;
			String p2 = noRepString.endsWith(".") ? "0" : noRepString.substring(decimalPos + 1);
			nom = Integer.parseInt(p1) - Integer.parseInt(p2);
			nom += Integer.parseInt(fraction.substring(0, decimalPos)) * den;

		}

		int whole = 0;
		if (nom >= den) {
			whole = nom / den;
			nom = nom % den;
		}
		int gcd = gcd(nom, den);
		return (whole != 0 ? whole + " ": "") + (nom/gcd) + "/" + (den/gcd);
	}


	public void run() {

		assertEquals("0.(010309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567)",toDecimal("1/97"));
		assertEquals("0.(1110)",toDecimal("370/3333"));
		assertEquals("0.1(6)",toDecimal("1/6"));
		assertEquals("12.75", toDecimal("12 3/4"));
		assertEquals("0.(3)", toDecimal("1/3"));
		assertEquals("1/2", fromDecimal("0.5"));
		assertEquals("0.(142857)", toDecimal("1/7"));

		assertEquals("1 1/4", fromDecimal("1.25"));
		assertEquals("3/5", fromDecimal("0.6"));
		assertEquals("1/3", fromDecimal("0.(3)"));
		assertEquals("1/7", fromDecimal("0.(142857)"));
	}

	public static void main(String[] args) {
		new Fraction2().run();;
	}

}
