package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day10 extends AdventOfCodeBase<Integer> {
	final static private int SIZE = 256;

	public static void main(String[] args) {
		Day10 day10 = new Day10();
		String input = day10.readFile("/Day10.input");
		System.out.println("result:" + day10.runPart1(input));
		input = day10.readFile("/Day10b.input");
		System.out.println("result:" + day10.runPart2(input));


	}



	@Override
	protected Integer runPart1(String input) {
		int[] list = initArray();
		int pos = 0;
		int skip = 0;
		List<Integer> lenArr = Arrays.stream(input.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
		for (int len : lenArr) {
			int[] subArr = getReversedSubArray(list, pos, len);
			for (int i = 0; i < subArr.length; i++) {
				list[(pos + i) % list.length] = subArr[i];
			}
			pos = (pos + len + skip) % list.length;
			skip++;
		}

		return list[0] * list[1];
	}

	@Override
	protected Integer runPart2(String input) {
		int[] list = initArray();
		int pos = 0;
		int skip = 0;
		List<Integer> lenArr = getLengths(input);
		for (int rep = 0; rep < 64; rep++) {
			for (int len : lenArr) {
				int[] subArr = getReversedSubArray(list, pos, len);
				for (int i = 0; i < subArr.length; i++) {
					list[(pos + i) % list.length] = subArr[i];
				}
				pos = (pos + len + skip) % list.length;
				skip++;
			}
		}

		//calculate hash
		int[] hash = new int[16];
		for (int i = 0; i < 16; i++) {
			hash[i] = list[i * 16];
			for (int j = 1; j < 16; j++) {
				hash[i] ^= list[i * 16 + j];
			}
		}

		for (int i = 0; i < 16; i++) {
			System.out.printf("%02x", hash[i]);
		}
		System.out.println();
		return 0;
	}

	private List<Integer> getLengths(String input) {
		List<Integer> lengths = new ArrayList<>();
		input.chars().forEach(lengths::add);
		lengths.addAll(Stream.of(17, 31, 73, 47, 23).collect(Collectors.toList()));
		return lengths;
	}

	private int[] getReversedSubArray(int[] list, int pos, int len) {
		int[] subArr = new int[len];
		if (pos + len > list.length) {
			System.arraycopy(list, pos, subArr, 0, list.length - pos);
			System.arraycopy(list, 0, subArr, list.length - pos, len - (list.length - pos));
		} else {
			System.arraycopy(list, pos, subArr, 0, len);
		}

		for (int i = 0; i < subArr.length / 2; i++) {
			int tmp = subArr[subArr.length - i - 1];
			subArr[subArr.length - i - 1] = subArr[i];
			subArr[i] = tmp;
		}
		return subArr;
	}

	private int[] initArray() {
		int[] list = new int[SIZE];
		for  (int i = 0; i < SIZE; i++) {
			list[i] = i;
		}
		return list;
	}


}
