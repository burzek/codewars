package sk.mysko.advent.code;

import java.util.regex.Pattern;

/**
 * @author boris.brinza
 */
public class Day8 extends AdventOfCodeBase {


	@Override
	protected long runPart1(String input) {
		int total = 0;
		int real = 0;
		for (String line : input.split("\\s+")) {
			total += (line.length());
			String replaces = line.replaceAll("\\\\\"", "X");
			replaces = replaces.replaceAll("\\\\\\\\","Y");
			replaces = replaces.replaceAll("\\\\x..", "Z").replaceAll(" ", "");
			real += (replaces.length() - 2);
		}
		return total - real;
	}


	@Override
	protected long runPart2(String input) {
		int total = 0;
		int expanded = 0;
		for (String line : input.split("\\s+")) {
			total += (line.length());
			expanded += line.length() + (2 * line.chars().filter((x) -> x == '"').count());
			expanded += Pattern.compile(".*\\\\x...*").matcher(line).matches() ? Pattern.compile("(\\\\x..)").matcher(line).groupCount() : 0;
		}
		return expanded - total;
	}

	public static void main(String[] args) {
		Day8 day8 = new Day8();
		String input = day8.readFile("/Day8.input");
		long value = day8.runPart1(input);
		System.out.println("output is:" + value);
		long brightness = day8.runPart2(input);
		System.out.println("outpus is:" + brightness);

	}

}
