package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day14 extends AdventOfCodeBase<Integer> {
	final static private int SIZE = 256;

	public static void main(String[] args) {
		Day14 day14 = new Day14();
		String input = day14.readFile("/Day14.input");
		System.out.println("result:" + day14.runPart1(input));
		input = day14.readFile("/Day14b.input");
		System.out.println("result:" + day14.runPart2(input));

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


	private String getKnotHash(String input) {
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

		StringBuilder str = new StringBuilder();
		Formatter fmt = new Formatter(str);
		for (int i = 0; i < 16; i++) {
			fmt = fmt.format("%02x", hash[i]);
		}
		return str.toString();
	}

	@Override
	protected Integer runPart1(String input) {
		int count = 0;
		for (int i = 0; i < 128; i++) {
			String hexa = getKnotHash(input + "-" + i);
			for (int h = 0; h < hexa.length(); h++) {
				int val = Integer.parseInt(String.valueOf(hexa.charAt(h)), 16);
				count += Integer.bitCount(val);
			}
		}
		return count;
	}

	@Override
	protected Integer runPart2(String input) {

		return 0;
	}




}
