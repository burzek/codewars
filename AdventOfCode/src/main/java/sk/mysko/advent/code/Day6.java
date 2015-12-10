package sk.mysko.advent.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * @author boris.brinza
 */
public class Day6  {

	private String readFile(String path) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Day1.class.getResourceAsStream(path)));
		String s = null;
		StringBuilder ret = new StringBuilder();
		try {
			while ((s = reader.readLine()) != null) {
				ret.append(s).append("\n");
			}

		} catch (Exception e) {
			System.err.println("Cannot read file:" + path + ", error:" + e.toString());
			return null;
		}
		return  ret.toString();

	}

	private  int getIndex(int x, int y) {
		return x + y * 1000;
	}

	//fromx, fromy, tox, toy, oper
	private int[] getRanges(String line) {
		String[] elements = line.split("[ ,]");
		int fromX = 0; int fromY = 0;
		int toX = 0; int toY = 0;
		int oper = 0;
		if (elements[0].equals("toggle")) {
			fromX = Integer.parseInt(elements[1]);
			fromY = Integer.parseInt(elements[2]);
			toX = Integer.parseInt(elements[4]);
			toY = Integer.parseInt(elements[5]);
			oper = 1;
		} else if (elements[0].equals("turn")) {
			fromX = Integer.parseInt(elements[2]);
			fromY = Integer.parseInt(elements[3]);
			toX = Integer.parseInt(elements[5]);
			toY = Integer.parseInt(elements[6]);
			oper = elements[1].equals("on") ? 2 : 3;
		}

		return new int[] {fromX, fromY, toX, toY, oper};

	}

	private int runPart1(String input) {
		BitSet bitSet = new BitSet();

		for (String line : input.split("\\n")) {
			int[] elements = getRanges(line);
			int fromX = elements[0]; int fromY = elements[1];
			int toX = elements[2]; int toY = elements[3];
			int oper = elements[4];

			for (int y = fromY; y <= toY; y++) {
				if (oper == 1) {
					bitSet.flip(getIndex(fromX, y), getIndex(toX, y) + 1);
				} else if (oper == 2) {
					bitSet.set(getIndex(fromX, y), getIndex(toX, y) + 1);
				} else if (oper == 3) {
					bitSet.clear(getIndex(fromX, y), getIndex(toX, y) + 1);
				}
			}

		}

		return bitSet.cardinality();
	}



	private int runPart2(String input) {
		int[] bArr = new int[1000 * 1000];
		int[] diffArr = new int[] {2, 1, -1};	//oper 1-diff 2-on 3-off

		for (String line : input.split("\\n")) {
			int[] elements = getRanges(line);
			int fromX = elements[0]; int fromY = elements[1];
			int toX = elements[2]; int toY = elements[3];
			int diff = diffArr[elements[4] - 1];

			for (int y = fromY; y <= toY; y++) {
				IntStream.rangeClosed(getIndex(fromX, y), getIndex(toX, y)).
						forEach((idx) -> {
							bArr[idx] += diff;
							bArr[idx] = bArr[idx] < 0 ? 0 : bArr[idx];
						});
			}

		}

		return Arrays.stream(bArr).sum();
	}

	public static void main(String[] args) {
		Day6 day6 = new Day6();
		String input = day6.readFile("/Day6.input");
		int value = day6.runPart1(input);
		System.out.println("lights on:" + value);
		int brightness = day6.runPart2(input);
		System.out.println("brightness:" + brightness);

	}

}
