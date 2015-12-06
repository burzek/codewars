package sk.mysko.advent.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.codec.binary.Hex;

/**
 * @author boris.brinza
 */
public class Day4 extends AdventOfCodeBase {

	MessageDigest messageDigest = null;

	private byte[] getHash(String data) throws Exception {
		if (messageDigest == null) {
			messageDigest = MessageDigest.getInstance("MD5");
		}
		return messageDigest.digest(data.getBytes());
	}
	private boolean checkHash(byte[] hash, int numOfStartZeroes) {
		char[] encoded = Hex.encodeHex(hash);
		boolean isOk = true;
		for (int i = 0; i < numOfStartZeroes; i++) {
			isOk &= (encoded[i] == '0');
		}
		return isOk;
	}


	protected long runPart1(String input) {
		return findHashPart(input, 5);
	}

	protected long runPart2(String input) {
		return findHashPart(input, 6);
	}

	private long findHashPart(String input, int numOfStartZeroes) {
		try {
			int length = input.length();
			int end = (int) Math.pow(10, length);
			for (long i = 1; i < end; i++) {
				String testInput = input + String.valueOf(i);
				byte[] hash = getHash(testInput);
				if (checkHash(hash, numOfStartZeroes)) {
					return i;
				};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void main(String[] args) {
		Day4 day4 = new Day4();
		long value = day4.runPart1("ckczppom");
		System.out.println("value:" + value);
		value = day4.runPart2("ckczppom");
		System.out.println("value:" + value);

	}

}
