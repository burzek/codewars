package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day16 extends AdventOfCodeBase<String> {

	public static void main(String[] args) {
		Day16 day16 = new Day16();
		String input = day16.readFile("/Day16.input");
		System.out.println("result:" + day16.runPart1(input));
		input = day16.readFile("/Day16b.input");
		System.out.println("result:" + day16.runPart2(input));

	}

	@Override
	protected String runPart1(String input) {
		List<Character> list = new LinkedList<>();
		IntStream.range('a', 'q').forEach(c -> list.add((char) c));

		String[] moves = input.split(",");
		for (String move : moves) {
			switch (move.charAt(0)) {
				case 's' :
					int shift = Integer.parseInt(move.substring(1).trim());
					for (int i = 0; i < shift; i++) {
						char c = list.remove(list.size() - 1);
						list.add(0,  c);
					}
					break;
				case 'x':
					String[] swaps = move.substring(1).split("/");
					int from = Integer.parseInt(swaps[0].trim());
					int to = Integer.parseInt(swaps[1].trim());
					Collections.swap(list, from, to);
					break;
				case 'p':
					swaps = move.substring(1).split("/");
					from = list.indexOf(swaps[0].trim().charAt(0));
					to = list.indexOf(swaps[1].trim().charAt(0));
					Collections.swap(list, from, to);
					break;
			}
		}
		return list.stream().map(Object::toString).collect(Collectors.joining());
	}

	@Override
	protected String runPart2(String input) {

		String listStr = runPart1(input);

		int[] shifts = new int['p' - 'a' + 1];
		for (int i = 0; i < shifts.length; i++) {
			int charIndex = listStr.charAt(i) - 'a';
			shifts[charIndex] = i - charIndex ;
			if (shifts[charIndex] < 0) {
				shifts[charIndex] = shifts.length + shifts[charIndex];
			}
			shifts[charIndex] = (1_000_000_000 * shifts[charIndex]) % shifts.length;
		}
		char[] ret = new char[16];
		IntStream.range('a', 'q').forEach(c -> {
			int newPos = (c - 'a') + shifts[c - 'a'];
			ret[newPos % shifts.length] =  (char) c;
		});


		return String.valueOf(ret);
	}




}
