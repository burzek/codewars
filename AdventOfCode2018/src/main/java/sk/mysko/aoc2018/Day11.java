package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day11 extends AdventOfCodeBase<String> {

	private String runPart1(int serialNo) {
		Integer[][] cells = new Integer[300][300];
		int row = 0;
		int col = 0;
		int maxSum = 0;
		int maxRow = 0;
		int maxCol = 0;

		while (row < 298) {
			col = 0;
			while (col < 298) {
				int sum = 0;
				for (int gx = row; gx < row + 3; gx++) {
					for (int gy = col; gy < col + 3; gy++) {
						if (cells[gx][gy] == null) {
							cells[gx][gy] = compute(gx, gy, serialNo);
						}
						sum += cells[gx][gy];
					}
				}
				if (sum > maxSum) {
					maxSum = sum;
					maxRow = row;
					maxCol = col;
				}
				col++;
			}
			row++;
		}
		return (maxRow + 1) + "," + (maxCol + 1);
	}

	private Integer compute(int gx, int gy, int serialNo) {
		int power = gx + 10 + 1;
		power *= (gy + 1);
		power += serialNo;
		power *= (gx + 10 + 1);
		power = (power / 100) % 10;
		return power - 5;
	}

	public static void main(String[] args) {
		Day11 day = new Day11();
		System.out.println("result:" + day.runPart1(7803));
	}
}
