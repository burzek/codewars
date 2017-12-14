package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day13 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day13 day13 = new Day13();
		String input = day13.readFile("/Day13.input");
		System.out.println("result:" + day13.runPart1(input));
		input = day13.readFile("/Day13b.input");
		System.out.println("result:" + day13.runPart2(input));

	}

	private class Layer {
		int maxDepth;
		int currentPosition;
		int step;

		public Layer(int maxDepth) {
			this.maxDepth = maxDepth;
			this.currentPosition = 0;
			this.step = 1;
		}

		public void doScan() {
			this.currentPosition += step;
			if (this.currentPosition == maxDepth - 1) {
				step = -1;
			} else if (this.currentPosition == 0) {
				step = 1;
			}


		}

		public void reset() {
			this.currentPosition = 0;
			this.step = 1;
		}

		@Override
		public String toString() {
			return "Layer {" +
				"maxDepth=" + maxDepth +
				", currentPosition=" + currentPosition +
				'}';
		}
	}

	@Override
	protected Integer runPart1(String input) {
		List<Layer> layers = parseInput(input);
		int spyPos = 0;
		int hit = 0;
		while (spyPos < layers.size()) {
			layers.forEach(Layer::doScan);
			spyPos++;
			if (spyPos < layers.size() && layers.get(spyPos).currentPosition == 0) {
				hit += layers.get(spyPos).maxDepth * spyPos;
			}

		}

		return hit;
	}

	@Override
	protected Integer runPart2(String input) {
		List<Layer> layers = parseInput(input);
		int hit;
		int delay = 10;
		do {
			int spyPos = 0;
			hit = 0;
			layers.forEach(Layer::reset);
			for (int i = 0; i < delay; i++) {
				layers.forEach(Layer::doScan);
			}
			while (spyPos < layers.size()) {
				layers.forEach(Layer::doScan);
				spyPos++;
				if (spyPos < layers.size() && layers.get(spyPos).currentPosition == 0) {
					hit += layers.get(spyPos).maxDepth * spyPos;
				}
			}
			delay++;
		} while (hit != 0);

		return delay;
	}


	private List<Layer> parseInput(String input) {
		List<Layer> ret = new ArrayList<>();

		String[] lines = input.split("\n");
		int line = 0;
		for (String l : lines) {
			int layer = Integer.parseInt(l.substring(0, l.indexOf(":")).trim());
			int depth = Integer.parseInt(l.substring(l.indexOf(":") + 1).trim());
			while (line < layer) {
				ret.add(new Layer(Integer.MAX_VALUE));
				line++;
			}
			ret.add(new Layer(depth));
			line++;
		}
		return ret;
	}


}
