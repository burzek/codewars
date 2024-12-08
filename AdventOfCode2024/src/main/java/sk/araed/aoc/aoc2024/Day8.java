package sk.araed.aoc.aoc2024;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Day8 {

  private record Position(int row, int col) {};

  public static void main(String[] args) {
    Day8 day8 = new Day8();
    char[][] map = AocHelper.readInputToMat("/day8.input");
    int maxRow = map.length;
    int maxCol = map[0].length;

    Map<Character, List<Position>> ant = parseAntennasMap(map);
    System.out.println("day8(1) = " + day8.runPart1(ant, maxRow, maxCol));
    System.out.println("day8(2) = " + day8.runPart2(ant, maxRow, maxCol));
  }

  private long runPart1(Map<Character, List<Position>> ant, int maxRow, int maxCol) {
    Set<Position> antinodes = new HashSet<>();

    ant.values().forEach(positions -> {
      for (int i = 0; i < positions.size(); i++) {
        for (int j = i + 1; j < positions.size(); j++) {
          addAntiNode(antinodes, positions.get(i), positions.get(j), maxRow, maxCol);
        }
      }
    });
    return antinodes.size();
  }


  private long runPart2(Map<Character, List<Position>> ant, int maxRow, int maxCol) {
    Set<Position> antinodes = new HashSet<>();

    ant.values().forEach(positions -> {
      for (int i = 0; i < positions.size(); i++) {
        for (int j = i + 1; j < positions.size(); j++) {
          addAntiNodeHarmonical(antinodes, positions.get(i), positions.get(j), maxRow, maxCol);
        }
      }
    });
    return antinodes.size();
  }

  private void addAntiNode(Set<Position> antinodes, Position from, Position to, int maxRow, int maxCol) {
    int dr = to.row - from.row;
    int dc = to.col - from.col;

    if (from.row - dr >= 0 && from.row - dr < maxRow && from.col - dc >= 0 && from.col - dc < maxCol) {
      antinodes.add(new Position(from.row - dr, from.col - dc));
    }
    if (to.row + dr >= 0 && to.row + dr < maxRow && to.col + dc >= 0 && to.col + dc < maxCol) {
      antinodes.add(new Position(to.row + dr, to.col + dc));
    }
  }

  private void addAntiNodeHarmonical(Set<Position> antinodes, Position from, Position to, int maxRow, int maxCol) {
    int dr = to.row - from.row;
    int dc = to.col - from.col;

    int row = from.row;
    int col = from.col;

    while (row - dr >= 0 && row - dr < maxRow && col - dc >= 0 && col - dc < maxCol) {
      antinodes.add(new Position(row - dr, col - dc));
      row -= dr;
      col -= dc;
    }

    row = to.row;
    col = to.col;
    while (row + dr >= 0 && row + dr < maxRow && col + dc >= 0 && col + dc < maxCol) {
      antinodes.add(new Position(row + dr, col + dc));
      row += dr;
      col += dc;
    }
  }



  private static Map<Character, List<Position>> parseAntennasMap(char[][] map) {
    //parse
    Map<Character, List<Position>> ant = new HashMap<>();
    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[row].length; col++) {
        if (Character.isLetterOrDigit(map[row][col])) {
          ant.putIfAbsent(map[row][col], new ArrayList<>());
          ant.get(map[row][col]).add(new Position(row, col));
        }
      }
    }
    return ant;
  }


}