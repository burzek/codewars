package sk.araed.aoc.aoc2024;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day6 {

  public static void main(String[] args) {
    Day6 day6 = new Day6();
    char[][] map = AocHelper.readInputToMat("/day6.input");
    int[] xy = day6.findPlayer(map);
    System.out.println("day6(1) = " + day6.runPart1(map, xy[0], xy[1]));
    //System.out.println("day6(2) = " + day6.runPart2(lines));
  }

  private long runPart1(char[][] map, int r, int c) {
    Set<Integer> positions = new HashSet<>();

    int dr = -1;
    int dc = 0;
    while (r >= 0 && r < map.length && c >= 0 && c < map[0].length) {
      positions.add(codePosition(r, c));
      if (r + dr < 0 || c + dc < 0 || r +  dr >= map.length || c + dc >= map[0].length) {
        return positions.size();
      }

      if (map[r + dr][c + dc] == '#') {
        if (dr == 0) {
          dr = dc;
          dc = 0;
        } else {
          dc = -dr;
          dr = 0;
        }
      }
      r += dr;
      c += dc;
    }
    return -1;
  }

  private Integer codePosition(int x, int y) {
    return x * 10000 + y;
  }

  private int[] findPlayer(char[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == '^') {
          return new int[] {i, j};
        }
      }
    }
    return new int[] {-1, -1};
  }


}