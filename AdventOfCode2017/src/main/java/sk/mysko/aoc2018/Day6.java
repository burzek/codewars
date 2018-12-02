package sk.mysko.aoc2018;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day6 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day6 day6 = new Day6();
		String input = day6.readFile("/Day6.input");
		System.out.println("result:" + day6.runPart1(input));
		input = day6.readFile("/Day6b.input");
		System.out.println("result:" + day6.runPart2(input));


	}

	private String getFingerprint(Integer[] b1) {
		return Arrays.stream(b1).map(String::valueOf).collect(Collectors.joining("-"));
	}
	private int findMax(Integer[] b) {
		return IntStream.range(0, b.length).reduce((i,j) -> b[i] >= b[j] ? i : j).getAsInt();
	}

	@Override
	protected Integer runPart1(String input) {
		Set<String> history = new HashSet<>();
		Integer[] memBankState = Stream.of(input.split("\t")).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[] {});
		int runs = 0;
		String h = getFingerprint(memBankState);
		while (!history.contains(h)) {
			history.add(h);
			int pos = findMax(memBankState);
			int val = memBankState[pos];
			memBankState[pos] = 0;
			while (val != 0) {
				pos = (pos + 1) % memBankState.length;
				memBankState[pos]++;
				val--;
			}
			h = getFingerprint(memBankState);
			runs++;
		}
		return runs;
	}

	@Override
	protected Integer runPart2(String input) {
		List<String> history = new LinkedList<>();
		Integer[] memBankState = Stream.of(input.split("\t")).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[] {});
		String h = getFingerprint(memBankState);
		while (!history.contains(h)) {
			history.add(h);
			int pos = findMax(memBankState);
			int val = memBankState[pos];
			memBankState[pos] = 0;
			while (val != 0) {
				pos = (pos + 1) % memBankState.length;
				memBankState[pos]++;
				val--;
			}
			h = getFingerprint(memBankState);
		}
		return history.size() - history.indexOf(h);
	}
}
