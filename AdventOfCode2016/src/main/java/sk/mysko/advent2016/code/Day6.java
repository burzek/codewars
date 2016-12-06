package sk.mysko.advent2016.code;

/**
 * @author boris.brinza
 */
public class Day6 extends AdventOfCodeBase<String> {


	protected String runPart1(String input) {
		String lines[] = input.split("\\r?\\n");

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < lines[0].length(); i++) {
			byte[] count = new byte['z' - 'a' + 1];
			for (int pos = 0; pos < count.length; pos++) {
				count[pos] = 0;
			}
			for (String line : lines) {
				count[line.charAt(i) - 'a']++;
			}
			//find max
			int max = 0;
			int maxIndex = 0;
			for (int pos = 0; pos < count.length; pos++) {
				if(count[pos] > max) {
					max = count[pos];
					maxIndex = pos;
				}
			}
			result.append((char) ('a' + maxIndex));
		}
		return result.toString();
	}



	protected String runPart2(String input) {
		String lines[] = input.split("\\r?\\n");

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < lines[0].length(); i++) {
			byte[] count = new byte['z' - 'a' + 1];
			for (int pos = 0; pos < count.length; pos++) {
				count[pos] = 0;
			}
			for (String line : lines) {
				count[line.charAt(i) - 'a']++;
			}
			//find max
			int min = Integer.MAX_VALUE;
			int minIndex = 0;
			for (int pos = 0; pos < count.length; pos++) {
				if(count[pos] < min && count[pos] != 0) {
					min = count[pos];
					minIndex = pos;
				}
			}
			result.append((char) ('a' + minIndex));
		}
		return result.toString();
	}

	public static void main(String[] args) {
		Day6 day6 = new Day6();
		String input = day6.readFile("/Day6.input");
		System.err.println("code:" + day6.runPart1(input));
		input = day6.readFile("/Day6b.input");
		System.err.println("code 2:" + day6.runPart2(input));

	}

}
