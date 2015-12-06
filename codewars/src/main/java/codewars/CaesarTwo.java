package sk.mysko.codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CaesarTwo {


	public static List<String> encodeStr(String s, int shift) {
		char firstCharLower = Character.toLowerCase(s.charAt(0));
		StringBuilder encoded = new StringBuilder().
				append(firstCharLower).
				append((char) (firstCharLower + shift));
		for (char c : s.toCharArray()) {
			if (!Character.isLetter(c)) {
				encoded = encoded.append(c);
			} else {
				char newChar = (char) (c + shift);
				if (Character.isUpperCase(c) && newChar > 'Z') {
					newChar = (char) (newChar - 'Z');
				} else if (Character.isLowerCase(c) && newChar > 'z') {
					newChar = (char) (newChar - 'z');
				}
				encoded = encoded.append(newChar);
			}
		}

		int split = encoded.length() / 5;
		int reminder = encoded.length() - split * 4;
		if (reminder > split) {
			split += 1;
			reminder -= 4;
		}
		List<String> retList = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			retList.add(encoded.substring(i * split, (i + 1) * split));
		}
		if (reminder != 0) {
			retList.add(encoded.substring(encoded.length() - reminder));
		}


		return retList;
	}

	public static String decode(List<String> s) {
		StringBuilder wholeMessage = new StringBuilder();
		Stream.of(s.toArray()).forEach(x -> wholeMessage.append(x));
		int shift = wholeMessage.charAt(1) - wholeMessage.charAt(0);

		for (int i = 2; i < wholeMessage.length(); i++) {
			char c = wholeMessage.charAt(i);
			if (Character.isLetter(c)) {
				char newChar = (char) (c - shift);
				if (Character.isUpperCase(c) && newChar < 'A') {
					newChar = (char) (newChar + 'A');
				} else if (Character.isLowerCase(c) && newChar < 'a') {
					newChar = (char) (newChar + 'a');
				}
				wholeMessage.setCharAt(i, newChar);
			}
		}

		return wholeMessage.substring(2).toString();
	}

}
