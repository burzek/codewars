package sk.araed.aoc.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class Day4 {

  private final List<Set<Integer>> winningCards = new ArrayList<>();

  public static void main(String[] args) {
    Day4 day4 = new Day4();
    String[] input = AocHelper.readInputToLines("/day4.input");

    day4.prepare(input);
    System.out.println("day4(1) = " + day4.runPart1(input));
    System.out.println("day4(2) = " + day4.runPart2(input));
  }

  private long runPart1(final String[] input) {
    long sum = 0L;
    for (int lineNo = 0; lineNo < input.length; lineNo++) {
      Set<Integer> myWinCards = winningCards.get(lineNo);
      if (!myWinCards.isEmpty()) {
        sum += ((long) 1 << myWinCards.size() - 1);
      }
    }
    return sum;
  }

  private long runPart2(final String[] input) {
    int[] copies = new int[input.length];
    Arrays.fill(copies, 1);

    for (int currentCard = 0; currentCard < copies.length; currentCard++) {
      for (int w = 0; w < winningCards.get(currentCard).size(); w++) {
        copies[currentCard + w + 1] += copies[currentCard];
      }
    }
    return Arrays.stream(copies).reduce(0, Integer::sum);

  }

  private void prepare(final String[] input) {
    for (final String line : input) {
      Set<Integer> winningCards = Arrays
          .stream(line.substring(line.indexOf(':') + 1, line.indexOf('|'))
              .split(" "))
          .filter(s -> !s.isEmpty())
          .map(s -> Integer.parseInt(s.trim()))
          .collect(Collectors.toSet());
      Set<Integer> owningCards = Arrays
          .stream(line.substring(line.indexOf('|') + 2)
              .split(" "))
          .filter(s -> !s.isEmpty())
          .map(s -> Integer.parseInt(s.trim()))
          .collect(Collectors.toSet());
      owningCards.retainAll(winningCards);
      this.winningCards.add(owningCards);
    }
  }


}
