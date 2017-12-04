package sk.mysko.aoc2017;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day3 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day3 day3 = new Day3();
		System.out.println("result:" + day3.runPart1(null));
		System.out.println("result:" + day3.runPart2(null));


	}

	enum Direction{
		RIGHT, UP, LEFT, DOWN;
	}

	private String[][] genUlam(int n, int i){
		String[][] spiral = new String[n][n];
		Direction dir = Direction.RIGHT;
		int j = i;
		int y = n / 2;
		int x = (n % 2 == 0) ? y - 1 : y; //shift left for even n's
		while(j <= ((n * n) - 1 + i)){
			spiral[y][x] = String.valueOf(j);
			if (j == 289326) {
				System.out.println(y + "/" + x);
				return null;
			}
			switch(dir){
				case RIGHT:
					if(x <= (n - 1) && spiral[y - 1][x] == null && j > i) dir = Direction.UP; break;
				case UP:
					if(spiral[y][x - 1] == null) dir = Direction.LEFT; break;
				case LEFT:
					if(x == 0 || spiral[y + 1][x] == null) dir = Direction.DOWN; break;
				case DOWN:
					if(spiral[y][x + 1] == null) dir = Direction.RIGHT; break;
			}

			switch(dir){
				case RIGHT:	x++; break;
				case UP: 	y--; break;
				case LEFT:	x--; break;
				case DOWN:	y++; break;
			}
			j++;
		}
		return spiral;
	}

	@Override
	protected Integer runPart1(String input) {
		genUlam(25000, 25000);
		return 0;
	}

	@Override
	protected Integer runPart2(String input) {
		return null;
	}
}
