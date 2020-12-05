/**
 * @author boris.brinza 01.dec.2020
 */
public class Day5 extends AdventOfCodeBase<Long> {

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        String input = day5.readFile("/Day5.input");
        String[] lines = input.split("\n");
        System.out.println("part1:" + day5.runSolution(lines));

    }

    private long runSolution(String[] lines) {
        int maxSeat = 0;
        for (String line : lines) {
            String rowDef = line.substring(0, 7);
            String colDef = line.substring(7);

            int min = 0;
            int max = 127;
            for (char c : rowDef.toCharArray()) {
                if (c == 'F') {
                    max = min + (max - min) / 2;

                } else if (c == 'B') {
                    min = min + (max - min) / 2 + 1;
                }
            }

            int minR = 0;
            int maxR = 7;
            for (char c : colDef.toCharArray()) {
                if (c == 'L') {
                    maxR = minR + (maxR - minR) / 2;

                } else if (c == 'R') {
                    minR = minR + (maxR - minR) / 2 + 1;
                }
            }

            maxSeat = Math.max(maxSeat, min * 8 + minR);

        }

        return maxSeat;

    }

}
