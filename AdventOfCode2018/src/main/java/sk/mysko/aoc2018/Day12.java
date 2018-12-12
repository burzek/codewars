package sk.mysko.aoc2018;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day12 extends AdventOfCodeBase<Long> {



	private Long runPart1(String input) {
		Cave cave = new Cave();
		cave.parse(input);
		for (long s = 0; s <= 50000000000L - 1; s++) {
			if (s % 100000 == 0) {
				System.out.println(s);
			}
			cave.next();
		}

		return cave.countLive();
	}

	private class Cave {
		private char[] plants;
		private Map<Integer, Character> growMap = new HashMap<>();


		private long countLive() {
			int mid = plants.length / 4 - 1;
			long sum = 0;
			int pos = 0;
			while (pos < mid) {
				sum -= plants[pos] == '#' ? (mid - pos) : 0;
				pos++;
			}
			while (pos < plants.length) {
				sum += plants[pos] == '#' ?  (pos - mid): 0;
				pos++;
			}
			return sum;

		}

		private void parse(String input) {
			String[] lines = input.split("\n");
			int size = lines[0].length() - 15;
			plants = new char[size * 4];
			Arrays.fill(plants, '.');

			int pos = size - 1;
			for (char c : lines[0].toCharArray()) {
				if (c == '#') {
					plants[pos++] = '#';
				} else if (c == '.') {
					plants[pos++] = '.';
				}

			}
			for (int l = 2; l < lines.length; l++) {
				String pattern = lines[l].substring(0, 5);
				growMap.put(getCode(pattern), lines[l].charAt(lines[l].length() - 1));
			}
		}


		public void next() {
			char[] newGen = new char[plants.length];
			Arrays.fill(newGen, '.');

			for (int p = 2; p <= plants.length - 3; p++) {
				StringBuilder testPattern = new StringBuilder();
				testPattern.append(plants[p - 2]).append(plants[p - 1]).append(plants[p]).append(plants[p + 1]).append(plants[p + 2]);
				char newState = growMap.getOrDefault(getCode(testPattern.toString()), '.');
				newGen[p] = newState;
			}
			plants = newGen;
		}

		private Integer getCode(String toString) {
			int mul = 10000;
			int code = 0;
			for (char c : toString.toCharArray()) {
				code += (mul * (c == '#' ? 1 : 0));
				mul /= 10;
			}
			return code;
		}

	}



	public static void main(String[] args) {
		Day12 day = new Day12();
		String input = day.readFile("/Day12.input");
		System.out.println("result:" + day.runPart1(input));
	}
}
