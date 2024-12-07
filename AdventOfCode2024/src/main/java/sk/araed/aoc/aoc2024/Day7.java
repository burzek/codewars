package sk.araed.aoc.aoc2024;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Day7 {

  public static void main(String[] args) {
    Day7 day7 = new Day7();
    String[] lines = AocHelper.readInputToLines("/day7.input");
    System.out.println("day7(1) = " + day7.runPart1(lines));
    //System.out.println("day7(2) = " + day7.runPart2(lines));
  }

  private long runPart1(String[] lines) {
    long sum = 0;

    for (String line : lines) {
      long result = parseResult(line);
      long[] nums = parseNumbers(line);

      if (leadToResult(nums[0], result, Arrays.copyOfRange(nums, 1, nums.length))) {
        sum += result;
      }



    }
    return sum;
  }

  private boolean leadToResult(long acc, long result, long[] nums) {
    if (nums.length == 0) {
      return acc == result;
    } else {
      boolean ok = leadToResult(acc + nums[0], result, Arrays.copyOfRange(nums, 1, nums.length));
      if (!ok) {
        ok = leadToResult(acc * nums[0], result, Arrays.copyOfRange(nums, 1, nums.length));
      }
      return ok;
    }
  }

  private long[] parseNumbers(final String line) {
    return Stream.of(
            line.substring(line.indexOf(':') + 1)
                .trim()
                .split(" "))
        .mapToLong(Long::parseLong)
        .toArray();
  }

  private long parseResult(final String line) {
    return Long.parseLong(line.substring(0, line.indexOf(':')));
  }


}