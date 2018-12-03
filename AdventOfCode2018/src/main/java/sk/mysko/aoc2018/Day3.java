package sk.mysko.aoc2018;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day2 extends AdventOfCodeBase<Object> {

	public static void main(String[] args) {
		Day2 day2 = new Day2();
		String input = day2.readFile("/Day2.input");
		System.out.println("result:" + day2.runPart1(input));
		System.out.println("result:" + day2.runPart2(input));


	}

	@Override
	protected Object runPart1(String input) {
		String[] lines = input.split("\n");
		int sum2 = 0;
		int sum3 = 0;
		for (String line : lines) {
			Map<String, Long> counts =
				Arrays
				.stream(line.split(""))
				.collect(Collectors.groupingBy(x -> x, Collectors.counting()));
			sum2 += counts.entrySet().stream().map(Entry::getValue).anyMatch(e -> e == 2) ? 1 : 0;
			sum3 += counts.entrySet().stream().map(Entry::getValue).anyMatch(e -> e == 3) ? 1 : 0;
		}
		return sum2 * sum3;
	}

	@Override
	protected Object runPart2(String input) {
		String[] lines = input.split("\n");
		for (int i = 0; i < lines.length - 1; i++) {
			for (int j = i + 1; j < lines.length; j++) {
				int diff = getDiff(lines[i], lines[j]);
				if (diff == 1) {
					return getCommon(lines[i], lines[j]);
				}
			}
		}
		return "???";

	}

	private String getCommon(String line1, String line2) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < line1.length(); i++) {
			if (line1.charAt(i) != line2.charAt(i)) {
				continue;
			}
			s.append(line1.charAt(i));
		}
		return s.toString();
	}

	private int getDiff(String s1, String s2) {
		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			diff += (s1.charAt(i) == s2.charAt(i) ? 0 : 1);
		}
		return diff;
	}
}
