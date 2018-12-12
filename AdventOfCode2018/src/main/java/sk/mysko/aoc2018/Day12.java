package sk.mysko.aoc2018;

import java.util.HashMap;
import java.util.Map;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day12 extends AdventOfCodeBase<Integer> {



	private Integer runPart1(String input) {
		boolean[] plants = parse(input);
		return 0;
	}

	private class Plants {
		private boolean[] plants;
		private Map<Integer, Boolean> growMap = new HashMap<>();
	}

	private Plants parse(String input) {
		Plants p = new Plants();
		String[] lines = input.split("\n");
		int size = lines[0].length() - 15;
		p.plants = new boolean[size * 2];
		int pos = size / 2;
		for (char c : lines[0].toCharArray()) {
			if (c == '#') {
				p.plants[pos] = true;
			}
			pos++;
		}
		for (int l = 2; l < lines.length; l++) {
			String from = lines[l].substring(0, 5);
			int fromN = 0;
			int mul = 10000;
			for (char s : from.toCharArray()) {
				fromN += (((s == '#') ? 1 : 0) * mul);
			}
			p.growMap.put(fromN, lines[l].charAt(lines[l].length() - 1) == '#');
		}
		return p;
	}

	public static void main(String[] args) {
		Day12 day = new Day12();
		String input = day.readFile("/Day12.input");
		System.out.println("result:" + day.runPart1(input));
	}
}
