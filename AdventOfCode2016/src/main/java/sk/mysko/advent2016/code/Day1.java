package sk.mysko.advent2016.code;

/**
 * @author boris.brinza
 */
public class Day1 extends AdventOfCodeBase {


	protected long runPart1(String input) {
		int dir = 0;
		int x = 0;
		int y = 0;
		for(String step : input.split(",")) {
			step = step.trim();
			dir = 'R' == step.charAt(0) ? dir + 1 : dir - 1;
			if (dir < 0) {
				dir = 3;
			} else {
				dir %= 4;
			}
			int distance = Integer.valueOf(step.substring(1));
			if (dir == 0 || dir == 2) {
				y = dir == 0 ? y + distance : y - distance;
			}
			if (dir == 1 || dir == 3) {
				x = dir == 1 ? x + distance : x - distance;
			}
		}
		return Math.abs(x) + Math.abs(y);
	}

	protected long runPart2(String input) {
		int dir = 0;
		int x = 250;
		int y = 250;
		boolean[][] visited = new boolean[500][500];

		int counter = 0;
		for(String step : input.split(",")) {

			step = step.trim();
			dir = 'R' == step.charAt(0) ? dir + 1 : dir - 1;
			if (dir < 0) {
				dir = 3;
			} else {
				dir %= 4;
			}
			int distance = Integer.valueOf(step.substring(1));

			if (dir == 0) {
				for (int i = 1; i <= distance; i++) {
					if (visited[x][y + i]) {
						return Math.abs(x - 250) + Math.abs(y + i - 250);
					}
					visited[x][y + i] = true;
				}
				y += distance;
			}
			if (dir == 1) {
				for (int i = 1; i <= distance; i++) {
					if (visited[x + i][y]) {
						return Math.abs(x + i - 250) + Math.abs(y - 250);
					}
					visited[x + i][y] = true;
				}
				x += distance;
			}
			if (dir == 2) {
				for (int i = 1; i <= distance; i++) {
					if (visited[x][y - i]) {
						return Math.abs(x - 250) + Math.abs(y - i - 250);
					}
					visited[x][y - i] = true;
				}
				y -= distance;
			}
			if (dir == 3) {
				for (int i = 1; i <= distance; i++) {
					if (visited[x - i][y]) {
						return Math.abs(x - i - 250) + Math.abs(y - 250);
					}
					visited[x - i][y] = true;
				}
				x -= distance;
			}

		}
		return Math.abs(x) + Math.abs(y);
	}

	public static void main(String[] args) {
		Day1 day1 = new Day1();
		String input = day1.readFile("/Day1.input");
		System.err.println("distance:" + day1.runPart1(input));
		input = day1.readFile("/Day1b.input");
		System.err.println("distance to location visited twice:" + day1.runPart2(input));

	}

}
