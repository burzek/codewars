package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day7 extends AdventOfCodeBase<String> {


	@Override
	protected String runPart1(String input) {
		String[] codes = new String['Z' - 'A' + 1];

		StringBuilder usedLetters = new StringBuilder();
		for (String line : input.split("\n")) {
			char prereq = line.charAt(5);
			int code = line.charAt(36) - 'A';
			codes[code] = (codes[code] == null ? String.valueOf(prereq) : codes[code] + prereq);
			usedLetters.append(line.charAt(5)).append(line.charAt(36));
		}

		//search root
		StringBuilder ret = new StringBuilder();
		for (int pos = 0; pos < codes.length; pos++) {
			if (codes[pos] == null && usedLetters.toString().indexOf((char) ('A' + pos)) != -1) {
				ret.append((char) ('A' + pos));
			}
		}

		boolean wasAdd;
		do {
			wasAdd = false;
			for (int pos = 0; pos < codes.length; pos++) {
				if (allPrereq(ret, codes[pos])) {
					ret.append((char) ('A' + pos));
					codes[pos] = null;
					wasAdd = true;
					break;
				}
			}
		} while (wasAdd);

		return ret.toString();
	}

	private boolean allPrereq(StringBuilder prereq, String test) {
		if (test != null) {
			return Arrays.stream(test.split("")).allMatch(c -> prereq.indexOf(c) != -1);
		}
		return false;
	}

	@Override
	protected String runPart2(String input) {
		return null;
	}


	public static void main(String[] args) {
		Day7 day7 = new Day7();
		String input = day7.readFile("/Day7.input");
		System.out.println("result:" + day7.runPart1(input));
		System.out.println("result:" + day7.runPart2(input));


	}
}
