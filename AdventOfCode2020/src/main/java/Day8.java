import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boris.brinza 01.dec.2020
 */
public class Day8 extends AdventOfCodeBase<Void> {

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        String input = day8.readFile("/Day8.input");
        String[] lines = input.split("\n");

        day8.runPart1(day8.parse(lines));
        day8.runPart2(day8.parse(lines));

    }

    private class Instruction {
        int opCode;         //0 - NOP 1 - ACC 2 - JMP
        int opArg;
        boolean processed;

        public Instruction(int opCode, int opArg) {
            this.opCode = opCode;
            this.opArg = opArg;
            this.processed = false;
        }
    }

    private void runPart1(List<Instruction> instructions) {
        int pc = 0;
        int a = 0;
        Instruction pcI = instructions.get(pc);
        do {

            pcI.processed = true;
            if (pcI.opCode == 0) {
                pc++;
            } else if (pcI.opCode == 1) {
                a += pcI.opArg;
                pc++;
            } else if (pcI.opCode == 2) {
                pc += pcI.opArg;
            }
            pcI = instructions.get(pc);
        } while (!pcI.processed);

        System.out.println("part1:" + a);
    }


    private void runPart2(List<Instruction> instructions) {

    }

    private List<Instruction> parse(String[] lines) {
        List<Instruction> instructions = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "nop":
                    instructions.add(new Instruction(0, Integer.parseInt(parts[1])));
                    break;
                case "acc":
                    instructions.add(new Instruction(1, Integer.parseInt(parts[1])));
                    break;
                case "jmp":
                    instructions.add(new Instruction(2, Integer.parseInt(parts[1])));
                    break;
            }
        }

        return instructions;
    }


}
