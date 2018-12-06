package sk.mysko.aoc2018;

import java.util.Arrays;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day5 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day5 day5 = new Day5();
		String input = day5.readFile("/Day5.input");
		System.out.println("result:" + day5.runPart1(input));
		System.out.println("result:" + day5.runPart2(input));
	}


	protected Integer runPart1(String input) {
		String finalPolymer = doReaction(input);
		return finalPolymer.length();
	}

	protected Integer runPart2(String input) {
		int minLength = Integer.MAX_VALUE;
		for (char c = 'a'; c <= 'z'; c++) {
			String improvedInput = input.replaceAll(String.valueOf(c), "").replaceAll(String.valueOf(c).toUpperCase(), "");
			int len = doReaction(improvedInput).length();
			minLength = len < minLength ? len : minLength;
		}
		return minLength;
	}


	private String doReaction(String polymer) {
		String newPolymer =
			Arrays.stream(polymer.split(""))
				.reduce("", (s, s2) -> {
					if (s.length() != 0) {
						char p1 = s.charAt(s.length() - 1);
						char p2 = s2.charAt(0);
						boolean sameUnit = Character.toLowerCase(p1) == Character.toLowerCase(p2);
						boolean sameCase = Character.isLowerCase(p1) == Character.isLowerCase(p2);
						if (sameUnit && !sameCase) {
							return s.substring(0, s.length() - 1);	//units are destroyed
						} else {
							return s + p2;	//add unit to polymer
						}
					} else {
						return s2;	//start of reaction, add unit to polymer
					}
				});
		if (newPolymer.length() == polymer.length()) {
			return newPolymer;
		} else {
			return doReaction(newPolymer);
		}
	}

}
