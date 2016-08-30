package sk.mysko.advent.code;

/**
 * @author boris.brinza
 */
public class Day12 extends AdventOfCodeBase {


	@Override
	protected long runPart1(String input) {
		long sum = 0;
		for (String s : input.replaceAll("[\\{\\[\\]\\}]", "").split("[:,]")) {
			if (s.charAt(0) != '"') {
				try {
					sum += Integer.parseInt(s);
				} catch (NumberFormatException e) {
					//nevermind
				}
			}
		}
		return sum;
	}

	@Override
	protected long runPart2(String input) {
		return 0;
	}


	public static void main(String[] args) {
		Day12 day12 = new Day12();
		String data = day12.readFile("/Day12.input");
		long value = day12.runPart1(data);
		System.out.println("output is:" + value);
		value = day12.runPart2(data);
		System.out.println("output is:" + value);

	}

}
