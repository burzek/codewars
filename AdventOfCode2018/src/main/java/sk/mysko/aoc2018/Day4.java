package sk.mysko.aoc2018;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day4 extends AdventOfCodeBase<Integer> {

	private class Record {
		private int guardId;
		private int[] sleep = new int[60];
	}
	private Map<Integer, Record> records = new HashMap<>();


	@Override
	protected Integer runPart1(String input) {
		int overallSum = 0;
		Record maxRecord = null;
		for (Record r : records.values()) {
			int sum = Arrays.stream(r.sleep).sum();
			if (sum > overallSum) {
				overallSum = sum;
				maxRecord = r;
			}
		}

		return maxRecord.guardId * maxMinute(maxRecord);

	}

	@Override
	protected Integer runPart2(String input) {
		Record maxRecord = null;
		int overallMax = 0;
		for (Record r : records.values()) {
			int max = Arrays.stream(r.sleep).max().orElse(0);
			if (max > overallMax) {
				overallMax = max;
				maxRecord = r;
			}
		}

		return maxRecord.guardId * maxMinute(maxRecord);

	}

	private int maxMinute(Record maxRecord) {
		int maxMinute = 0;
		for (int minute = 0; minute < 59; minute++) {
			if (maxRecord.sleep[minute] > maxRecord.sleep[maxMinute]) {
				maxMinute = minute;
			}
		}
		return maxMinute;
	}

	private void parse(String input) {
		Set<String> lines = new TreeSet();
		for (String line : input.split("\n")) {
			lines.add(line);
		}

		Record currentRecord = null;
		int asleep = 0;
		for (String line : lines) {
			int minute = Integer.parseInt(line.substring(15, 17));
			if (line.indexOf('#') != -1) {
				int guardId = Integer.parseInt(line.substring(26, line.indexOf(' ', 27)));
				if ((currentRecord = records.get(guardId)) == null) {
					currentRecord = new Record();
					currentRecord.guardId = guardId;
					records.put(guardId, currentRecord);
				}
			} else if (line.contains("falls")) {
				asleep = minute;
			} else if (line.indexOf("wakes") != 1) {
				for (int i = asleep; i < minute; i++) {
					currentRecord.sleep[i]++;
				}
			}
		}

	}

	public static void main(String[] args) {
		Day4 day4 = new Day4();
		String input = day4.readFile("/Day4.input");

		day4.parse(input);		//set records map
		System.out.println("result:" + day4.runPart1(input));
		System.out.println("result:" + day4.runPart2(input));
	}
}
