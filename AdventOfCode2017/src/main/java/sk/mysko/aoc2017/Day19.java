package sk.mysko.aoc2017;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day19 extends AdventOfCodeBase<String> {

	public static void main(String[] args) {
		Day19 day19 = new Day19();
		String input = day19.readFile("/Day19.input");
		day19.runPart1(input);	//all is done in part1

	}

	enum DIR {
		UP, DOWN, LEFT, RIGHT
	}

	@Override
	protected String runPart1(String input) {
		String[] lines = input.split("\n");
		//find start
		int row = 0;
		int col = 0;
		int steps = 0;
		DIR dir = DIR.DOWN;
		while (lines[row].charAt(col) != '|') {
			col++;
		}

		StringBuilder ret = new StringBuilder();
		boolean exit = false;
		while (!exit) {
			steps++;
			switch (dir) {
				case DOWN:
					row++;
					break;
				case UP:
					row--;
					break;
				case LEFT:
					col--;
					break;
				case RIGHT:
					col++;
					break;
			}

			if (row < 0 || row > lines.length || col < 0 || col > lines[row].length() || lines[row].charAt(col) == ' ') {
				exit = true;
				continue;
			}

			char c = lines[row].charAt(col);
			if (Character.isLetter(c)) {
				ret.append(c);
			} else if (c == '+') {
				if (dir == DIR.DOWN || dir == DIR.UP) {
					if (col > 0 && lines[row].charAt(col - 1) == '-') {
						dir = DIR.LEFT;
					} else {
						dir = DIR.RIGHT;
					}
				} else if (dir == DIR.LEFT || dir == DIR.RIGHT) {
					if (row > 0 && col < lines[row - 1].length() && lines[row - 1].charAt(col) == '|') {
						dir = DIR.UP;
					} else {
						dir = DIR.DOWN;
					}
				}
			}


		}

		System.out.println("letters:" + ret.toString());
		System.out.println("steps:" + steps);
		return "";
	}


	@Override
	protected String runPart2(String input) {
		return "";
	}




}
