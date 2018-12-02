package sk.mysko.aoc2017;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day2 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day2 day2 = new Day2();
		String input = day2.readFile("/Day2.input");
		System.out.println("result:" + day2.runPart1(input));
		input = day2.readFile("/Day2.input");
		System.out.println("result:" + day2.runPart2(input));


	}

	@Override
	protected Integer runPart1(String input) {
		int sum = 0;
		String[] lines = input.split("\n");
		for (String line : lines) {
			IntSummaryStatistics s = Stream.of(line.split("\t")).collect(Collectors.summarizingInt(Integer::parseInt));
			sum += (s.getMax() - s.getMin());
		}
		return sum;
	}

	@Override
	protected Integer runPart2(String input) {
		int sum = 0;
		String[] lines = input.split("\n");
		for (String line : lines) {
			List<Integer> list = Stream.of(line.split("\t")).map(Integer::parseInt).sorted().collect(Collectors.toList());
			for (int i = list.size() - 1; i > 1; i--) {
				for (int j = i - 1; j > 0; j--) {
					if (list.get(i) % list.get(j) == 0) {
						sum += list.get(i) / list.get(j);
						break;
					}
				}
			}
		}
		return sum;
	}
}
