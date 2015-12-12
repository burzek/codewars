package sk.mysko.advent.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author boris.brinza
 */
public class Day1 extends AdventOfCodeBase {


	protected long runPart1(String input) {
		long floor = 0;
		floor = input.chars().filter(x -> x == '(').count();
		floor -= input.chars().filter(x -> x == ')').count();
		return floor;
	}

	protected long runPart2(String input) {
		int floor = 0;
		int pos = 0;
		for (char c : input.toCharArray()) {
			floor += c == '(' ? 1 : -1;
			pos++;
			if (floor == -1) {
				return pos;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Day1 day1 = new Day1();
		String input = day1.readFile("/Day1.input");
		long floor = day1.runPart1(input);
		System.err.println("Floor=" + floor);
		long positionOfBasement = day1.runPart2(input);
		System.err.println("Basement=" + positionOfBasement);

	}

}
