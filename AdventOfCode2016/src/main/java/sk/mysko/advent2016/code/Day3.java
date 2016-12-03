package sk.mysko.advent2016.code;

import java.util.Arrays;

/**
 * @author boris.brinza
 */
public class Day3 extends AdventOfCodeBase<Integer> {


	protected Integer runPart1(String input) {
		String lines[] = input.split("\\r?\\n");
		int validCount = 0;
		for (String line : lines) {
			String[] lenghts = line.split("[ ]+");
			long[] sides = new long[] {Long.valueOf(lenghts[1]), Long.valueOf(lenghts[2]), Long.valueOf(lenghts[3])};
			Arrays.sort(sides);
			validCount += sides[0] + sides[1] > sides[2] ? 1 : 0;
		}
		return validCount;
	}

	protected Integer runPart2(String input) {
		String lines[] = input.split("\\r?\\n");
		int validCount = 0;
		for (int i = 0; i < lines.length; i+= 3) {
			String[][] test = new String[3][];
			test[0] = lines[i].split("[ ]+");
			test[1] = lines[i + 1].split("[ ]+");
			test[2] = lines[i + 2].split("[ ]+");

			for (int t = 1; t < 4; t++) {
				long[] sides = new long[] { Long.valueOf(test[0][t]), Long.valueOf(test[1][t]), Long.valueOf(test[2][t]) };
				Arrays.sort(sides);
				validCount += sides[0] + sides[1] > sides[2] ? 1 : 0;
			}
		}
		return validCount;
	}

	public static void main(String[] args) {
		Day3 day3 = new Day3();
		String input = day3.readFile("/Day3.input");
		System.err.println("possible triangels:" + day3.runPart1(input));
		input = day3.readFile("/Day3b.input");
		System.err.println("possible triangles b):" + day3.runPart2(input));

	}

}
