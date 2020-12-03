/**
 * @author boris.brinza 01.dec.2020
 */
public class Day3 extends AdventOfCodeBase<Long> {

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        String input = day3.readFile("/Day3.input");
        String[] lines = input.split("\n");

        System.out.println("result:" + day3.runPart1(lines));
        System.out.println("result:" + day3.runPart2(lines));
    }

    private long runPart1(String[] lines) {
        return countTrees(lines, 3, 1);
    }

    private long runPart2(String[] lines) {
        return countTrees(lines, 1, 1) *
                countTrees(lines, 3, 1) *
                countTrees(lines, 5, 1) *
                countTrees(lines, 7, 1) *
                countTrees(lines, 1, 2);
    }


    private long countTrees(String[] lines, int x_step, int y_step) {
        int x = 0;
        int y = 0;
        int lineLen = lines[0].length();
        long count = 0;
        while (y < lines.length) {
            count += (lines[y].charAt(x) == '#' ? 1 : 0);
            x = (x + x_step) % lineLen;
            y += y_step;
        }
        return count;
    }
}
