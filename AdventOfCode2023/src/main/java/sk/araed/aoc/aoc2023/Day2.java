package sk.araed.aoc.aoc2023;

import java.util.Arrays;
import java.util.Comparator;

public class Day2 {

  public static void main(String[] args) {
    Day2 day2 = new Day2();
    String[] input = AocHelper.readInputToLines("/day2.input");
    System.out.println("day2(1) = " + day2.runPart1(input));
    System.out.println("day2(2) = " + day2.runPart2(input));
  }

  private int runPart1(final String[] input) {
    return Arrays.stream(input)
        .map(this::processLine)
        .reduce(Integer::sum)
        .orElse(0);
  }

  private int processLine(final String line) {
    int gameNo = Integer.parseInt(line.substring(5, line.indexOf(':')).trim());
    return Arrays.stream(line.substring(line.indexOf(':') + 1).split("[,;]"))
        .anyMatch(s -> {
          int cubes = Integer.parseInt(s.replaceAll("[\\D.]", ""));
          return
              (s.contains("red") && cubes > 12) ||
              (s.contains("green") && cubes > 13) ||
              (s.contains("blue") && cubes > 14);
        }) ? 0 : gameNo;
  }

  private int runPart2(final String[] input) {
    return Arrays.stream(input)
        .map(this::processLineForPart2)
        .reduce(Integer::sum)
        .orElse(0);
  }

  private int processLineForPart2(final String line) {
    int gameNo = Integer.parseInt(line.substring(5, line.indexOf(':')).trim());
    final String[] split = line.substring(line.indexOf(':') + 1).split("[,;]");
    int minRed = Arrays.stream(split)
        .filter(s -> s.contains("red"))
        .map(s -> Integer.parseInt(s.replaceAll("[\\D.]", "")))
        .max(Comparator.naturalOrder())
        .orElse(0);
    int minGreen = Arrays.stream(split)
        .filter(s -> s.contains("green"))
        .map(s -> Integer.parseInt(s.replaceAll("[\\D.]", "")))
        .max(Comparator.naturalOrder())
        .orElse(0);
    int minBlue = Arrays.stream(split)
        .filter(s -> s.contains("blue"))
        .map(s -> Integer.parseInt(s.replaceAll("[\\D.]", "")))
        .max(Comparator.naturalOrder())
        .orElse(0);
    return minRed*minBlue*minGreen;
  }

}
