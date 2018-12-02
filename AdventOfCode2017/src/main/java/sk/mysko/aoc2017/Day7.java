package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day7 extends AdventOfCodeBase<String> {

	public static void main(String[] args) {
		Day7 day7 = new Day7();
		String input = day7.readFile("/Day7.input");
		System.out.println("result:" + day7.runPart1(input));
		input = day7.readFile("/Day7b.input");
		System.out.println("result:" + day7.runPart2(input));


	}

	private class Node {
		Node parentNode;
		List<Node> children = new ArrayList<>();
		int weight;
		int totalWeight;
		String name;

		@Override
		public boolean equals(Object obj) {
			return obj.equals(this.name);
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}
	}

	private List<Node> nodes = new ArrayList<>();

	private Node findNodeByName(String name) {
		return nodes.stream().filter(n -> n.name.equals(name)).findFirst().get();
	}

	private void parse(String input) {
		nodes.clear();
		String[] lines = input.split("\n");
		for (String line : lines) {
			Node node = new Node();
			node.name = line.substring(0, line.indexOf(' ')).trim();
			node.weight = Integer.parseInt(line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim());
			node.totalWeight = node.weight;
			nodes.add(node);
		}
		//run2
		int index = 0;
		for (String line : lines) {
			if (line.indexOf('>') != -1) {
				Node parentNode = nodes.get(index);
				for(String c : line.substring(line.indexOf('>') + 1).split(",")) {
					Node n = findNodeByName(c.trim());
					n.parentNode = parentNode;
					if (!parentNode.children.contains(n)) {
						parentNode.children.add(n);
					}
					parentNode.totalWeight += n.weight;
				}
			}
			index++;

		}
	}


	@Override
	protected String runPart1(String input) {
		parse(input);
		return nodes.stream().filter(n -> n.parentNode == null).map(n -> n.name).findFirst().get();
	}

	private void findUnbalancedNode(Node n, int level) {
		long w = 0;
		int count = 0;
		n.children.sort(Comparator.comparingInt(o -> o.totalWeight));
		for (Node cn : n.children) {
			if (w == 0) {
				w = cn.totalWeight;
			} else if (cn.totalWeight != w) {
				count++;
			}
		}
		if (count == 1) {
			n.children.stream().forEach(x -> System.out.print(x.name + " (" + x.weight + ") " + (x.totalWeight) + " "));
			System.out.println();
		} else {
			for (Node cn : n.children) {
				findUnbalancedNode(cn, level + 1);
			}
		}
	}


	@Override
	protected String runPart2(String input) {
		parse(input);
		Node n = nodes.stream().filter(node -> node.parentNode == null).findFirst().get();
		findUnbalancedNode(n, 0);
		return "";
	}
}
