package sk.araed.aoc.aoc2023;

import java.util.Arrays;
import java.util.Comparator;

public class Day1 extends AocBase<Integer> {

  public static void main(String[] args) {
    Day1 day1 = new Day1();
    String[] input = day1.readInputToLines("/day1.input");
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
    final String[] patterns = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    int minIdx;
    do {
      minIdx = Arrays.stream(patterns).map(line::indexOf).filter(idx -> idx != -1)
          .min(Comparator.naturalOrder())
          .orElse(-1);
      if (minIdx != -1) {
        int patternIdx = switch (line.substring(minIdx, minIdx + 2)) {
          case "on" -> 0;
          case "tw" -> 1;
          case "th" -> 2;
          case "fo" -> 3;
          case "fi" -> 4;
          case "si" -> 5;
          case "se" -> 6;
          case "ei" -> 7;
          case "ni" -> 8;
          default -> throw new IllegalStateException("Unexpected value: " + line.substring(minIdx, 2));
        };
        line = line.replaceFirst(patterns[patternIdx], Integer.toString(patternIdx + 1));
      }
    } while (minIdx != -1);
    return line;
  }


}
