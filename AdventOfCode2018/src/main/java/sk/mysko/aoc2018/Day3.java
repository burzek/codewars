package sk.mysko.aoc2018;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day3 extends AdventOfCodeBase<Long> {

	private class Claim {
		private long claimId;
		private int leftMargin;
		private int topMargin;
		private int width;
		private int height;
		private boolean overlapped;

		private Claim(String line) {
			this.parseLine(line);
		}

		private void parseLine(String line) {
			String parts[] = line.split(" ");
			claimId = Integer.parseInt(parts[0].substring(1).trim());
			String[] margins = parts[2].split(",");
			leftMargin = Integer.parseInt(margins[0].trim());
			topMargin = Integer.parseInt(margins[1].substring(0, margins[1].length() - 1).trim());
			String[] size = parts[3].split("x");
			width = Integer.parseInt(size[0].trim());
			height = Integer.parseInt(size[1].trim());
		}
	}


	public static void main(String[] args) {
		Day3 day3 = new Day3();
		String input = day3.readFile("/Day3.input");
		System.out.println("result:" + day3.runPart1(input));
		System.out.println("result:" + day3.runPart2(input));
	}

	protected Long runPart1(String input) {
		//quick and dirty
		int[][] claims = new int[1000][1000];
		for (String line : input.split("\n")) {
			Claim c = new Claim(line);
			for (int row = c.topMargin; row < c.topMargin + c.height; row++) {
				for (int col = c.leftMargin; col < c.leftMargin + c.width; col++) {
					claims[row][col]++;
				}
			}
		}
		return Arrays.stream(claims).mapToLong(c -> Arrays.stream(c).filter(v -> v > 1).count()).sum();
	}

	protected Long runPart2(String input) {
		//quick and dirty
		Map<Long, Claim> allClaims = new HashMap<>();
		for (String line : input.split("\n")) {
			Claim c = new Claim(line);
			allClaims.put(c.claimId, c);
		}

		long[][] claims = new long[1000][1000];
		for (Claim c : allClaims.values()) {
			for (int row = c.topMargin; row < c.topMargin + c.height; row++) {
				for (int col = c.leftMargin; col < c.leftMargin + c.width; col++) {
					if (claims[row][col] != 0) {
						c.overlapped = true;
						allClaims.get(claims[row][col]).overlapped = true;
					}
					claims[row][col] = c.claimId;
				}
			}
		}

		return allClaims.values().stream().filter(c -> !c.overlapped).findFirst().map(c -> c.claimId).orElseThrow(() -> new RuntimeException("Not found, error"));

	}

}
