package sk.mysko.aoc2019;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day3 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day3 day1 = new Day3();
		String input = day1.readFile("/Day3.input");
		System.out.println("result:" + day1.runPart1(input));
		System.out.println("result:" + day1.runPart2(input));


	}

	protected Integer runPart1(String input) {
		Set<String> wire1 = new LinkedHashSet<>();

		String[] wires = input.split("\n");

		int x = 0;
		int y = 0;
		for (String linePart : wires[0].split(",")) {
			char dir = linePart.charAt(0);
			int steps = Integer.parseInt(linePart.substring(1));
			for (int i = 0; i < steps; i++) {
				switch (dir) {
					case 'L':
						x--;break;
					case 'R':
						x++;break;
					case 'U':
						y--;break;
					case 'D':
						y++;break;
				}
				wire1.add(x + ":" + y);

			}
		}


		x = 0;
		y = 0;
		int minDistance = Integer.MAX_VALUE;
		for (String linePart : wires[1].split(",")) {
			char dir = linePart.charAt(0);
			int steps = Integer.parseInt(linePart.substring(1));
			for (int i = 0; i < steps; i++) {
				switch (dir) {
					case 'L':
						x--;break;
					case 'R':
						x++;break;
					case 'U':
						y--;break;
					case 'D':
						y++;break;
				}
				if (wire1.contains(x + ":" + y)) {
					int distance = Math.abs(x) + Math.abs(y);
					if (distance > 0) {
						minDistance = Math.min(minDistance, distance);
					}
				}


			}
		}
		return minDistance;

	}

	protected Integer runPart2(String input) {
		return 0;
	}
}
