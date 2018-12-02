package sk.mysko.aoc2017;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day9 extends AdventOfCodeBase<Integer> {
	private int total;
	private int removed;

	public static void main(String[] args) {
		Day9 day9 = new Day9();
		String input = day9.readFile("/Day9.input");
		System.out.println("result:" + day9.runPart1(input));
		input = day9.readFile("/Day9b.input");
		System.out.println("result:" + day9.runPart2(input));


	}

	private int skipGarbage(String input, int pos) {
		boolean processingGarbage = true;
		boolean cancel = false;
		while (processingGarbage) {
			if (input.charAt(pos) == '!') {
				cancel = !cancel;
				pos++;
			} else if (input.charAt(pos) == '>' && !cancel) {
				processingGarbage = false;
			} else {
				if (!cancel) {
					removed++;
				}
				cancel = false;
				pos++;
			}
		}
		return pos;
	}


	private Integer parseInput(String input, int pos, int level) {
		boolean parsing = true;
		while (parsing && pos < input.length()) {
			if (input.charAt(pos) == '{') {
				total += level;
				pos = parseInput(input, pos + 1,  level + 1);
			} else if (input.charAt(pos) == '<') {
				pos = skipGarbage(input, pos + 1);
			} else if (input.charAt(pos) == '}') {
				parsing = false;
			} else {
				pos++;
			}
		}
		return pos + 1;
	}

	@Override
	protected Integer runPart1(String input) {
		total = 0;
		removed = 0;
		parseInput(input, 0, 1);
		return total;
	}

	@Override
	protected Integer runPart2(String input) {
		removed = 0;
		total = 0;
		parseInput(input, 0, 1);
		return removed;
	}
}
