package sk.mysko.aoc2018;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day1 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day1 day1 = new Day1();
		String input = day1.readFile("/Day1.input");
		System.out.println("result:" + day1.runPart1(input));
		System.out.println("result:" + day1.runPart2(input));


	}

	protected Integer runPart1(String input) {
		return Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).sum();
	}

	protected Integer runPart2(String input) {
		Set<Integer> frequencies = new HashSet<>();
		int sum = 0;
		int f[] = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();
		int pos = 0;
		do {
			sum += f[pos++];
			if (frequencies.contains(sum)) {
				break;
			}
			frequencies.add(sum);
			pos = pos % f.length;
		} while (true);
		return sum;
	}
}
