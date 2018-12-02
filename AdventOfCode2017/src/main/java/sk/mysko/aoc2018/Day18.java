package sk.mysko.aoc2018;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day18 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day18 day18 = new Day18();
		String input = day18.readFile("/Day18.input");
		System.out.println("result:" + day18.runPart1(input));
		System.out.println("result:" + day18.runPart2(input));

	}

	private Long getValue(Map<Character, Long> registers, String operand) {
		operand = operand.trim();
		if (operand.length() == 1 && Character.isLetter(operand.charAt(0))) {
			return registers.getOrDefault(operand.charAt(0), 0L);
		} else {
			return Long.parseLong(operand);
		}
	}

	private class Program {
		Program(String programLines) {
			this.program = programLines.split("\n");
		}
		Program target;

		Map<Character, Long> registers = new HashMap<>();
		int ir = 0;
		String[] program;
		Queue<Long> queue = new ArrayDeque<>();
		boolean programEnded = false;
		boolean programWaiting = false;
		int sendCount = 0;

		public void send(Long value) {
			target.queue.add(value);
		}
		public void step() {
			if (programEnded) {
				return;
			}
			String[] cmd = program[ir].split(" ");
			char reg = cmd[1].trim().charAt(0);
			registers.putIfAbsent(reg, 0L);
			switch (cmd[0].trim()) {
				case "snd":
					sendCount++;
					send(getValue(registers, cmd[1]));
					ir++;
					break;
				case "set":
					registers.put(reg, getValue(registers, cmd[2]));
					ir++;
					break;
				case "add":
					registers.put(reg, registers.get(reg) + getValue(registers, cmd[2].trim()));
					ir++;
					break;
				case "mul":
					registers.put(reg, registers.get(reg) * getValue(registers, cmd[2].trim()));
					ir++;
					break;
				case "mod":
					registers.put(reg, registers.get(reg) % getValue(registers, cmd[2].trim()));
					ir++;
					break;
				case "rcv":
					programWaiting = true;
					if (!queue.isEmpty()) {
						registers.put(reg, queue.poll());
						programWaiting = false;
						ir++;
					}
					break;
				case "jgz":
					if (getValue(registers, cmd[1].trim()) != 0) {
						ir += getValue(registers, cmd[2].trim());
					} else {
						ir++;
					}
					break;
			}
			if (ir < 0 || ir >= program.length) {
				programEnded = true;
			}

		}
	}

	@Override
	protected Integer runPart1(String input) {
		Map<Character, Long> registers = new HashMap<>();
		String[] lines = input.split("\n");
		long lastPlayed = 0;
		int ir = 0;
		while (ir >= 0 && ir < lines.length) {
			String[] cmd = lines[ir].split(" ");
			char reg = cmd[1].trim().charAt(0);
			registers.putIfAbsent(reg, 0L);
			switch (cmd[0].trim()) {
				case "snd":
					lastPlayed = getValue(registers, cmd[1]);
					ir++;
					break;
				case "set":
					registers.put(reg, getValue(registers, cmd[2]));
					ir++;
					break;
				case "add":
					registers.put(reg, registers.get(reg) + getValue(registers, cmd[2].trim()));
					ir++;
					break;
				case "mul":
					registers.put(reg, registers.get(reg) * getValue(registers, cmd[2].trim()));
					ir++;
					break;
				case "mod":
					registers.put(reg, registers.get(reg) % getValue(registers, cmd[2].trim()));
					ir++;
					break;
				case "rcv":
					if (getValue(registers, cmd[1].trim()) != 0) {
						registers.put(reg, lastPlayed);
						return  (int) lastPlayed;
					}
					ir++;
					break;
				case "jgz":
					if (getValue(registers, cmd[1].trim()) != 0) {
						ir += getValue(registers, cmd[2].trim());
					} else {
						ir++;
					}
					break;
			}

		}
		return 0;
	}


	@Override
	protected Integer runPart2(String input) {

		Program p1 = new Program(input);
		Program p2 = new Program(input);
		p1.target = p2;
		p2.target = p1;
		p1.send(1L);
		p1.send(2L);
		p1.send(0L);

		p2.send(1L);
		p2.send(2L);
		p2.send(1L);

		boolean bothEnd = p1.programEnded && p2.programEnded;
		boolean bothWaiting = p1.programWaiting && p2.programWaiting;

		while (!bothEnd || !bothWaiting) {
			p1.step();
			p2.step();
		}

		return p1.sendCount;

	}




}
