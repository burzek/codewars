package sk.mysko.aoc2019;

import java.util.Arrays;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day2 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day2 day1 = new Day2();
		String input = day1.readFile("/Day2.input");
		System.out.println("result:" + day1.runPart1(input));
		System.out.println("result:" + day1.runPart2(input));


	}

	protected Integer runPart1(String input) {
		Integer[] program = Arrays.stream(input.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
		program[1] = 12;
		program[2] = 2;
		int pos = 0;
		while (program[pos] != 99) {
			int op = program[pos];
			if (op == 1) {
				program[program[pos + 3]] = program[program[pos + 1]] + program[program[pos + 2]];
			} else if (op == 2) {
				program[program[pos + 3]] = program[program[pos + 1]] * program[program[pos + 2]];
			}
			pos += 4;
		}
		return program[0];
	}

	protected Integer runPart2(String input) {
		Integer[] program;
		int verb = 0;
		int noun = 0;
		for (;;) {
			program = Arrays.stream(input.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
			program[1] = noun;
			program[2] = verb;
			int pos = 0;
			while (program[pos] != 99) {
				int op = program[pos];
				if (op == 1) {
					program[program[pos + 3]] = program[program[pos + 1]] + program[program[pos + 2]];
				} else if (op == 2) {
					program[program[pos + 3]] = program[program[pos + 1]] * program[program[pos + 2]];
				}
				pos += 4;
			}
			if (program[0] == 19690720) {
				break;
			}
			verb++;
			if (verb == 100) {
				verb = 0;
				noun++;
			}
		}
		return noun * 100 + verb;
	}
}
