package sk.mysko.aoc2018;

import java.util.HashMap;
import java.util.Map;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day8 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day8 day8 = new Day8();
		String input = day8.readFile("/Day8.input");
		System.out.println("result:" + day8.runPart1(input));
		input = day8.readFile("/Day8b.input");
		System.out.println("result:" + day8.runPart2(input));


	}

	private Map<String, Integer> registers = new HashMap<>();

	private Integer process(String reg, String regOp, Integer shiftVal, String ifReg, String ifRegOp, Integer ifRegVal) {
		registers.putIfAbsent(reg, 0);
		registers.putIfAbsent(ifReg, 0);

		Integer currentIfRegVal = registers.get(ifReg);
		boolean isOperationValid = false;
		switch (ifRegOp) {
			case "<":
				isOperationValid = currentIfRegVal < ifRegVal;
				break;
			case ">":
				isOperationValid = currentIfRegVal > ifRegVal;
				break;
			case "==":
				isOperationValid = currentIfRegVal.intValue() == ifRegVal.intValue();
				break;
			case "!=":
				isOperationValid = currentIfRegVal.intValue() != ifRegVal.intValue();
				break;
			case "<=":
				isOperationValid = currentIfRegVal <= ifRegVal;
				break;
			case ">=":
				isOperationValid = currentIfRegVal >= ifRegVal;
				break;
		}


		int newVal = 0;
		int max = 0;
		if (isOperationValid) {
			if (regOp.equals("inc")) {
				newVal = registers.get(reg) + shiftVal;
			} else if (regOp.equals("dec")) {
				newVal = registers.get(reg) - shiftVal;
			}
			max = Math.max(registers.get(reg), newVal);
			registers.put(reg, newVal);
		}
		return max;
	}

	@Override
	protected Integer runPart1(String input) {
		registers.clear();
		for (String line : input.split("\n")) {
			String ops[] = line.split(" ");
			String reg = ops[0].trim();
			String regOp = ops[1].trim();
			Integer shiftVal = Integer.parseInt(ops[2].trim());
			String ifReg = ops[4].trim();
			String ifRegOp = ops[5].trim();
			Integer ifRegVal =  Integer.parseInt(ops[6].trim());

			process(reg, regOp, shiftVal, ifReg, ifRegOp, ifRegVal);

		}

		return registers.values().stream().max(Integer::compareTo).orElse(0);
	}

	@Override
	protected Integer runPart2(String input) {
		registers.clear();
		int max = 0;
		for (String line : input.split("\n")) {
			String ops[] = line.split(" ");
			String reg = ops[0].trim();
			String regOp = ops[1].trim();
			Integer shiftVal = Integer.parseInt(ops[2].trim());
			String ifReg = ops[4].trim();
			String ifRegOp = ops[5].trim();
			Integer ifRegVal =  Integer.parseInt(ops[6].trim());

			int newVal = process(reg, regOp, shiftVal, ifReg, ifRegOp, ifRegVal);
			if (newVal > max) {
				max = newVal;
			}

		}

		return max;
	}
}
