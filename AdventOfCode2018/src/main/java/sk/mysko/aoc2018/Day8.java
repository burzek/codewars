package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day8 extends AdventOfCodeBase<Integer> {

	private class Node {
		private List<Integer> metadata = new ArrayList<>();
		private List<Node> children = new ArrayList<>();
	}

	private Node rootNode;

	private int parse(String[] entries, int index, Node parentNode) {
		Node node = new Node();
		if (parentNode == null) {
			rootNode = node;
		} else {
			parentNode.children.add(node);
		}
		int childrenCount = Integer.parseInt(entries[index++]);
		int metaDataCount = Integer.parseInt(entries[index++]);

		for (int i = 0; i < childrenCount; i++) {
			index = parse(entries, index, node);
		}

		for (int i = 0; i < metaDataCount; i++) {
			int md = Integer.parseInt(entries[index++]);
			node.metadata.add(md);
		}
		return index;
	}

	private int sumMetadata(Node rootNode) {
		int sum = rootNode.metadata.stream().mapToInt(i -> i).sum();
		for (Node n : rootNode.children) {
			sum += sumMetadata(n);
		}
		return sum;
	}


	private int getRootNodeValue(Node rootNode) {
		if (rootNode.children.isEmpty()) {
			return rootNode.metadata.stream().mapToInt(i -> i).sum();
		} else {
			int val = 0;
			for (int md : rootNode.metadata) {
				if (md > 0 && md <= rootNode.children.size()) {
					val += getRootNodeValue(rootNode.children.get(md - 1));
				}
			}
			return val;
		}

	}

	protected Integer runPart1(String input) {
		String[] entries = input.split(" ");
		parse(entries, 0, rootNode);
		return sumMetadata(rootNode);
	}

	protected Integer runPart2(String input) {
		String[] entries = input.split(" ");
		parse(entries, 0, rootNode);
		return getRootNodeValue(rootNode);
	}



	public static void main(String[] args) {
		Day8 day = new Day8();
		String input = day.readFile("/Day8.input");
		System.out.println("result:" + day.runPart1(input));
		System.out.println("result:" + day.runPart2(input));


	}
}
