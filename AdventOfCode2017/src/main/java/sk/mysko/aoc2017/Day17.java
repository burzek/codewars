package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day17 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day17 day17 = new Day17();
		System.out.println("result:" + day17.runPart1("394"));
		System.out.println("result:" + day17.runPart2("394"));

	}

	@Override
	protected Integer runPart1(String input) {
		int step = Integer.parseInt(input);
		List<Integer> buffer = new ArrayList<>();
		buffer.add(0);
		int pos = 0;
		for (int i = 1; i <= 2017; i++) {
			pos = (pos + step) % buffer.size();
			buffer.add(++pos, i);
		}
		return buffer.get(pos + 1 == buffer.size() ? 0 : pos + 1);
	}


	@Override
	protected Integer runPart2(String input) {
		int step = Integer.parseInt(input);
		int zeroPos = 0;
		int pos = 0;
		int afterZeroVal = 0;
		for (int i = 1; i <= 50_000_000; i++) {
			pos = (pos + step) % i;
			if (pos == zeroPos) {
				afterZeroVal = i;
			}
			if (pos < zeroPos) {
				zeroPos++;
			}
			pos++;

		}
		return afterZeroVal;
	}




}
