package sk.araed.aoc.aoc2023;

import static java.util.Objects.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day15 {

  public static void main(String[] args) {
    Day15 day15 = new Day15();
    String[] input = AocHelper.readInputToLines("/day15.input");
    System.out.println("day14(1) = " + day15.runPart1(input));
    System.out.println("day14(2) = " + day15.runPart2(input));
  }

  private long runPart1(final String[] input) {
    String[] parts = input[0].split(",");
    return Arrays.stream(parts).map(this::hash).reduce(Integer::sum).orElse(0);
  }

  record Lens(String name, int fLen) {}

  private long runPart2(final String[] input) {
    //map <boxID -> map of <name -> focal len> (for fast removal)>
    Map<Integer, Map<String, Integer>> boxes = new TreeMap<>();

    //fill boxes
    String[] parts = input[0].split(",");
    for (String p : parts) {
      String splitter = p.indexOf('=') >= 0 ? "=" : "-";
      String[] nameWithFocal = p.split(splitter);
      int box = hash(nameWithFocal[0]);
      boxes.putIfAbsent(box, new LinkedHashMap<>());
      if (nameWithFocal.length == 1) {
        boxes.get(box).remove(nameWithFocal[0]);
      } else {
        boxes.get(box).put(nameWithFocal[0], Integer.parseInt(nameWithFocal[1]));
      }
    }


   return  boxes.keySet().stream().map(b -> {
      long boxSum = 0L;
      int idx = 1;
      for (final Integer focal : boxes.get(b).values()) {
        boxSum += ((b + 1) * ((long) (idx++) * focal));
      }
      return boxSum;
    }).mapToLong(c -> c).sum();
  }

  public int hash(String val) {
    return val.chars().reduce(0, (a, b) -> ((a + b) * 17) % 256);
  }
}

