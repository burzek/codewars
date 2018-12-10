package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day10 extends AdventOfCodeBase<Integer> {

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

	protected int runPart1(String input) {
		String[] entries = input.split("\n");
		List<Star> stars = new ArrayList<>();

		for (String entry : entries) {
			int from = entry.indexOf('<');
			int to = entry.indexOf(',', from);
			Integer x = Integer.parseInt(entry.substring(from + 1, to).trim());

			from = to;
			to = entry.indexOf('>', from);
			Integer y = Integer.parseInt(entry.substring(from + 1, to).trim());

			from = entry.indexOf('<', from);
			to = entry.indexOf(',', from);
			Integer vx = Integer.parseInt(entry.substring(from + 1, to).trim());

			from = to;
			to = entry.indexOf('>', from);
			Integer vy = Integer.parseInt(entry.substring(from + 1, to).trim());
			stars.add(new Star(x, y, vx, vy));
		}


		int secs = 0;
		while (!allTogether(stars)) {
			move(stars);
			secs++;

		}
		printMessage(stars);
		return secs;


	}

	private void printMessage(List<Star> stars) {
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;

		for (Star s : stars) {
			if (s.x >= 0 && s.y >= 0) {
				if (s.y > maxY) {
					maxY = s.y;
				}
				if (s.y < minY) {
					minY = s.y;
				}
				if (s.x > maxX) {
					maxX = s.x;
				}
				if (s.x < minX) {
					minX = s.x;
				}
			}
		}


		boolean[][] text = new boolean[maxY - minY + 1][maxX - minX + 1];
		for (Star s : stars) {
			if (s.x >= minX && s.x <= maxX && s.y >= minY && s.y <= maxY) {
				text[s.y - minY][s.x - minX] = true;
			}
		}
		for (int i = 0; i < maxY - minY + 1; i++) {
			for (int j = 0; j < maxX - minX + 1; j++) {
				System.out.print(text[i][j] ? '#' : ' ');
			}
			System.out.println();
		}
	}

	private void move(List<Star> stars) {
		stars.forEach(Star::move);
	}

	private boolean allTogether(List<Star> stars) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (Star s : stars) {
			if (s.x >= 0 && s.y >= 0) {
				if (s.y > max) {
					max = s.y;
				}
				if (s.y < min) {
					min = s.y;
				}
			}
		}
		return (Math.abs(max - min ) <= 10);	//size of message is 10
	}




	public static void main(String[] args) {
		Day10 day = new Day10();
		String input = day.readFile("/Day10.input");
		System.out.println("result:" + day.runPart1(input));
	}
}
