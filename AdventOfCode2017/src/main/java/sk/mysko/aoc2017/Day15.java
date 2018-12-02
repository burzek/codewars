package sk.mysko.aoc2017;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day15 extends AdventOfCodeBase<Long> {

	public static void main(String[] args) {
		Day15 day15 = new Day15();
		System.out.println("result:" + day15.runPart1("277,349"));
		System.out.println("result:" + day15.runPart2("277,349"));

	}

	@Override
	protected Long runPart1(String input) {
		long initialA = Long.parseLong(input.substring(0, input.indexOf(',')));
		long initialB = Long.parseLong(input.substring(input.indexOf(',') + 1));
		long fakA = 16807;
		long fakB = 48271;
		long count = 0;
		for (long iter = 0; iter < 40_000_000; iter++) {
			initialA = initialA * fakA % 2_147_483_647;
			initialB = initialB * fakB % 2_147_483_647;

			if ((initialA & 0xffff) == (initialB & 0xffff)) {
				count++;
			}
		}

		return count;
	}

	@Override
	protected Long runPart2(String input) {
		long initialA = Long.parseLong(input.substring(0, input.indexOf(',')));
		long initialB = Long.parseLong(input.substring(input.indexOf(',') + 1));
		long fakA = 16807;
		long fakB = 48271;
		long count = 0;
		for (long iter = 0; iter < 5_000_000; iter++) {
			do {
				initialA = initialA * fakA % 2_147_483_647;
			} while (initialA % 4 != 0);

			do {
				initialB = initialB * fakB % 2_147_483_647;
			} while (initialB % 8 != 0);

			if ((initialA & 0xffff) == (initialB & 0xffff)) {
				count++;
			}
		}

		return count;
	}




}
