package sk.mysko.advent2016.code;

/**
 * @author boris.brinza
 */
public class Day2 extends AdventOfCodeBase<String> {


	protected String runPart1(String input) {
		String lines[] = input.split("\\r?\\n");
		String code = "";

		int x = 1;
		int y = 1;
		for (String line : lines) {
			for (char c : line.toCharArray()) {
				switch (c) {
					case 'L':
						x -= 1;
						break;
					case 'R':
						x += 1;
						break;
					case 'U':
						y -= 1;
						break;
					case 'D':
						y += 1;
						break;
				}
				x = x < 0 ? 0 : x;
				x = x > 2 ? 2 : x;
				y = y < 0 ? 0 : y;
				y = y > 2 ? 2 : y;
			}
			code += y * 3 + (x + 1);
		}
		return code;
	}

	protected String runPart2(String input) {
		String lines[] = input.split("\\r?\\n");
		String code = "";

		char[][] map = new char[][] {
				{'0', '0', '0', '0', '0', '0', '0'},
				{'0', '0', '0', '1', '0', '0', '0'},
				{'0', '0', '2', '3', '4', '0', '0'},
				{'0', '5', '6', '7', '8', '9', '0'},
				{'0', '0', 'A', 'B', 'C', '0', '0'},
				{'0', '0', '0', 'D', '0', '0', '0'},
				{'0', '0', '0', '0', '0', '0', '0'},
		};
		int x = 1;
		int y = 3;
		for (String line : lines) {
			for (char c : line.toCharArray()) {
				switch (c) {
					case 'L':
						x -= map[x - 1][y] != '0' ? 1 : 0;
						break;
					case 'R':
						x += map[x + 1][y] != '0' ? 1 : 0;
						break;
					case 'U':
						y -= map[x][y - 1] != '0' ? 1 : 0;
						break;
					case 'D':
						y += map[x][y + 1] != '0' ? 1 : 0;
						break;
				}
			}
			code += map[y][x];
		}
		return code;
	}

	public static void main(String[] args) {
		Day2 day2 = new Day2();
		String input = day2.readFile("/Day2.input");
		System.err.println("code 1:" + day2.runPart1(input));
		input = day2.readFile("/Day2b.input");
		System.err.println("code 2:" + day2.runPart2(input));

	}

}
