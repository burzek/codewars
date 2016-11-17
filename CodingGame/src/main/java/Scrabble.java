import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author boris.brinza
 */
public class Scrabble extends CodingGameBase {

	public Scrabble(String inputFile) {
		super(inputFile);
	}

	@Override
	protected void doTask(String input) {
		Scanner in = new Scanner(new ByteArrayInputStream(input.getBytes()));
		int N = in.nextInt();
		in.nextLine();
		List<String> words = new ArrayList<>();
		List<Map<String, Long>> wordsMap = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String W = in.nextLine();
			wordsMap.add(Arrays.stream(W.split("")).
					collect(Collectors.groupingBy(c -> c, Collectors.counting())));
			words.add(W);
		}

		String LETTERS = in.nextLine();
		Map<String, Long> lettersMap = Arrays.stream(LETTERS.split("")).
				collect(Collectors.groupingBy(c -> c, Collectors.counting()));

		long maxLen = 0;
		int maxPos = 0;
		int currentPos = 0;
		for(Map<String, Long> m : wordsMap) {
			if (m.keySet().stream().allMatch(l -> lettersMap.containsKey(l) && m.get(l) <= lettersMap.get(l))) {
				long wordLen = getWordValue(m);
				if (wordLen > maxLen) {
					maxLen = wordLen;
					maxPos = currentPos;
				}
			}
			currentPos++;
		}

		System.out.println(words.get(maxPos));

	}

	private int getValue(char c) {
		if ("eaionrtlsu".indexOf(c) != -1) {
			return 1;
		} else if (c == 'd' || c == 'g') {
			return 2;
		} else if ("bcmp".indexOf(c) != -1) {
			return 3;
		} else if ("fhvwy".indexOf(c) != -1) {
			return 4;
		} else if (c == 'k') {
			return 5;
		} else if (c == 'j' || c == 'x') {
			return 8;
		} else if (c == 'q' || c == 'z') {
			return 10;
		}
		return 0;
	}

	private long getWordValue(Map<String, Long> word) {
		return word.keySet().stream().map(x -> getValue(x.charAt(0)) * word.get(x)).reduce((v1, v2) -> v1 + v2).get();
	}

	public static void main(String args[]) {
		new Scrabble("Scrabble1.txt").run();
	}
}
