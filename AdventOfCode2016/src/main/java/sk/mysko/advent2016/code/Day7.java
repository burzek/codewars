package sk.mysko.advent2016.code;

/**
 * @author boris.brinza
 */
public class Day7 extends AdventOfCodeBase<Integer> {

	private static final int MAX_ROWS = 6;
	private static final int MAX_COLS = 50;

	private void doRect(String rectData, char[][] lcd) {
		String[] size = rectData.split("x");
		int wide = Integer.parseInt(size[0].trim());
		int tall = Integer.parseInt(size[1].trim());
		for (int row = 0; row < tall; row++) {
			for (int col = 0; col < wide; col++) {
				lcd[row][col] = '#';
			}
		}
	}


	private void doRotateColumn(String rectData, char[][] lcd) {
		String[] size = rectData.split("by");
		int col = Integer.parseInt(size[0].trim());
		int by = Integer.parseInt(size[1].trim());
		for (int i = 0; i < by; i++) {
			char lastRowValue = lcd[MAX_ROWS - 1][col];
			for (int j = MAX_ROWS - 1; j > 0; j--) {
				lcd[j][col] = lcd[j - 1][col];
			}
			lcd[0][col] = lastRowValue;
		}
	}

	private void doRotateRow(String rectData, char[][] lcd) {
		String[] size = rectData.split("by");
		int row = Integer.parseInt(size[0].trim());
		int by = Integer.parseInt(size[1].trim());
		for (int i = 0; i < by; i++) {
			char lastColValue = lcd[row][MAX_COLS - 1];
			for (int j = MAX_COLS - 1; j > 0; j--) {
				lcd[row][j] = lcd[row][j - 1];
			}
			lcd[row][0] = lastColValue;
		}
	}

	private void printLcd(char[][] lcd) {
		for (int row = 0; row < MAX_ROWS; row++) {
			for (int col = 0; col < MAX_COLS; col++) {
				System.out.print(lcd[row][col] == '#' ? '#' : '.');
			}
			System.out.println();
		}
		System.out.println();
	}

	private void initLcd(char[][] lcd) {
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				lcd[i][j] = '.';
			}
		}
	}

	protected Integer runPart1(String input) {
		String lines[] = input.split("\\r?\\n");
		char[][] lcd = new char[MAX_ROWS][MAX_COLS];
		initLcd(lcd);
		for (String line : lines) {
			if (line.startsWith("rect")) {
				doRect(line.substring(5), lcd);
			} else if (line.startsWith("rotate column x=")) {
				doRotateColumn(line.substring(16), lcd);
			} else if (line.startsWith("rotate row y=")) {
				doRotateRow(line.substring(13), lcd);
			}
		}

		int count = 0;
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				count += lcd[i][j] == '#' ? 1 : 0;
			}
		}

		return count;
	}



	protected Integer runPart2(String input) {
		String lines[] = input.split("\\r?\\n");
		char[][] lcd = new char[MAX_ROWS][MAX_COLS];
		initLcd(lcd);
		for (String line : lines) {
			if (line.startsWith("rect")) {
				doRect(line.substring(5), lcd);
			} else if (line.startsWith("rotate column x=")) {
				doRotateColumn(line.substring(16), lcd);
			} else if (line.startsWith("rotate row y=")) {
				doRotateRow(line.substring(13), lcd);
			}
		}

		printLcd(lcd);
		return 0;
	}

	public static void main(String[] args) {
		Day7 day7 = new Day7();
		String input = day7.readFile("/Day7.input");
		System.err.println("lit#:" + day7.runPart1(input));
		input = day7.readFile("/Day7b.input");
		day7.runPart2(input);

	}

}
