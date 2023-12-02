package sk.araed.aoc.aoc2023;

import java.util.Arrays;
import java.util.Comparator;

public class Day1 {

  public static void main(String[] args) {
    Day1 day1 = new Day1();
    String[] input = AocHelper.readInputToLines("/day1.input");
    System.out.println("day1(1) = " + day1.runPart1(input));
    System.out.println("day1(2) = " + day1.runPart2(input));
  }

  private int runPart1(final String[] lines) {
    int sum = 0;
    for (final String line : lines) {
      final int[] numerics = line.chars()
          .filter(ci -> Character.isDigit((char) ci))
          .map(ci -> ci - '0')
          .toArray();
      sum += (numerics[0] * 10 + numerics[numerics.length - 1]);
    }
    return sum;
  }

  private int runPart2(final String[] lines) {
    final String[] fixedLines = Arrays.stream(lines)
        .map(this::fixLine)
        .toList()
        .toArray(new String[]{});
    return runPart1(fixedLines);
  }

  private String fixLine(String line) {
    return line
        .replaceAll("one", "o1e")
        .replaceAll("two", "t2o")
        .replaceAll("three", "t3e")
        .replaceAll("four", "f4r")
        .replaceAll("five", "f5e")
        .replaceAll("six", "s6x")
        .replaceAll("seven", "s7n")
        .replaceAll("eight", "e8h")
        .replaceAll("nine", "n9e");
  }


}
