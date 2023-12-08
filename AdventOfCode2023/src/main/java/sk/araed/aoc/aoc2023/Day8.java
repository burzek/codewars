package sk.araed.aoc.aoc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

public class Day8 {


  final Map<Integer, Integer[]> map = new HashMap<>();
  String path = null;
  int currentNode = -1;



  public static void main(String[] args) {
    Day8 day8 = new Day8();
    String[] input = AocHelper.readInputToLines("/day8.input");
    day8.parse(input);
    System.out.println("day8(1) = " + day8.runPart1());
    System.out.println("day8(2) = " + day8.runPart2(input));
  }

  private void parse(final String[] lines) {
    path = lines[0].trim();
    for (int i = 2; i < lines.length; i++) {
      int nH = lines[i].substring(0, 3).hashCode();
      if (lines[i].startsWith("AAA")) {
        currentNode = nH;
      }
      map.put(nH, new Integer[] {
          lines[i].substring(7, 10).hashCode(), lines[i].substring(12, 15).hashCode()
      });
    }
  }

  private long runPart1() {
    int finalHash = "ZZZ".hashCode();
    long pathLen = 0L;
    while (currentNode != finalHash) {
      char dir = path.charAt((int) (pathLen++ % path.length()));
      currentNode = dir == 'L' ? map.get(currentNode)[0] : map.get(currentNode)[1];
    }
    return pathLen;
  }


  private long runPart2(String[] lines) {
    return 0L;
  }

}

