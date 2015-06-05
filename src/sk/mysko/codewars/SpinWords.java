package sk.mysko.codewars;

import static org.junit.Assert.assertEquals;

public class SpinWords {

	public String spinWords(String sentence) {
		String[] words = sentence.split(" ");
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() >= 5) {
				words[i] = new StringBuilder(words[i]).reverse().toString();
			}
		}

		return String.join(" ", words);
	}

	public static void main(String[] args) {
		assertEquals("emocleW", new SpinWords().spinWords("Welcome"));
		assertEquals("Hey wollef sroirraw", new SpinWords().spinWords("Hey fellow warriors"));
	}

}