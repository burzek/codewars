package sk.araed.aoc.aoc2024;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day5 {

  public static void main(String[] args) {
    Day5 day5 = new Day5();
    String[] lines = AocHelper.readInputToLines("/day5.input");
    System.out.println("day5(1) = " + day5.runPart1(lines));
    System.out.println("day5(2) = " + day5.runPart2(lines));
  }

  private long runPart1(String[] lines) {
    String line;

    Map<String, Set<String>> order  = new HashMap<>();

    Iterator<String> iterator = Arrays.stream(lines).iterator();
    while (!(line = iterator.next()).isEmpty()) {
      final String[] parts = line.split("\\|");
      if (!order.containsKey(parts[0])) {
        order.put(parts[0], new HashSet<>());
      }
      order.get(parts[0]).add(parts[1]);
    }

    long sum = 0;
    while (iterator.hasNext()) {
      List<String> pages = Arrays
          .stream(iterator.next().split(","))
          .toList();

      boolean breakRule = false;
      for (int i = 0; i < pages.size(); i++) {
        String testPage = pages.get(i);
        for (int j = i + 1; j < pages.size(); j++) {
          breakRule |= order.getOrDefault(pages.get(j), new HashSet<>()).contains(testPage);
        }
      }
      sum += breakRule ? 0 : Integer.parseInt(pages.get(pages.size() / 2));

    }

    return sum;
  }

  private long runPart2(String[] lines) {
    String line;

    Map<String, Set<String>> order  = new HashMap<>();

    Iterator<String> iterator = Arrays.stream(lines).iterator();
    while (!(line = iterator.next()).isEmpty()) {
      final String[] parts = line.split("\\|");
      if (!order.containsKey(parts[0])) {
        order.put(parts[0], new HashSet<>());
      }
      order.get(parts[0]).add(parts[1]);
    }

    long sum = 0;
    while (iterator.hasNext()) {
      List<String> pages = new ArrayList<>();
      pages.addAll(Arrays
          .stream(iterator.next().split(","))
          .toList());

      boolean fixed = false;
      boolean breakRule;
      do {
        breakRule = false;
        for (int i = 0; i < pages.size(); i++) {
          String testPage = pages.get(i);
          for (int j = i + 1; j < pages.size(); j++) {
            if (order.getOrDefault(pages.get(j), new HashSet<>()).contains(testPage)) {
              pages.set(i, pages.get(j));
              pages.set(j, testPage);
              breakRule = true;
              fixed = true;
              break;
            }
          }
        }
      } while (breakRule);
      sum += fixed ? Integer.parseInt(pages.get(pages.size() / 2)) : 0;

    }

    return sum;
  }
}