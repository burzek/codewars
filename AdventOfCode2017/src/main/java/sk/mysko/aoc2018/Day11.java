package sk.mysko.aoc2018;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day11 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day11 day11 = new Day11();
		String input = day11.readFile("/Day11.input");
		System.out.println("result:" + day11.runPart1(input));
		input = day11.readFile("/Day11b.input");
		System.out.println("result:" + day11.runPart2(input));


	}



	@Override
	protected Integer runPart1(String input) {
		int[] out = parseInput(input);
		return getManhattan(out[0], out[1]);

	}

	@Override
	protected Integer runPart2(String input) {
		int[] out = parseInput(input);
		return out[2];
	}

	private int getManhattan(int x, int y) {
		int path = 0;
		if (Math.abs(x) > Math.abs(y)) {
			path += Math.abs(x) - Math.abs(y);
		}
		path += Math.abs(y);
		return path;
	}

	private int[] parseInput(String input) {
		int x = 0;
		int y = 0;
		int max = 0;
		for (String s : input.split(",")) {
			switch (s) {
				case "n":
					y--;
					break;
				case "nw":
					y--;
					x--;
					break;
				case "ne":
					y--;
					x++;
					break;
				case "s":
					y++;
					break;
				case "sw":
					y++;
					x--;
					break;
				case "se":
					y++;
					x++;
			}
			if (getManhattan(x, y) > max) {
				max = getManhattan(x, y);
			}
		}
		return new int[] {x, y, max};
	}



}
