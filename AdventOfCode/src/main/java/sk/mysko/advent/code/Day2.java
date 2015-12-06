package sk.mysko.advent.code;

import java.util.Arrays;

/**
 * @author boris.brinza
 */
public class Day2 extends AdventOfCodeBase{


	protected  long runPart1(String input) {
		long total = 0;

		for (String line : input.split("(\\n)+")) {
			String[] values = line.split("x");
			int [] dimensions = new int[3];
			dimensions[0] = Integer.parseInt(values[0]);
			dimensions[1] = Integer.parseInt(values[1]);
			dimensions[2] = Integer.parseInt(values[2]);
			int[] areas = new int[3];
			areas[0] = dimensions[0] * dimensions[1];
			areas[1] = dimensions[0] * dimensions[2];
			areas[2] = dimensions[1] * dimensions[2];

			total += (2 * areas[0]) + (2 * areas[1]) + (2 * areas[2]);
			total += (areas[0] < areas[1] ? (areas[0] < areas[2] ? areas[0] : areas[2]) : (areas[1] < areas[2] ? areas[1] : areas[2]));

		}
		return total;
	}

	protected long runPart2(String input) {
		long totalRibbon = 0;
		for (String line : input.split("(\\n)+")) {
			String[] values = line.split("x");
			int [] dimensions = new int[3];
			dimensions[0] = Integer.parseInt(values[0]);
			dimensions[1] = Integer.parseInt(values[1]);
			dimensions[2] = Integer.parseInt(values[2]);
			Arrays.sort(dimensions);
			totalRibbon += 2 * dimensions[0] + 2 * dimensions[1];
			totalRibbon += dimensions[0] * dimensions[1] * dimensions[2];

		}
		return totalRibbon;
	}

	public static void main(String[] args) {
		Day2 day2 = new Day2();
		String input = day2.readFile("/Day2.input");
		long area = day2.runPart1(input);
		System.out.println("total feet:" + area);

		long ribbon = day2.runPart2(input);
		System.out.println("total ribbon:" + ribbon);

	}

}
