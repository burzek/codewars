package sk.mysko.aoc2017;

import java.util.Arrays;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day1 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day1 day1 = new Day1();
		String input = day1.readFile("/Day1.input");
//		System.out.println("result:" + day1.runPart1(input));
		System.out.println("result:" + day1.runPart1(input));


	}

	@Override
	protected Integer runPart1(String input) {
		return Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).sum();
	}

	@Override
	protected Integer runPart2(String input) {
		return 0;
	}
}
