package sk.mysko.advent.code;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Hex;

/**
 * @author boris.brinza
 */
public class Day5 extends AdventOfCodeBase {

	@Override
	protected long runPart1(String input) {
		return Arrays.stream(input.split("\\s+")).
				filter((line) -> Pattern.matches(".*(.)\\1.*", line)).
				filter((line) -> Pattern.matches(".*([aeiou]).*([aeiou]).*([aeiou]).*", line)).
				filter((line) -> Pattern.matches("^((?!ab).)*$", line)).
				filter((line) -> Pattern.matches("^((?!cd).)*$", line)).
				filter((line) -> Pattern.matches("^((?!pq).)*$", line)).
				filter((line) -> Pattern.matches("^((?!xy).)*$", line)).
				count();
	}

	@Override
	protected long runPart2(String input) {
		return  Arrays.stream(input.split("\\s+")).
				 filter((line) -> Pattern.matches(".*(.).\\1.*", line)).
				 filter((line) -> Pattern.matches(".*(..).*\\1.*", line)).
				 count();
	}

	public static void main(String[] args) {
		Day5 day5 = new Day5();
		String input = day5.readFile("/Day5.input");
		long niceStringCount = day5.runPart1(input);
		System.err.println("nice string count: " + niceStringCount);
		niceStringCount = day5.runPart2(input);
		System.err.println("nice string count 2: " + niceStringCount);
	}

}
