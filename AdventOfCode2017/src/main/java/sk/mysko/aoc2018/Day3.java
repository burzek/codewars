package sk.mysko.aoc2018;

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

	private void genUlam(){
		Direction dir = Direction.DOWN;
		int count = 1;
		int step = 0;
		int x = 0;
		int y = 0;
		boolean repeat = true;
		while (repeat) {

			switch (dir) {
				case RIGHT:
					dir = Direction.UP;
					break;
				case UP:
					dir = Direction.LEFT;
					break;
				case LEFT:
					dir = Direction.DOWN;
					break;
				case DOWN:
					dir = Direction.RIGHT;
					break;
			}



			if (dir == Direction.LEFT || dir == Direction.RIGHT) {
				step += 1;
			}


			if (count + step > 289326) {
				step -= (count + step) - 289326;
				repeat = false;
			}


			switch (dir){
				case RIGHT:	x += step; break;
				case UP: 	y -= step; break;
				case LEFT:	x -= step; break;
				case DOWN:	y += step; break;
			}
			count += step;
		}

		System.err.println(x + " " + y);

	}

	@Override
	protected Integer runPart1(String input) {
		genUlam();
		return 0;
	}

	@Override
	protected Integer runPart2(String input) {
		return null;
	}
}
