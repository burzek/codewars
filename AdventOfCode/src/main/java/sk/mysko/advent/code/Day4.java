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
public class Day4 {

	MessageDigest messageDigest = null;

	private byte[] getHash(String data) throws Exception {
		if (messageDigest == null) {
			messageDigest = MessageDigest.getInstance("MD5");
		}
		return messageDigest.digest(data.getBytes());
	}
	private boolean checkHash(byte[] hash, int number) {
		char[] encoded = Hex.encodeHex(hash);
		if (encoded [0] == '0' && encoded [1] == '0' && encoded [2] == '0' && encoded [3] == '0' && encoded [4] == '0' && encoded [5] == '0') {
			System.out.println("i/hash:" + number + ":" + new String(encoded));
			return true;
		}
		return false;
	}


	private int runPart1(String input) {
		try {
			int length = input.length();
			int end = (int) Math.pow(10, length);
			for (int i = 1; i < end; i++) {
				String testInput = input + String.valueOf(i);
				byte[] hash = getHash(testInput);
				if (checkHash(hash, i)) {
					return i;
				};
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}



	private long runPart2(String input) {
		return 0;
	}

	public static void main(String[] args) {
		Day4 day4 = new Day4();
		int value = day4.runPart1("ckczppom");
		System.out.println("hash:" + value);

	}

}
