package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day14 extends AdventOfCodeBase<Integer> {


	private String runPart1(int receipts) {
		List<Integer> r = new ArrayList<>();
		r.add(3);
		r.add(7);
		int elf1 = 0;
		int elf2 = 1;
		while (r.size() != receipts + 10) {
			int newRec = r.get(elf1) + r.get(elf2);
			if (newRec >= 10) {
				r.add(newRec / 10);
			}
			r.add(newRec % 10);

			elf1 += 1 + r.get(elf1);
			elf1 %= r.size();

			elf2 += 1 + r.get(elf2);
			elf2 %= r.size();
		}


		return r.subList(receipts, receipts + 10).stream().map(String::valueOf).collect(Collectors.joining());

	}


	private int runPart2(String stopNumber) {
		StringBuilder b = new StringBuilder(10000000);
		b.append("37");
		int elf1 = 0;
		int elf2 = 1;
		while (true) {
			int newRec = (b.charAt(elf1) - '0') + (b.charAt(elf2) - '0');
			if (newRec >= 10) {
				b.append(newRec / 10);
			}
			b.append(newRec % 10);

			int index = b.indexOf(stopNumber);
			if (index != -1) {
				return index;
			}

			elf1 += 1 + (b.charAt(elf1) - '0');
			elf1 %= b.length();

			elf2 += 1 + (b.charAt(elf2) - '0');
			elf2 %= b.length();
			if (b.length() % 10000 == 0) {
				System.out.println(b.length());
			}

		}

	}




	public static void main(String[] args) {
		Day14 day = new Day14();
		System.out.println("result:" + day.runPart1(920831));
		System.out.println("result:" + day.runPart2("920831"));
	}
}
