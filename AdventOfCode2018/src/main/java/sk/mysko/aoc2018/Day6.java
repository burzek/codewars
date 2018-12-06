package sk.mysko.aoc2018;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day6 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day6 day6 = new Day6();
		String input = day6.readFile("/Day6.input");

		DataMap data = day6.initialize(input);
		System.out.println("result:" + day6.runPart1(data));
		System.out.println("result:" + day6.runPart2(data));
	}


	private class Node {
		char nodeCode;
		int nodeRow;
		int nodeCol;

		public Node(char nodeCode, int nodeRow, int nodeCol) {
			this.nodeCode = nodeCode;
			this.nodeRow = nodeRow;
			this.nodeCol = nodeCol;
		}
	}

	private class DataMap {
		int mapSize;
		char[][] map;
		Node[] nodes;
	}

	private DataMap initialize(String input) {
		int maxSize = 0;
		DataMap ret = new DataMap();
		String[] lines = input.split("\n");
		ret.nodes = new Node[lines.length];

		for (int idx = 0; idx < lines.length; idx++) {
			int col = Integer.parseInt(lines[idx].substring(0, lines[idx].indexOf(',')).trim());
			int row = Integer.parseInt(lines[idx].substring(lines[idx].indexOf(',') + 1).trim());
			ret.nodes[idx] = new Node((char) ('a' + idx), row, col);
			if (col > maxSize) {
				maxSize = col;
			}
			if (row > maxSize) {
				maxSize = row;
			}
		}
		maxSize = maxSize + 1;

		ret.map = new char[maxSize][maxSize];	//quick and dirty
		ret.mapSize = maxSize;
		return ret;
	}

	protected Integer runPart1(DataMap data) {

		for (int i = 0; i < data.mapSize; i++) {
			for (int j = 0; j < data.mapSize; j++) {
				char minNodeCode = '.';
				int minDist = Integer.MAX_VALUE;
				for (Node node : data.nodes) {
					int dist = getDist(node, i, j);
					if (dist < minDist) {
						minDist = dist;
						minNodeCode = node.nodeCode;
					} else if (dist == minDist) {
						minNodeCode = '.';	//two nodes same distance
					}
					data.map[node.nodeRow][node.nodeCol] = node.nodeCode;
				}
				data.map[i][j] = minNodeCode;
			}
		}

		int maxCount = 0;
		for (Node n : data.nodes) {
			if (isInfiniteNode(data, n.nodeCode)) {
				continue;
			}
			int count = 0;
			for (int i = 0; i < data.mapSize ; i++) {
				for (int j = 0; j < data.mapSize; j++) {
					if (data.map[i][j] == n.nodeCode)
						count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
			}
		}

		return maxCount;
	}




	protected Integer runPart2(DataMap data) {
		int regions = 0;
		for (int i = 0; i < data.mapSize; i++) {
			for (int j = 0; j < data.mapSize; j++) {
				int sumDis = 0;
				for (Node node : data.nodes) {
					sumDis += getDist(node, i, j);
				}
				if (sumDis < 10000) {
					regions++;
				}

			}
		}


		return regions;
	}



	private boolean isInfiniteNode(DataMap dataMap, char nodeCode) {
		for (int i = 0; i< dataMap.mapSize; i++) {
			final int iF = i;
			if (dataMap.map[0][iF] == nodeCode ||
					dataMap.map[dataMap.mapSize - 1][iF] == nodeCode ||
					dataMap.map[iF][0] == nodeCode ||
					dataMap.map[iF][dataMap.mapSize - 1] == nodeCode) {
				return true;
			}
		}
		return false;
	}

	private int getDist(Node node, int row, int col) {
		return Math.abs(node.nodeRow - row) + Math.abs(node.nodeCol - col);
	}

}
