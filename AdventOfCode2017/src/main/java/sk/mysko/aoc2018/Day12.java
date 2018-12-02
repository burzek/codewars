package sk.mysko.aoc2018;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day12 extends AdventOfCodeBase<Integer> {



	public static void main(String[] args) {
		Day12 day12 = new Day12();
		String input = day12.readFile("/Day12.input");
		System.out.println("result:" + day12.runPart1(input));
		input = day12.readFile("/Day12b.input");
		System.out.println("result:" + day12.runPart2(input));


	}

	@Override
	protected Integer runPart1(String input) {
		List<List<Integer>> conn = parse(input);
		return countConnections(conn, 0, new HashSet<>());
	}


	@Override
	protected Integer runPart2(String input) {
		List<List<Integer>> conn = parse(input);

		int groups = 0;
		for (int i = 0; i < conn.size(); i++) {
			Set<Integer> processedRows = new HashSet<>();
			int count = countConnections(conn, i, processedRows);
			if (count > 0) {
				groups++;
			}
			for (Integer processedRow : processedRows) {
				conn.get(processedRow).clear();
			}
		}
		return groups;
	}

	private Integer countConnections(List<List<Integer>> conn, int currentRow, Set<Integer> processedRows) {
		List<Integer> row = conn.get(currentRow);
		int count = 0;
		for (Integer val : row) {
			if (!processedRows.contains(val)) {
				processedRows.add(val);
				count ++;
				count += countConnections(conn, val, processedRows);
			}
		}
		return count;
	}

	private List<List<Integer>> parse(String input) {
		List<List<Integer>> conn = new LinkedList<List<Integer>>();
		int nodeIndex = 0;
		for (String s : input.split("\n")) {
			List<Integer> row = new LinkedList<>();
			conn.add(row);
			for (String cl : s.substring(s.indexOf(">") + 1).split(",")) {
				row.add(Integer.parseInt(cl.trim()));
			}
			nodeIndex++;
		}
		return conn;
	}


}
