package sk.mysko.aoc2019;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day4 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day4 day = new Day4();
		System.out.println("result:" + day.runPart1(382345, 843167));
		System.out.println("result:" + day.runPart2(382345, 843167));


	}

	protected Integer runPart1(int from, int to) {
		int counter = 0;
		for (int t = from; t <= to; t++) {
			if (isValid1(t)) {
				counter++;
			}
		}
		return counter;
	}

	protected Integer runPart2(int from, int to) {
		int counter = 0;
		for (int t = from; t <= to; t++) {
			if (isValid2(t)) {
				counter++;
			}
		}
		return counter;
	}

	private boolean isValid1(int t) {
		boolean same = false;
		int lastDigit = 10;
		while (t != 0) {
			int digit = t % 10;
			if (digit > lastDigit) {
				return false;
			}
			if (digit == lastDigit) {
				same = true;
			}

			lastDigit = digit;
			t = t / 10;
		}

		return same;
	}


	private boolean isValid2(int t) {
		int maxSameLen = 0;
		int sameLen = 1;
		int lastDigit = 10;
		while (t != 0) {
			int digit = t % 10;
			if (digit > lastDigit) {
				return false;
			}
			if (digit == lastDigit) {
				sameLen++;
			} else {
				maxSameLen = Math.max(maxSameLen, sameLen);
				sameLen = 1;
			}

			lastDigit = digit;
			t = t / 10;
		}

		return maxSameLen == 2;
	}

}
