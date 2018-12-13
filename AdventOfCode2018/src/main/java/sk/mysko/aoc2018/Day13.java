package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day13 extends AdventOfCodeBase<Integer> {

	private class Car {
		int row;
		int col;
		char dir;
		int crossCounter = 0;
	}
	private class Map {
		char[][] map;
		List<Car> cars = new ArrayList<>();

		String collision() {
			Set<String> col = new HashSet<>();
			for (Car c : cars) {
				String key = c.col + "," + c.row;
				if (col.contains(key)) {
					return key;
				}
				col.add(key);
			}
			return null;
		}

		void step() {
			for (int ci = 0; ci < cars.size(); ci++) {
				Car c = cars.get(ci);
				if (c.dir == '>' || c.dir == '<') {
					c.col = c.col + (c.dir == '>' ? 1 : -1);
					if (map	[c.row][c.col] == '\\') {
						c.dir = (c.dir == '<' ? '^' : 'v');
					} else if (map[c.row][c.col] == '/') {
						c.dir = (c.dir == '<' ? 'v' : '^');
					} else if (map[c.row][c.col] == '+') {
						c.dir = turn(c);
						c.crossCounter++;
						c.crossCounter %= 3;
					}
				} else if (c.dir == '^' || c.dir == 'v') {
					c.row = c.row + (c.dir == '^' ? -1 : 1);
					if (map[c.row][c.col] == '\\') {
						c.dir = (c.dir == '^' ? '<' : '>');
					} else if (map[c.row][c.col] == '/') {
						c.dir = (c.dir == '^' ? '>' : '<');
					} else if (map[c.row][c.col] == '+') {
						c.dir = turn(c);
						c.crossCounter++;
						c.crossCounter %= 3;
					}
				}
			}
		}

		char turn(Car c) {
			if (c.crossCounter == 0) {
				if (c.dir == '^') {
					return '<';
				} else if (c.dir == '<') {
					return 'v';
				} else if (c.dir == 'v') {
					return '>';
				} else {
					return '^';
				}
			} else if (c.crossCounter == 2) {
				if (c.dir == '^') {
					return '>';
				} else if (c.dir == '<') {
					return '^';
				} else if (c.dir == 'v') {
					return '<';
				} else {
					return 'v';
				}
			}
			return c.dir;
		}
	}

	private String runPart1(String input) {
		Map m = parse(input);
		String collisionCoord = null;
		while ((collisionCoord = m.collision()) == null) {
			m.step();
		}
		return collisionCoord;
	}



	private String runPart2(String input) {
		Map m = parse(input);
		while (m.cars.size() != 1) {
			m.step();
			String collision = m.collision();
			m.cars.removeIf(c -> (c.col + "," + c.row).equals(collision));
			if (m.cars.size() == 1) {
				break;
			}
		}


		return m.cars.get(0).col + "," + m.cars.get(0).row;
	}


	private Map parse(String input) {
		Map ret = new Map();
		ret.map = new char[151][151];
		int ci = 0;
		int row = 0;
		for (String line : input.split("\n")) {
			int col = 0;
			for (char c : line.toCharArray()) {
				if (c == '>' || c == '<' || c == '^' || c == 'v') {
					Car car = new Car();
					car.row = row;
					car.col = col;
					car.dir = c;
					ret.cars.add(car);
					ret.map[row][col++] = car.dir == '>' || car.dir == '<' ? '-' : '|';
				} else {
					ret.map[row][col++] = c;
				}
			}
			row++;
		}
		return ret;
	}


	public static void main(String[] args) {
		Day13 day = new Day13();
		String input = day.readFile("/Day13.input");
		System.out.println("result:" + day.runPart1(input));
		System.out.println("result:" + day.runPart2(input));
	}
}
