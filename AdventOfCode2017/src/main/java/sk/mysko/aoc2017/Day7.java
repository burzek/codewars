package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
					parentNode.children.add(n);
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

	@Override
	protected String runPart2(String input) {
		parse(input);
		Node n = nodes.stream().filter(node -> node.parentNode == null).findFirst().get();
		n.children.stream().forEach(node -> System.out.println((node.name + " " + node.totalWeight)));
		return "";
	}
}
