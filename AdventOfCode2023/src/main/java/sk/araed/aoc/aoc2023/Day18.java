package sk.araed.aoc.aoc2023;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day18 {

  //  char [][] map = new char[120][120];
  int rows;
  int cols;

  public static void main(String[] args) {
    Day18 day16 = new Day18();
    String[] input = AocHelper.readInputToLines("/day16.input");
    day16.parse(input);
    System.out.println("day16(1) = " + day16.runPart1(input));
    System.out.println("day16(2) = " + day16.runPart2(input));
  }

  private void parse(final String[] input) {
    rows = input.length;
    cols = input[0].length();
  }

  enum Direction {
    N(0, -1), E(1, 0), S(0, 1), W(-1, 0);

    private final int dx;
    private final int dy;

    Direction(final int dx, final int dy) {
      this.dx = dx;
      this.dy = dy;
    }
  }

  class Beam {

    int px;
    int py;
    Direction d;

    public Beam(final int px, final int py, final Direction d) {
      this.px = px;
      this.py = py;
      this.d = d;
    }

    public void reflect(char mirror) {
      if (mirror == '/') {
        d = switch (d) {
          case N -> Direction.E;
          case E -> Direction.N;
          case S -> Direction.W;
          case W -> Direction.S;
        };
      } else if (mirror == '\\') {
        d = switch (d) {
          case N -> Direction.W;
          case E -> Direction.S;
          case S -> Direction.E;
          case W -> Direction.N;
        };
      }
    }

    public void move() {
      switch (d) {
        case N -> py--;
        case S -> py++;
        case W -> px--;
        case E -> px++;
      }
    }

    public Beam splitOrMove(final char splitter) {
      if (splitter == '-' && (d == Direction.N || d == Direction.S)) {
        d = Direction.W;
        return new Beam(px, py, Direction.E);
      } else if (splitter == '|' && (d == Direction.W || d == Direction.E)) {
        d = Direction.N;
        return new Beam(px, py, Direction.S);
      }
      return null;
    }
  }

  private long runPart1(final String[] input) {
    Set<Integer> energizedSet = new HashSet<>();
    int[] last10EnergizedCount = new int[10];
    int l10idx = 0;

    List<Beam> beams = new ArrayList<>();
    beams.add(new Beam(0, 0, Direction.E));
    boolean beamsSame = false;
    while (!beamsSame) {
      List<Beam> newBeams = new ArrayList<>();
      for (Beam b : beams) {
        energizedSet.add(b.px * 1000 + b.py);
        char mapVal = input[b.py].charAt(b.px);
        if (mapVal == '/' || mapVal == '\\') {
          b.reflect(mapVal);
        } else if (mapVal == '|' || mapVal == '-') {
          Beam newBeam = b.splitOrMove(mapVal);
          if (newBeam != null) {
            newBeams.add(newBeam);
          }
        }
      }
      beams.addAll(newBeams);

      //remove all oot-of-map beams
      beams.forEach(Beam::move);
      beams.removeIf(b -> b.px < 0 || b.px >= rows || b.py < 0 || b.py >= cols);
      last10EnergizedCount[l10idx++ % 10] = energizedSet.size();
      beamsSame = Arrays.stream(last10EnergizedCount).distinct().count() <= 1;
    }
    return energizedSet.size();
  }


  private long runPart2(final String[] input) {
    return 0L;
  }

}

