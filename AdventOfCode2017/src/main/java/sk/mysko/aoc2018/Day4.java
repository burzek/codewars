package sk.mysko.aoc2018;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day4 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day4 day4 = new Day4();
		String input = day4.readFile("/Day4.input");
		System.out.println("result:" + day4.runPart1(input));
		input = day4.readFile("/Day4b.input");
		System.out.println("result:" + day4.runPart2(input));


	}


	@Override
	protected Integer runPart1(String input) {
		Set<String> set = new HashSet<>();
		String line[] = input.split("\n");
		int count = 0;
		for (String s : line) {
			boolean ok = true;
			String[] word = s.split(" ");
			for (String w : word) {
				if (set.contains(w)) {
					ok = false;
					break;
				} else {
					set.add(w);
				}
			}
			count += (ok ? 1 : 0);
			set.clear();
		}

		return count;
	}

	@Override
	protected Integer runPart2(String input) {
		Set<String> set = new HashSet<>();
		String line[] = input.split("\n");
		int count = 0;
		for (String s : line) {
			boolean ok = true;
			String[] word = s.split(" ");
			for (String w : word) {
				String wsorted = w.chars().sorted().mapToObj(String::valueOf).collect(Collectors.joining());
				if (set.contains(wsorted)) {
					ok = false;
					break;
				} else {
					set.add(wsorted);
				}
			}
			count += (ok ? 1 : 0);
			set.clear();
		}

		return count;
	}
}
