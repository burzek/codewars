package sk.araed.aoc.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day6 {

  long[] time;
  long[] max;

  public static void main(String[] args) {
    Day6 day6 = new Day6();
    String[] input = AocHelper.readInputToLines("/day6.input");
    day6.parse(input);
    System.out.println("day6(1) = " + day6.runPart1());
    System.out.println("day6(2) = " + day6.runPart2());
  }

  private void parse(String[] lines) {
    time = Arrays.stream(lines[0].substring(10).split(" "))
        .filter(Predicate.not(String::isEmpty))
        .mapToLong(s -> Integer.parseInt(s.trim()))
        .toArray();
    max = Arrays.stream(lines[1].substring(10).split(" "))
        .filter(Predicate.not(String::isEmpty))
        .mapToLong(s -> Integer.parseInt(s.trim()))
        .toArray();
  }

  private long runPart1() {
   long sum = 1L;
   for (int idx = 0; idx < time.length; idx++) {
     int fi = 0;
     while (fi * (time[idx] - fi) <= max[idx]) {
       fi++;
     }
     sum *= (time[idx] + 1) - 2L * fi;
   }
   return sum;
  }

  private long runPart2() {
    time = new long[] {Long.parseLong(LongStream.of(time).mapToObj(String::valueOf).collect(Collectors.joining()))};
    max = new long[] {Long.parseLong(LongStream.of(max).mapToObj(String::valueOf).collect(Collectors.joining()))};
    return runPart1();
  }




}

