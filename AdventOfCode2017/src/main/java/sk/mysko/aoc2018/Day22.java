package sk.mysko.aoc2018;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day22 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day22 day22 = new Day22();
		String input = day22.readFile("/Day22.input");
		System.out.println(day22.runPart1(input));
		System.out.println(day22.runPart2(input));
	}


	private byte[][] parseInput(String input, byte infectedValue) {
		byte[][] maze = new byte[10000][10000];
		int y = 5000 - 12;
		int x = 5000 - 12;
		for (String line: input.split("\n")) {
			for (int i = 0; i < line.length(); i++) {
				maze[x][y+i] = line.charAt(i) == '.' ? (byte) 0 : infectedValue;
			}
			x++;
		}
		return maze;
	}


	@Override
	protected Integer runPart1(String input) {
		byte[][] maze = parseInput(input, (byte) 1);
		int x = 5000;
		int y = 5000;
		int dir = 0;		//0 up 1 right 2 down 3 left
		int infectedCount = maze[x][y] == 1 ? -1 : 0;
		for (int i = 0; i < 10_000; i++) {
			dir += maze[x][y] == 1 ? 1 : -1;
			if (dir < 0) {
				dir = 3;
			} else if (dir > 3) {
				dir = 0;
			}

			maze[x][y] = (byte) (1 - maze[x][y]);
			infectedCount += maze[x][y];
			switch (dir) {
				case 0:
					x--;
					break;
				case 1:
					y++;
					break;
				case 2:
					x++;
					break;
				case 3:
					y--;
					break;
			}
		}
		return infectedCount;
	}


	//0 - clean
	//1 - weakened
	//2 - infected
	//3 - flagged
	@Override
	protected Integer runPart2(String input) {
//		byte[][] maze = parseInput(input, (byte) 2);
//		int x = 5000;
//		int y = 5000;
//		int dir = 0;		//0 up 1 right 2 down 3 left
//		int infectedCount = maze[x][y] == 1 ? -1 : 0;
//		for (int i = 0; i < 10_000_000; i++) {
//			switch (maze[x][y]) {
//				case 0:
//					dir--;
//					break;
//				case 2:
//					dir++;
//					break;
//				case 4:
//					dir += 2;
//					break;
//			}
//
//			if (dir < 0) {
//				dir = 3;
//			} else if (dir > 3) {
//				dir -= 3;
//			}
//
//			maze[x][y] = (byte) ((maze[x][y] + 1) % 3);
//			infectedCount += maze[x][y];
//			switch (dir) {
//				case 0:
//					x--;
//					break;
//				case 1:
//					y++;
//					break;
//				case 2:
//					x++;
//					break;
//				case 3:
//					y--;
//					break;
//			}
//		}
//		return infectedCount;
		return 0;
	}




}
