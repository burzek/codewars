package sk.araed.aoc.aoc2024;


import static java.lang.Math.abs;
import static java.lang.Math.signum;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day3 {

  public static void main(String[] args) {
    Day3 day3 = new Day3();
    String input = AocHelper.readInput("/day3.input");
    System.out.println("day3(1) = " + day3.runPart1(input));
    System.out.println("day3(2) = " + day3.runPart2(input));
  }

  private long runPart1(final String input) {
    final Matcher matcher = Pattern.compile("mul\\((\\d+),(\\d+)\\)").matcher(input);
    long sum = 0L;
    while (matcher.find()) {
      sum += (Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
    }
    return sum;
  }

  private long runPart2(final String input) {
    final String updated = input
        .replaceAll("don't\\(\\)((\\s?|\\S?)+)do\\(\\)", "")
        .replaceAll("don't\\(\\).*", "");
    return runPart1(updated);
  }


}
