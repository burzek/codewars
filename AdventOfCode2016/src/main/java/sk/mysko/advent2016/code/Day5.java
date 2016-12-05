package sk.mysko.advent2016.code;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author boris.brinza
 */
public class Day5 extends AdventOfCodeBase<String> {


	protected String runPart1(String input) {
		int foundCount = 0;
		int idx = 0;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("algorithm exception:" + e.toString());
		}

		char hex[] = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		input = input.trim();
		StringBuilder code = new StringBuilder();
		idx = 0;

		do {
			byte[] test = md5.digest((input + idx).getBytes());
			if (test[0] == 0 && test[1] == 0 && (test[2] & 0xf0) == 0) {
				code.append(hex[((test[2] & 0x0f))]);
				foundCount++;
			}
			idx++;
		} while (foundCount != 8);
		return code.toString();
	}



	protected String runPart2(String input) {
		int foundCount = 0;
		long idx = 0;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("algorithm exception:" + e.toString());
		}

		char hex[] = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		input = input.trim();
		StringBuilder code = new StringBuilder("________");

		do {
			byte[] test = md5.digest((input + idx).getBytes());
			if (test[0] == 0 && test[1] == 0 && (test[2] & 0xf0) == 0) {
				int pos = (test[2] & 0x0f);
				if (pos < 8 && code.charAt(pos) == '_') {
					code.setCharAt(pos, hex[((test[3] & 0xf0) >> 4)]);
					foundCount++;
				}
			}
			idx++;
		} while (foundCount != 8);
		return code.toString();
	}

	public static void main(String[] args) {
		Day5 day5 = new Day5();
//		String input = day5.readFile("/Day5.input");
//		System.err.println("sectorId code:" + day5.runPart1(input));
		String input = day5.readFile("/Day5b.input");
		System.err.println("sectorId:" + day5.runPart2(input));

	}

}
