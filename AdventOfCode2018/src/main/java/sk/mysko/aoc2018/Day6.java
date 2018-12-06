package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day6 extends AdventOfCodeBase<Integer> {

	private static final int SIZE = 8;

	public static void main(String[] args) {
		Day6 day6 = new Day6();
		String input = day6.readFile("/Day6.input");
		System.out.println("result:" + day6.runPart1(input));
		System.out.println("result:" + day6.runPart2(input));
	}


	private class Node {
		char node;
		int dist;
		int row;
		int col;

		public Node(char node, int dist, int row, int col) {
			this.node = node;
			this.dist = dist;
			this.row = row;
			this.col = col;
		}
	}
	List<Node> baseNodes = new ArrayList<>();

	@Override
	protected Integer runPart1(String input) {
		Node[][] nodes = new Node[SIZE][SIZE];
		char name = 'a';
		for (String line : input.split("\n")) {
			int row = Integer.parseInt(line.substring(0, line.indexOf(',')).trim());
			int col = Integer.parseInt(line.substring(line.indexOf(',') + 1).trim());
			nodes[row][col] = new Node(name++, 0, row, col);
			baseNodes.add(nodes[row][col]);
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(nodes[i][j] == null ? " . " : nodes[i][j].node + "/" + nodes[i][j].dist);
			}
			System.out.println("\n");
		}

		//remove base nodes with infinite size
		for (Node n : baseNodes) {

		}

		return 0;
	}

	private int getDist(Node node, int row, int col) {
		return Math.abs(node.row - row) + Math.abs(node.col - col);
	}

	@Override
	protected Integer runPart2(String input) {
		return 0;
	}

}
