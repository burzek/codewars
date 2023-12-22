package sk.araed.aoc.aoc2023;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day18 {

  boolean [][] map = new boolean[800][800];
  int rows;
  int cols;

  public static void main(String[] args) {
    Day18 day18 = new Day18();
    String[] input = AocHelper.readInputToLines("/day18.input");
    day18.parse(input);
    System.out.println("day18(1) = " + day18.runPart1(input));
    System.out.println("day18(2) = " + day18.runPart2(input));
  }

  private void parse(final String[] input) {

  }

  enum Dir {
    U,D,L,R;
  }

  private long runPart1(final String[] input) {
    int r = 250;
    int c = 50;     //heuristic :)
    int count = 0;
    for (String line : input) {
      Dir d = Dir.valueOf(line.substring(0, 1));
      int size = Integer.parseInt(line.substring(2, line.indexOf(' ', 2)));
      for (int n = 0; n < size; n++) {
        map[r][c] = true;
        count++;

        c += switch (d) {
          case R -> 1;
          case L -> -1;
          default -> 0;
        };
        r += switch (d) {
          case U -> -1;
          case D -> 1;
          default -> 0;
        };
        cols = Integer.max(cols, c);
        rows = Integer.max(rows, r);
      }
    }
    printMap();

    //fill
    for (r = 0; r < rows; r++) {
      c = 0;
      while (c < cols) {
        while (c < cols && !map[r][c]) {
          c++;
        }
        c++;
        //inside
        while (c < cols && !map[r][c]) {
          map[r][c] = true;
          c++;
        }

        c++;

      }
    }
    printMap();



    return count;
  }

  private void printMap() {
    for (int r = 0; r < 40; r++) {//rows + 1; r++) {
//      System.out.print(r);
      for (int c = 0; c < cols + 1; c++) {
        System.out.print(map[r][c] ? "#" : ".");
      }
      System.out.println();
    }
  }


  private long runPart2(final String[] input) {
    return 0L;
  }

}

