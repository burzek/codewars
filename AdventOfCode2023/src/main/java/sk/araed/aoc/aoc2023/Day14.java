package sk.araed.aoc.aoc2023;

import java.util.Arrays;

public class Day14 {

  public static void main(String[] args) {
    Day14 day14 = new Day14();
    String[] input = AocHelper.readInputToLines("/day14.input");
    day14.init(input);
    System.out.println("day14(1) = " + day14.runPart1());
    System.out.println("day14(2) = " + day14.runPart2(input));
  }

  int[][] map;
  private void init(String[] lines) {
    map = new int[lines.length][lines[0].length()];
    for (int r = 0; r < lines.length; r++) {
      for (int c = 0; c < lines[0].length(); c++) {
        map[r][c] = switch (lines[r].charAt(c)) {
          case '.' : yield 0;
          case 'O' : yield 1;
          case '#' : yield 2;
          default: yield 0;
        };
      }
    }
  }

  private long runPart1() {
    for (int c = 0 ; c < map[0].length; c++) {
      int r = 0;
      do {
        while (r < map.length && map[r][c] != 1)
          r++;
        while (r > 0 && r < map.length && map[r - 1][c] == 0) {
          map[r - 1][c] = 1;
          map[r][c] = 0;
          r--;
        }
        r++;

      } while (r < map.length);
    }

    printMap();
    long sum = 0L;
    for (int r = 0; r < map.length; r++) {
      sum += ((map.length - r) * Arrays.stream(map[r]).filter(x -> x == 1).count());
    }
    return sum;
  }

  private void printMap() {
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[r].length; c++) {
        System.out.print(switch (map[r][c]) {
          case 0: yield '.';
          case 1: yield 'O';
          case 2: yield '#';
          default: yield '?';
        });
      }
      System.out.println();
    }
    System.out.println();
  }


  private long runPart2(String[] lines) {
    return 0L;
  }

}

