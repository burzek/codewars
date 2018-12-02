package sk.mysko.aoc2018;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day5 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day5 day5 = new Day5();
		String input = day5.readFile("/Day5.input");
		System.out.println("result:" + day5.runPart1(input));
		input = day5.readFile("/Day5b.input");
		System.out.println("result:" + day5.runPart2(input));


	}


	@Override
	protected Integer runPart1(String input) {
		Integer[] jumps = Stream.of(input.split("\n")).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[] {});
		int pos = 0;
		int c = 0;
		while (pos >= 0 && pos < jumps.length) {
			int offset = jumps[pos];
			jumps[pos]++;
			pos += offset;
			c++;
		}
		return c;
	}

	@Override
	protected Integer runPart2(String input) {
		Integer[] jumps = Stream.of(input.split("\n")).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[] {});
		int pos = 0;
		int c = 0;
		while (pos >= 0 && pos < jumps.length) {
			int offset = jumps[pos];
			jumps[pos] = jumps[pos] + (offset >= 3 ? -1 : 1);
			pos += offset;
			c++;
		}
		return c;
	}
}
