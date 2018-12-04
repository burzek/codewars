package sk.mysko.aoc2018;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day4 extends AdventOfCodeBase<Integer> {

	private class Record {
		private int guardId;
		private int sleepTime;

		@Override
		public String toString() {
			return "Record{" +
				"guardId=" + guardId +
				", sleepTime=" + sleepTime +
				'}';
		}
	}

	public static void main(String[] args) {
		Day4 day4 = new Day4();
		String input = day4.readFile("/Day4.input");
		System.out.println("result:" + day4.runPart1(input));
		System.out.println("result:" + day4.runPart2(input));
	}


	Set<String> lines = new TreeSet<>();
	Set<Record> records = new HashSet<>();

	@Override
	protected Integer runPart1(String input) {

		//sort
		for (String line : input.split("\n")) {
			lines.add(line);
		}

		Record current = null;
		long asleep = 0;
		for (String line : lines) {
			int minute = Integer.parseInt(line.substring(15, 17));
			if (line.contains("Guard #")) {
				current = new Record();
				current.guardId = Integer.parseInt(line.substring(26, line.indexOf(' ', 27)));
				records.add(current);
			} else if (line.contains("asleep")) {
				asleep = minute;
			} else if (line.contains("wakes up")) {
				current.sleepTime += (minute - asleep - 1);
			}

		}

		return records.stream()
			.max(Comparator.comparingInt(o2 -> o2.sleepTime))
			.map(o -> o.sleepTime * o.guardId)
			.orElseThrow(() -> new RuntimeException("Not found"));
	}

	@Override
	protected Integer runPart2(String input) {
		return 0;

	}

}
