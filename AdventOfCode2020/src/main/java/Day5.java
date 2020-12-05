import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author boris.brinza 01.dec.2020
 */
public class Day5 extends AdventOfCodeBase<Void> {

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        String input = day5.readFile("/Day5.input");
        String[] lines = input.split("\n");
        day5.runSolution(lines);

    }

    private int getSeat(char highMark, int maxVal, String def) {
        int min = 0;
        int max = maxVal;
        for (char c : def.toCharArray()) {
            if (c == highMark) {
                max = min + (max - min) / 2;

            } else {
                min = min + (max - min) / 2 + 1;
            }
        }
        return min; //same as max
    }

    private void runSolution(String[] lines) {
        int maxSeat = 0;
        List<Integer> seats = new ArrayList<>();

        for (String line : lines) {
            String rowDef = line.substring(0, 7);
            String colDef = line.substring(7);
            int seat = getSeat('F', 127, rowDef) * 8 + getSeat('L', 7, colDef);

            maxSeat = Math.max(maxSeat, seat);
            seats.add(seat);

        }

        Collections.sort(seats);
        int mySeat = 0;
        for (int i = 0; i < seats.size() - 1; i++) {
            if (seats.get(i + 1) - seats.get(i) == 2) {
                mySeat = seats.get(i) + 1;
                break;
            }
        }

        System.out.println("part1:" + maxSeat);
        System.out.println("part2:" + mySeat);

    }

}
