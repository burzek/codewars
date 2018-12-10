package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day10 extends AdventOfCodeBase<String> {

	private class Star {
		private int x;
		private int y;
		private int vx;
		private int vy;

		public Star(int x, int y, int vx, int vy) {
			this.x = x;
			this.y = y;
			this.vx = vx;
			this.vy = vy;
		}

		public void move() {
			this.x = this.x + this.vx;
			this.y = this.y + this.vy;
		}
	}

	@Override
	protected String runPart1(String input) {
		String[] entries = input.split("\n");
		List<Star> stars = new ArrayList();

		for (String entry : entries) {
			Integer x = Integer.parseInt(entry.substring(10, entry.indexOf(',', 10)).trim());
			Integer y = Integer.parseInt(entry.substring(entry.indexOf(',', 10)+1, entry.indexOf('>', 10)).trim());
			int pos = entry.indexOf("velocity");
			Integer vx = Integer.parseInt(entry.substring(entry.indexOf('<', pos) + 1, entry.indexOf(',', pos)).trim());
			Integer vy = Integer.parseInt(entry.substring(entry.indexOf(',', pos) + 1, entry.indexOf('>', pos)).trim());
			stars.add(new Star(x, y, vx, vy));
		}

		while (true) {
			move(stars);
			if (allTogether(stars)) {
				printMessage(stars);
			}
		}





	}

	private void printMessage(List<Star> stars) {
		final int minY = stars.stream().filter(s -> s.x >= 0 && s.y >= 0).map(s -> s.y).mapToInt(i -> i).min().orElse(0);
		final int maxY = stars.stream().filter(s -> s.x >= 0 && s.y >= 0).map(s -> s.y).mapToInt(i -> i).max().orElse(0);
		final int minX = stars.stream().filter(s -> s.x >= 0 && s.y >= 0).map(s -> s.x).mapToInt(i -> i).min().orElse(0);
		final int maxX = stars.stream().filter(s -> s.x >= 0 && s.y >= 0).map(s -> s.x).mapToInt(i -> i).max().orElse(0);

		boolean[][] text = new boolean[maxY-minY+1][maxX-minX+1];
		stars.stream().filter(s -> s.x >= minX && s.x <= maxX && s.y >= minY && s.y <= maxY).forEach(s -> text[s.x - minX][s.y - minY] = true);
		for (int i = 0; i < text[0].length; i++) {
			for (int j = 0; j < text[0].length; j++) {
				System.out.print(text[i][j] ? '#' : ' ');
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();

	}

	private void move(List<Star> stars) {
		stars.forEach(Star::move);
	}

	private boolean allTogether(List<Star> stars) {
		int min = stars.stream().filter(s -> s.x >= 0 && s.y >= 0).map(s -> s.y).mapToInt(i -> i).min().orElse(0);
		int max = stars.stream().filter(s -> s.x >= 0 && s.y >= 0).map(s -> s.y).mapToInt(i -> i).max().orElse(0);
		return (Math.abs(max - min ) < 10);
	}



	@Override
	protected String runPart2(String input) {
		return null;
	}



	public static void main(String[] args) {
		Day10 day = new Day10();
		String input = day.readFile("/Day10.input");
		System.out.println("result:" + day.runPart1(input));
		System.out.println("result:" + day.runPart2(input));


	}
}
