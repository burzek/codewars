package sk.araed.aoc.aoc2023;

import java.util.HashMap;
import java.util.Map;

public class Day10 {

  public static void main(String[] args) {
    Day10 day10 = new Day10();
    String[] input = AocHelper.readInputToLines("/day10.input");
    System.out.println("day10(1) = " + day10.runPart1(input));
    System.out.println("day10(2) = " + day10.runPart2(input));
  }


  private long runPart1(String[] input) {
    int init_x = 31;
    int init_y = 26;

    int c = 0;
    int x = init_x;
    int y = init_y;
    int dir_x = 0;
    int dir_y = 1;
    do {
      c++;
      switch (input[y].charAt(x)) {
        case 'L':
          if (dir_y == 1) {
            dir_y = 0;
            dir_x = 1;
          } else {
            dir_x = 0;
            dir_y = -1;
          }
          break;
        case 'J' :
          if (dir_y == 1) {
            dir_y = 0;
            dir_x = -1;
          } else {
            dir_x = 0;
            dir_y = -1;
          }
          break;
        case '7':
          if (dir_y == -1) {
            dir_y = 0;
            dir_x = -1;
          } else {
            dir_x = 0;
            dir_y = 1;
          }
          break;
        case 'F':
          if (dir_y == -1) {
            dir_y = 0;
            dir_x = 1;
          } else {
            dir_x = 0;
            dir_y = 1;
          }
          break;
      }

      x += dir_x;
      y += dir_y;
    } while (x != init_x || y != init_y);

    return c/2;
  }


  private long runPart2(String[] lines) {
    return 0L;
  }

}

