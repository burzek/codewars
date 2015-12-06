package sk.mysko.advent.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author boris.brinza
 */
public class Day3 {

	private Set<String> houses = new HashSet<>();

	private String readFile(String path) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Day3.class.getResourceAsStream(path)));
		String s = null;
		StringBuilder ret = new StringBuilder();
		try {
			while ((s = reader.readLine()) != null) {
				ret.append(s).append("\n");
			}
			;
		} catch (Exception e) {
			System.err.println("Cannot read file:" + path + ", error:" + e.toString());
			return null;
		}
		return  ret.toString();

	}


	private String getKey(int x, int y) {
		return String.valueOf(x) + "/" + String.valueOf(y);
	}

	private void processDirections(String input) {
		int x = 0;
		int y = 0;
		for (char dir : input.toCharArray()) {
			switch (dir) {
				case '^':
					y++;
					break;
				case 'v':
					y--;
					break;
				case '>':
					x++;
					break;
				case '<':
					x--;
					break;
			}
			boolean visited = houses.contains(getKey(x, y));
			if (visited == false) {
				houses.add(getKey(x, y));
			}
		}

	}

	private long runPart1(String input) {
		houses = new HashSet<>();
		houses.add("0/0");
		processDirections(input);
		return houses.size();
	}



	private long runPart2(String input) {
		houses = new HashSet<>();
		houses.add("0/0");
		String x = IntStream.range(0, input.length()).filter(i -> i % 2 == 0).mapToObj(i -> String.valueOf(input.charAt(i)))
				.collect(Collectors.joining());
		String y = IntStream.range(0, input.length()).filter(i -> i % 2 != 0).mapToObj(i -> String.valueOf(input.charAt(i)))
				.collect(Collectors.joining());

		processDirections(x);
		processDirections(y);
		return houses.size();
	}

	public static void main(String[] args) {
		Day3 day3 = new Day3();
		String input = day3.readFile("/Day3.input");
		long houses = day3.runPart1(input);
		System.out.println("total houses:" + houses);
		houses = day3.runPart2(input);
		System.out.println("total houses with robo:" + houses);

	}

}
