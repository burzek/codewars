import java.util.Arrays;

/**
 * @author boris.brinza 01.dec.2020
 */
public class Day1 extends AdventOfCodeBase<Integer> {

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        String input = day1.readFile("/Day1.input");

        Integer[] nums = Arrays
                .stream(input.split("\n"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        System.out.println("result:" + day1.runPart1(nums));
        System.out.println("result:" + day1.runPart2(nums));
    }

    private int runPart1(Integer[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == 2020) {
                    return nums[i] * nums[j];
                }
            }
        }
       return -1;
    }

    private int runPart2(Integer[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    if (nums[i] + nums[j] + nums[k] == 2020) {
                        return nums[i] * nums[j] * nums[k];
                    }
                }
            }
        }

        return -1;
    }

}
