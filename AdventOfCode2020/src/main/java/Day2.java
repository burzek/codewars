import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author boris.brinza 01.dec.2020
 */
public class Day2 extends AdventOfCodeBase<Integer> {

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        String input = day2.readFile("/Day2.input");
        String[] lines = input.split("\n");

        System.out.println("result:" + day2.runPart1(lines));
        System.out.println("result:" + day2.runPart2(lines));
    }

    private int runPart1(String[] lines) {
        int valid = 0;
        for (String line : lines) {
            String[] split = line.split(" ");
            int min = Integer.parseInt(split[0].substring(0, split[0].indexOf('-')));
            int max = Integer.parseInt(split[0].substring(split[0].indexOf('-') + 1));
            char test = split[1].charAt(0);
            String pwd = split[2];

            long count = pwd.chars().filter(c -> c == test).count();
            if (count >= min && count <= max) {
                valid++;
            }

        }
        return valid;
    }

    private int runPart2(String[] lines) {
        int valid = 0;
        for (String line : lines) {
            String[] split = line.split(" ");
            int pos1 = Integer.parseInt(split[0].substring(0, split[0].indexOf('-')));
            int pos2 = Integer.parseInt(split[0].substring(split[0].indexOf('-') + 1));
            char test = split[1].charAt(0);
            String pwd = split[2];

            if ((pwd.charAt(pos1 - 1) == test && pwd.charAt(pos2 - 1) != test) ||
                    (pwd.charAt(pos1 - 1) != test && pwd.charAt(pos2 - 1) == test)) {
                valid++;
            }

        }
        return valid;
    }

}
