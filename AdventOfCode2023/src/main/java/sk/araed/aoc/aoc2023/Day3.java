package sk.araed.aoc.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class Day3 {

  public static void main(String[] args) {
    Day3 day3 = new Day3();
    String[] input = AocHelper.readInputToLines("/day3.input");

    System.out.println("day3(1) = " + day3.runPart1(input));
    System.out.println("day3(2) = " + day3.runPart2(input));
  }

  private int runPart1(final String[] input) {
    int sum = 0;
    final String emptyLine = ".".repeat(input.length);

    for (int l = 0; l < input.length; l++) {
      final String line = input[l];
      final String prevLine = l > 0 ? input[l - 1] : emptyLine;
      final String nextLine = l < input.length - 1 ? input[l + 1] : emptyLine;

      boolean adj = false;
      int num = 0;
      //scan chars in line
      for (int c = 0; c < line.length(); c++) {
        if (Character.isDigit(line.charAt(c))) {
          num = num * 10 + (line.charAt(c) - '0');
          if (isAdjecent(c, prevLine, line, nextLine)) {
            //ok, adjecent char found in current num
            adj = true;
          }
        } else {
          if (adj) {
            sum += num;
            adj = false;
          }
          num = 0;
        }
      }
      if (adj) {  //number ends at end of line?
        sum += num;
      }

    }
    return sum;
  }

  private long runPart2(final String[] input) {
    final String emptyLine = ".".repeat(input.length);
    long sum = 0L;
    for (int l = 0; l < input.length; l++) {
      final String line = input[l];
      final String prevLine = l > 0 ? input[l - 1] : emptyLine;
      final String nextLine = l < input.length - 1 ? input[l + 1] : emptyLine;

      for (int c = 0; c < line.length(); c++) {
        if (line.charAt(c) == '*') {
          sum += getPossibleGear(c, prevLine, line, nextLine);
        }
      }
    }
    return sum;
  }

  private Long scanForGear(String line, int pos) {
    while (pos > 0 && Character.isDigit(line.charAt(pos - 1))) pos--;
    StringBuilder val = new StringBuilder();
    while (pos < line.length() && Character.isDigit(line.charAt(pos))) {
      val.append(line.charAt(pos));
      pos++;
    }

    return val.isEmpty() ? 0L : Long.parseLong(val.toString());
  }

  private boolean isAdjecent(final int col, final String prevLine, final String line, final String nextLine) {
    boolean adj = false;
    adj |= col > 0 && isMarker(prevLine.charAt(col - 1));
    adj |= col > 0 && isMarker(line.charAt(col - 1));
    adj |= col > 0 && isMarker(nextLine.charAt(col - 1));
    adj |= isMarker(prevLine.charAt(col));
    adj |= isMarker(nextLine.charAt(col));
    adj |= col < line.length() - 1 && isMarker(prevLine.charAt(col + 1));
    adj |= col < line.length() - 1 && isMarker(line.charAt(col + 1));
    adj |= col < line.length() - 1 && isMarker(nextLine.charAt(col + 1));
    return adj;
  }


  private boolean isMarker(char val) {
    return val != '.' && !Character.isDigit(val);
  }




  private long getPossibleGear(final int c, final String prevLine, final String line, final String nextLine) {
    List<Long> gears = new ArrayList<>();

    long gear = scanForGear(line, c);
    gears.add(gear);

    gear = scanForGear(line, c + 1);
    gears.add(gear);


    if (Character.isDigit(prevLine.charAt(c))) {
      gear = scanForGear(prevLine, c);
      gears.add(gear);
    } else {
      gear = scanForGear(prevLine, c);
      gears.add(gear);
      gear = scanForGear(prevLine, c + 1);
      gears.add(gear);
    }

    if (Character.isDigit(nextLine.charAt(c))) {
      gear = scanForGear(nextLine, c);
      gears.add(gear);
    } else {
      gear = scanForGear(nextLine, c);
      gears.add(gear);
      gear = scanForGear(nextLine, c + 1);
      gears.add(gear);

    }

    return gears.stream().filter(g -> g != 0L).count() == 2L ?
        gears.stream().filter(g -> g != 0).reduce(1L, (a, b) -> a * b) : 0L;
  }

}
