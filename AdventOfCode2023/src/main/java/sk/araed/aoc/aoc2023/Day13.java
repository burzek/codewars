package sk.araed.aoc.aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

  public static void main(String[] args) {
    Day13 day13 = new Day13();
    String[] input = AocHelper.readInputToLines("/day13.input");
    System.out.println("day13(1) = " + day13.runPart1(input));
    System.out.println("day13(2) = " + day13.runPart2(input));
  }


  private long runPart1(String[] input) {
    long sum = 0L;
    List<String> area = new ArrayList<>();
    for (String line : input) {
      if (line.isEmpty()) {
        int pVal = testPalindrome(area.toArray(new String[]{}));
        sum += pVal;
        if (pVal == 0) {
          sum += 100L * testPalindrome(transpose(area).toArray(new String[]{}));
        }
        area.clear();
      } else {
        area.add(line);
      }
    }

    int pVal = testPalindrome(area.toArray(new String[]{}));
    sum += pVal;
    if (pVal == 0) {
      sum += 100L * testPalindrome(transpose(area).toArray(new String[]{}));
    }

    return sum;
  }


  private long runPart2(String[] lines) {
    return 0L;
  }


  private List<String> transpose(final List<String> area) {
    List<String> ret = new ArrayList<>();
    for (int c = 0; c < area.get(0).length(); c++) {
      StringBuilder s = new StringBuilder();
      for (String line : area) {
        s.append(line.charAt(c));
      }
      ret.add(s.toString());
    }
    return ret;
  }


  private int testPalindrome(String[] lines) {
    int pIdx = -1;
    for (String line : lines) {
      final String s = line.replaceAll("#", "1").replaceAll("\\.", "0");
     
      int p = manachers(s.toCharArray());
      if (pIdx == -1) {
        pIdx = p;
      } else {
        if (p != pIdx) {
          pIdx = 0;
        }
      }
    }
    return Math.max(pIdx, 0);
  }


  private int manachers(char[] str) {
    char[] arr = preProcess(str);
    int n = arr.length, c = 0, r = 0;
    int[] p = new int[n];

    int pMaxIdx = 0;
    for (int i = 1; i < n - 1; i++) {
      int invI = 2 * c - i;
      p[i] = r > i ? Math.min(r - i, p[invI]) : 0;
      while (arr[i + 1 + p[i]] == arr[i - 1 - p[i]]) {
        p[i]++;
      }
      if (i + p[i] > r) {
        c = i;
        r = i + p[i];
      }
      if (p[i] > p[pMaxIdx]) {
        pMaxIdx = i;
      }
    }

    return pMaxIdx / 2;
  }

  // Pre-process the string by injecting separator characters.
  // We do this to account for even length palindromes, so we can
  // assign them a unique center, for example: "abba" -> "^#a#b#b#a#$"
  private char[] preProcess(char[] str) {
    char[] arr = new char[str.length * 2 + 3];
    arr[0] = '^';
    for (int i = 0; i < str.length; i++) {
      arr[i * 2 + 1] = '#';
      arr[i * 2 + 2] = str[i];
    }
    arr[arr.length - 2] = '#';
    arr[arr.length - 1] = '$';
    return arr;
  }
}

