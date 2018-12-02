package sk.mysko.aoc2018;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day1 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day1 day1 = new Day1();
//		String input = day1.readFile("/Day1.input");
//		System.out.println("result:" + day1.runPart1(input));
		String input = day1.readFile("/Day1b.input");
		System.out.println("result:" + day1.runPart2(input));


	}

	@Override
	protected Integer runPart1(String input) {
		int sum = 0;
		for (int i = 0; i < input.length(); i++) {
			int next = (i + 1 == input.length()) ? 0 : i + 1;
			sum += (input.charAt(i) == input.charAt(next) ? (input.charAt(i) - '0') : 0);
		}
		return sum;
	}

	@Override
	protected Integer runPart2(String input) {
		int sum = 0;
		int diff = input.length() / 2;
		for (int i = 0; i < input.length(); i++) {
			int next = (i + diff) % input.length();
			sum += (input.charAt(i) == input.charAt(next) ? (input.charAt(i) - '0') : 0);
		}
		return sum;
	}
}
