package sk.araed.aoc.aoc2024;


import static java.lang.Math.abs;
import static java.lang.Math.signum;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day2 {

  public static void main(String[] args) {
    Day2 day2 = new Day2();
    String[] input = AocHelper.readInputToLines("/day2.input");
    System.out.println("day2(1) = " + day2.runPart1(input));
    System.out.println("day2(2) = " + day2.runPart2(input));
  }

  private long runPart1(final String[] input) {
    return Arrays.stream(input).filter(this::isSafe).count();
  }

  private long runPart2(final String[] input) {
    long count = 0;
    for (String line : input) {
      String[] sliced = slice(line);
      count += (isSafe(line) || Arrays.stream(sliced).anyMatch(this::isSafe)) ? 1 : 0;
    }
    return count;
  }

  private String[] slice(String line) {
    String[] numbers = line.split(" ");

    // Generate the array of strings with one number dropped at each position
    return IntStream.range(0, numbers.length)
        .mapToObj(i -> IntStream.range(0, numbers.length)
            .filter(j -> j != i) // Exclude the index i
            .mapToObj(j -> numbers[j])
            .reduce((a, b) -> a + " " + b)
            .orElse(""))
        .toArray(String[]::new);

  }

  private boolean isSafe(String line) {
    String[] numStr = line.split(" ");
    int[] diffs = IntStream
        .range(0, numStr.length - 1)
        .map(i -> Integer.parseInt(numStr[i + 1]) - Integer.parseInt(numStr[i]))
        .toArray();

    return IntStream
        .range(0, diffs.length )
        .map(i -> (
            (i == 0 || signum(diffs[i]) == signum(diffs[i - 1])) &&
            abs(diffs[i]) > 0 &&
            abs(diffs[i]) < 4) ? 1 : 0)
        .sum() == diffs.length;

  }



}
