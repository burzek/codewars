package sk.mysko.aoc2017;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day23 extends AdventOfCodeBase<Long> {

	public static void main(String[] args) {
		Day23 day23 = new Day23();
		String input = day23.readFile("/Day23.input");
		System.out.println(day23.runPart1(input));
		System.out.println(day23.runPart2(input));

	}

	enum OP_CODE {
		SET, SUB, MUL, JNZ;
	}
	enum I_TYPE {
		REG, VAL;
	}

	class Instruction {
		private OP_CODE opCode;

		private I_TYPE op1Type;
		private int op1Reg;
		private long op1Val;

		private I_TYPE op2Type;
		private int op2Reg;
		private long op2Val;
	}

	class CPU {
		private long registers[] = new long[8];
		private int pc;
		private Instruction[] instructions;
		private int mulCount;

		CPU(String program) {
			String[] lines = program.split("\n");
			instructions = new Instruction[lines.length];
			int pos = 0;
			for (String line : lines) {
				String i = line.substring(0, line.indexOf(' '));
				Instruction instruction = new Instruction();
				switch (i) {
					case "set":
						instruction.opCode = OP_CODE.SET;
						break;
					case "sub":
						instruction.opCode = OP_CODE.SUB;
						break;
					case "mul":
						instruction.opCode = OP_CODE.MUL;
						break;
					case "jnz":
						instruction.opCode = OP_CODE.JNZ;
						break;
				}
				int from = line.indexOf(' ');
				int to = line.indexOf(' ', from + 1);
				char op1 = line.substring(from + 1,  to).charAt(0);
				if (Character.isLetter(op1)) {
					instruction.op1Type = I_TYPE.REG;
					instruction.op1Reg = op1 - 'a';
				} else {
					instruction.op1Type = I_TYPE.VAL;
					instruction.op1Val= Long.valueOf(op1);
				}

				from = line.indexOf(' ', to  + 1);
				String v = line.substring(to + 1);
				if (Character.isLetter(v.charAt(0))) {
					instruction.op2Type = I_TYPE.REG;
					instruction.op2Reg = v.charAt(0) - 'a';
				} else {
					instruction.op2Type = I_TYPE.VAL;
					instruction.op2Val = Long.valueOf(v);
				}
				instructions[pos++] = instruction;
			}

		}

		private long getVal(Instruction instruction) {
			if (instruction.op2Type == I_TYPE.REG) {
				return registers[instruction.op2Reg];
			} else {
				return instruction.op2Val;
			}
		}

		public boolean isRunning() {
			return pc >= 0 && pc < instructions.length;
		}

		void step() {
			Instruction instruction = instructions[pc];
			switch (instruction.opCode) {
				case SET:
					registers[instruction.op1Reg] = getVal(instruction);
					pc++;
					break;
				case SUB:
					registers[instruction.op1Reg] -= getVal(instruction);
					pc++;
					break;
				case MUL:
					registers[instruction.op1Reg] *= getVal(instruction);
					mulCount++;
					pc++;
					break;
				case JNZ:
					long testVal =  instruction.op1Type == I_TYPE.REG ? registers[instruction.op1Reg] : instruction.op1Val;
					if (testVal != 0) {
						pc += getVal(instruction);
					} else {
						pc++;
					}
					break;
			}
		}

	}


	@Override
	protected Long runPart1(String input) {

		CPU cpu = new CPU(input);
		while (cpu.isRunning()) {
			cpu.step();
		}
		return (long) cpu.mulCount;
	}


	@Override
	protected Long runPart2(String input) {

		CPU cpu = new CPU(input);
		cpu.registers[0] = 1;
		while (cpu.isRunning()) {
			cpu.step();
		}
		return cpu.registers[7];

	}




}
