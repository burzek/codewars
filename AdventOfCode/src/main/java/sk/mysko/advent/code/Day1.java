package sk.mysko.advent.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author boris.brinza
 */
public class Day1 {

	private String readFile(String path) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Day1.class.getResourceAsStream(path)));
		String s = null;
		StringBuilder ret = new StringBuilder();
		try {
			while ((s = reader.readLine()) != null) {
				ret.append(s);
			}
			;
		} catch (Exception e) {
			System.err.println("Cannot read file:" + path + ", error:" + e.toString());
			return null;
		}
		return  ret.toString();

	}
	private long runPart1(String input) {
		long floor = 0;
		floor = input.chars().filter(x -> x == '(').count();
		floor -= input.chars().filter(x -> x == ')').count();
		return floor;
	}

	private long runPart2(String input) {
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
		long floor = new Day1().runPart1(input);
		System.err.println("Floor=" + floor);
		long positionOfBasement = new Day1().runPart2(input);
		System.err.println("Basement=" + positionOfBasement);

	}

}
