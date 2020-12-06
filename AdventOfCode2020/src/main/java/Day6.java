import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author boris.brinza 01.dec.2020
 */
public class Day6 extends AdventOfCodeBase<Void> {

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        String input = day6.readFile("/Day6.input");
        String[] lines = input.split("\n");
        day6.runPart1(lines);
        day6.runPart2(lines);

    }

    private void runPart1(String[] lines) {
        Set<Character> answers = new HashSet<>();
        int sum = 0;
        for (String line : lines) {
            if (line.length() == 0) {
                sum += answers.size();
                answers.clear();
            }
            line.chars().forEach(c -> answers.add((char) c));
        }
        sum += answers.size();

        System.out.println("result 1 is:" + sum);
    }

    private void runPart2(String[] lines) {
        int[] answers = new int[26];
        int sum = 0;
        int persons = 0;
        for (String line : lines) {
            if (line.length() == 0) {
                sum += check(answers, persons);
                Arrays.fill(answers, 0);
                persons = 0;
            } else {
                line.chars().forEach(c -> answers[(char) c - 'a']++);
                persons++;
            }

        }
        sum += check(answers, persons);

        System.out.println("result 2 is:" + sum);
    }

    private int check(int[] answers, int persons) {
        return (int) Arrays.stream(answers).filter(b -> b == persons).count();
    }

}
