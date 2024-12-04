package sk.araed.aoc.aoc2024;


public class Day4 {
  private final static char[] XMAS = new char[] {'X', 'M', 'A', 'S'};

  public static void main(String[] args) {
    Day4 day4 = new Day4();
    char[][] map = AocHelper.readInputToMat("/day4.input");
    System.out.println("day4(1) = " + day4.runPart1(map));
    System.out.println("day4(2) = " + day4.runPart2(map));
  }

  private long runPart1(final char[][] map) {
    int count = 0;
    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[0].length; col++) {
        count += (
            isXMas(map, row, col, -1, 0) +
            isXMas(map, row, col, -1, 1) +
            isXMas(map, row, col, 0, 1) +
            isXMas(map, row, col, 1, 1) +
            isXMas(map, row, col, 1, 0) +
            isXMas(map, row, col, 1, -1) +
            isXMas(map, row, col, 0, -1) +
            isXMas(map, row, col, -1, -1));
      }
    }
    return count;
  }

  private long runPart2(final char[][] map) {
    int count = 0;
    for (int row = 1; row < map.length - 1; row++) {
      for (int col = 1; col < map[0].length - 1; col++) {
        count += isX_Mas(map, row, col);
      }
    }
    return count;
  }



  private int isXMas(char[][] map, int row, int col, int rowStep, int colStep) {
    boolean found = true;
    int r = row;
    int c = col;
    for (int i = 0; i < 4; i++) {
      if (r < 0 || r > map.length - 1 || c < 0 || c > map[0].length - 1) {
        return 0;
      }
      found &= map[r][c] == XMAS[i];
      r += rowStep;
      c += colStep;
    }
    return found ? 1 : 0;
  }

  private int isX_Mas(char[][] map, int row, int col) {
    return (map[row][col] == 'A' &&
        (
          (map[row - 1][col - 1] == 'M' && map[row - 1][col + 1] == 'M' &&
              map[row + 1][col - 1] == 'S' && map[row + 1][col + 1] == 'S') ||
          (map[row - 1][col - 1] == 'S' && map[row - 1][col + 1] == 'S' &&
              map[row + 1][col - 1] == 'M' && map[row + 1][col + 1] == 'M') ||
          (map[row - 1][col - 1] == 'S' && map[row - 1][col + 1] == 'M' &&
              map[row + 1][col - 1] == 'S' && map[row + 1][col + 1] == 'M') ||
          (map[row - 1][col - 1] == 'M' && map[row - 1][col + 1] == 'S' &&
              map[row + 1][col - 1] == 'M' && map[row + 1][col + 1] == 'S')
    )) ? 1 : 0;
  }

}
