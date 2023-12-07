package sk.araed.aoc.aoc2023;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day7 {

  static class Hand {
    String cards;
    long bid;
    int value;

    public Hand(final String cards, final Long bid, final Integer value) {
      this.cards = cards;
      this.bid = bid;
      this.value = value;
    }
  };
  final String cards = "AKQJT987654321";
  final List<Hand> hands = new ArrayList<>();

  public static void main(String[] args) {
    Day7 day7 = new Day7();
    String[] input = AocHelper.readInputToLines("/day7.input");
    day7.parse(input);
    System.out.println("day7(1) = " + day7.runPart1());
    System.out.println("day7(2) = " + day7.runPart2(input));
  }


  private void parse(final String[] lines) {
    for (String line : lines) {
      hands.add(new Hand(line.substring(0, 5), Long.parseLong(line.substring(6)), getScore(line.substring(0, 5))));
    }
  }

  private long runPart1() {
    hands.sort((h1, h2) -> {
      if (h1.value == h2.value) {
        int i = 0;
        while (h1.cards.charAt(i) == h2.cards.charAt(i)) i++;
        return cards.indexOf(h2.cards.charAt(i)) - cards.indexOf(h1.cards.charAt(i));
      } else {
        return h1.value - h2.value;
      }
    });

    return LongStream
        .range(0, hands.size())
        .reduce(0L, (sum, idx) -> sum + hands.get((int) idx).bid * (idx + 1));

  }


  private long runPart2(String[] lines) {
    return 0L;
  }

  private int getScore(final String hand) {
    int[] cardCount = new int[14];
    for (int c = 0; c < 5; c++) {
      int val = cards.indexOf(hand.charAt(c));
      cardCount[val]++;
    }

    int total = 0;
    for (int count : cardCount) {
      if (count == 5) {
        return 7;
      }
      if (count == 4) {
        return 6;
      }
      if (count == 3) {
        total += 3;
      } else if (count == 2) {
        total += 2;
      }
    }

    if (total == 5) {
      return 5;
    }
    if (total == 3)
      return 4;

    if (total == 4)
      return 3;

    if (total == 2)
      return 2;

    return 1;
  }





}

