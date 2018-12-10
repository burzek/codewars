package sk.mysko.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boris.brinza 07-dec-2018.
 */
public class Day9 extends AdventOfCodeBase<Integer> {


	protected Integer runPart1(int players, int marbles) {
		int[] playerScore = new int[players];

		List<Integer> marbleDesk = new ArrayList<>(marbles);
		marbleDesk.add(0);
		marbleDesk.add(1);
		int currentMarbleValue = 2;
		int currentMarblePos = 1;
		int currentPlayerIndex = 1;

		int currentSize = 2;

		while (currentMarbleValue <= marbles) {
			if (currentMarbleValue % 5000 == 0) {
				System.out.println(currentMarbleValue);
			}
			if (currentMarbleValue % 23 == 0) {
				playerScore[currentPlayerIndex] += currentMarbleValue;
				currentMarblePos = currentMarblePos - 7;
				if (currentMarblePos < 0) {
					currentMarblePos = currentSize + currentMarblePos;
				}
				playerScore[currentPlayerIndex] += marbleDesk.remove(currentMarblePos);
				currentSize--;
			} else {
				currentMarblePos = (currentMarblePos + 1) % currentSize + 1;
				marbleDesk.add(currentMarblePos, currentMarbleValue);
				currentSize++;
			}

			currentMarbleValue ++;
			currentPlayerIndex++;
			currentPlayerIndex %= players;

			//shrink
			if (currentSize > 10000 && currentMarblePos > 10 && currentMarblePos < marbleDesk.size() - 23) {
				marbleDesk = marbleDesk.subList(currentMarblePos, currentMarblePos + 23);
				currentMarblePos = 0;//currentMarblePos - 6;
				currentSize = marbleDesk.size();
			}


		}

		return Arrays.stream(playerScore).max().orElse(0);

	}



	public static void main(String[] args) {
		Day9 day = new Day9();
		System.out.println("result:" + day.runPart1(419, 71052));
//		System.out.println("result:" + day.runPart1(419, 71052 * 100));


	}
}
