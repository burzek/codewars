package sk.mysko.aoc2017;

import java.util.LinkedList;
import java.util.List;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day25 extends AdventOfCodeBase<Long> {

	public static void main(String[] args) {
		Day25 day25 = new Day25();
		String input = day25.readFile("/Day25.input");
		System.out.println(day25.runPart1(input));
//		System.out.println(day23.runPart2(input));77

	}

	class State {
		int[] write = new int[2];
		int[] move = new int[2];
		int[] step = new int[2];

	}


	class Turing {
		List<Integer> tape = new LinkedList<>();
		State[] states;
		int currentState;
		int tapePos;

		void setProgram(String program) {
			this.states = parse(program);
		}


		private State[] parse(String input) {
			State[] states = new State[6];

			String[] lines = input.split("\n");

			int state = -1;
			int current = 0;
			for (int l = 3; l < lines.length; l++) {
				String line = lines[l].trim();
				if (line.startsWith("In state")) {
					state++;
					states[state] = new State();
				} else if (line.startsWith("If the current value is 0:")) {
					current = 0;
				} else if (line.startsWith("If the current value is 1:")) {
					current = 1;
				} else if (line.startsWith("- Write the value 1.")) {
					states[state].write[current] = 1;
				} else if (line.startsWith("- Write the value 0.")) {
					states[state].write[current] = 0;
				} else if (line.startsWith("- Move one slot to the right.")) {
					states[state].move[current] = 1;
				} else if (line.startsWith("- Move one slot to the left.")) {
					states[state].move[current] = -1;
				} else if (line.startsWith("- Continue with state ")) {
					int newState = line.charAt(line.length() - 2) - 'A';
					states[state].step[current] = newState;
				}
			}
			return states;
		}

		long checkSum() {
			return tape.stream().filter(v -> v == 1).count();
		}

		void step() {
			State s = states[currentState];
			if (tapePos < 0) {
				tapePos = 0;
				tape.add(0, 0);
			} else if (tapePos == tape.size()) {
				tape.add(tape.size(), 0);
			}
			Integer val = tape.get(tapePos);
			if (val == null) {
				val = 0;
				tape.set(tapePos, val);
			}
			tape.set(tapePos, s.write[val]);
			tapePos += s.move[val];
			currentState = s.step[val];
		}
	}

	@Override
	protected Long runPart1(String input) {
		Turing turing = new Turing();
		turing.setProgram(input);
		for (int i = 0; i < 12_386_363; i++) {
			turing.step();
		}

		return turing.checkSum();
	}


	@Override
	protected Long runPart2(String input) {
		return 0L;
	}




}
