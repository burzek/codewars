package sk.mysko.advent.code;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * @author boris.brinza
 */
public class Day10 extends AdventOfCodeBase {


	@Override
	protected long runPart1(String input) {
		return getSizeAfterNRepeats(input, 40);
	}

	@Override
	protected long runPart2(String input) {
		return getSizeAfterNRepeats(input, 50);
	}

	private long getSizeAfterNRepeats(String input, int repeats) {
		for (int rep = 0; rep < repeats; rep++) {
			StringBuilder str = new StringBuilder();
			char lastChar = 0;
			int count = 0;
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if (lastChar == 0 || c == lastChar) {
					count++;
					lastChar = c;
				}

				if (c != lastChar || i == input.length() - 1) {
					if (i == input.length() - 1) {
						if (c == lastChar) {
							str.append(count + 1).append(lastChar);
						} else {
							str.append(count).append(lastChar);
							str.append(1).append(c);
						}
					} else {
						str.append(count).append(lastChar);
					}
					count = 1;
					lastChar = c;
				}

			}
			input = str.toString();
		}
		return input.length();
	}



	public static void main(String[] args) {
		Day10 day10 = new Day10();
		long value = day10.runPart1("1113222113");
		System.out.println("output is:" + value);
		long brightness = day10.runPart2("1113222113");
		System.out.println("output is:" + brightness);

	}

}
