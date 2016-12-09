package sk.mysko.advent2016.code;

/**
 * @author boris.brinza
 */
public class Day8 extends AdventOfCodeBase<Integer> {

	private class DecompressData {
		private int repeat;
		private int length;
	}

	private DecompressData parseDecompressData(String def) {
		String[] parts = def.split("x");
		int len = Integer.parseInt(parts[0].trim());
		int repeat = Integer.parseInt(parts[1].trim());
		DecompressData dd = new DecompressData();
		dd.length = len;
		dd.repeat = repeat;
		return dd;
	}

	private String decompress(DecompressData dd, String data) {
		StringBuilder str = new StringBuilder();
		for (int repIdx = 0; repIdx < dd.repeat; repIdx++) {
			str.append(data.substring(0, dd.length));
		}
		return str.toString();
	}

	protected Integer runPart1(String input) {
		int pos = 0;
		StringBuilder str = new StringBuilder();
		while (pos < input.length()) {
			if (input.charAt(pos) == '(') {
				int end = input.indexOf(')', pos);
				DecompressData dd = parseDecompressData(input.substring(pos + 1, end));
				str.append(decompress(dd, input.substring(end + 1)));
				pos = end + dd.length;
			} else {
				str.append(input.charAt(pos));
			}
			pos++;
		}

		int len = 0;
		for (int i = 0; i < str.length();i++) {
			len += (Character.isWhitespace(str.charAt(i)) ? 0 : 1);
		}
		return len;
	}



	protected Integer runPart2(String input) {
		return 0;
	}

	public static void main(String[] args) {
		Day8 day8 = new Day8();
		String input = day8.readFile("/Day8.input");
		System.err.println("count:" + day8.runPart1(input));
//		input = day7.readFile("/Day7b.input");
//		System.err.println("code 2:" + day7.runPart2(input));

	}

}

