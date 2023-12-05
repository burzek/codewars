package sk.araed.aoc.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class Day5 {


  record Mapper(long dst, long src, long size) {};

  private Long[] seeds;
  List<List<Mapper>> mappers = new ArrayList<>();

  public static void main(String[] args) {
    Day5 day5 = new Day5();
    String[] input = AocHelper.readInputToLines("/day5.input");

    day5.parse(input);

    System.out.println("day5(1) = " + day5.runPart1(input));
    System.out.println("day5(2) = " + day5.runPart2(input));
  }

  private long runPart1(final String[] input) {
    return Arrays.stream(seeds).map(this::toLocation).min(Comparator.naturalOrder()).orElse(0L);
  }

  private long toLocation(Long seed) {
    for (int i = 0; i < 7; i++) {
      seed = map(i, seed);
    }
    return seed;
  }

  private long runPart2(final String[] input) {
    return 0L;
  }


  private void parse(final String[] input) {
    int mapIdx = -1;
    for (String line : input) {
      if (line.startsWith("seeds: ")) {
        seeds = Arrays.stream(line.substring(7).split(" ")).map(Long::parseLong).toArray(Long[]::new);
      } else if (line.isEmpty()) {
        mapIdx++;
        mappers.add(new ArrayList<>());
      } else if (Character.isDigit(line.charAt(0))) {
        String[] dsc = line.split(" ");
        mappers.get(mapIdx).add(new Mapper(Long.parseLong(dsc[0]), Long.parseLong(dsc[1]), Long.parseLong(dsc[2])));
      }
    }

  }

  private long map(int mapIndex, long src) {
    List<Mapper> mapperForSection = mappers.get(mapIndex);
    for (Mapper m : mapperForSection) {
      if (m.src <= src && src <= m.src + m.size) {
        return m.dst + (src - m.src);
      }
    }
    return src;
  }




}

