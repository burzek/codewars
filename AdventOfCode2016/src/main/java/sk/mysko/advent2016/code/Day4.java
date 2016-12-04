package sk.mysko.advent2016.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boris.brinza
 */
public class Day4 extends AdventOfCodeBase<Integer> {

	private class RoomData implements Comparable<RoomData> {
		private char code;
		private int count;

		@Override
		public int compareTo(RoomData o) {
			if (o == null) {
				return 1;
			}
			if (count == o.count) {
				return code - o.code;
			}
			return o.count - count;
		}
	}

	private RoomData[] initRoomData() {
		RoomData[] roomDataList = new RoomData['z' - 'a' + 1];
		for (int i = 0; i <= 'z' - 'a'; i++) {
			roomDataList[i] = new RoomData();
			roomDataList[i].code = (char) ('a' + i);
		}
		return roomDataList;
	}


	protected Integer runPart1(String input) {
		int sectorIdSum = 0;


		String[] lines = input.split("\\r?\\n");
		for (String line : lines) {
			RoomData[] roomDataList = initRoomData();
			int index = 0;
			char c;
			while (!Character.isDigit(line.charAt(index))) {
				c = line.charAt(index++);
				if (c == '-') {
					continue;
				}
				roomDataList[c - 'a'].code = c;
				roomDataList[c - 'a'].count++;

			}

			//sort roomdata by count, then by char
			Arrays.sort(roomDataList);

			int sectorId = 0;
			c = '-';
			while (line.charAt(index) != '[') {
				c = line.charAt(index++);
				sectorId = sectorId	* 10 + (c - '0');
			};

			index++;
			int testIndex = 0;
			boolean isValid = true;
			do {
				c = line.charAt(index++);
				if (roomDataList[testIndex++].code != c) {
					isValid = false;
				}
			} while (line.charAt(index) != ']');


			if (isValid) {
				sectorIdSum += sectorId;
			}
		}

		return sectorIdSum;
	}



	protected Integer runPart2(String input) {
		String[] lines = input.split("\\r?\\n");
		for (String line : lines) {
			int last = line.lastIndexOf('-');
			int code = Integer.valueOf(line.substring(last + 1, line.indexOf('[')));
			int index = 0;
			StringBuilder str = new StringBuilder();
			while (index != last) {
				if (line.charAt(index) == '-') {
					str.append(" ");
				} else {

					str.append((char) ('a' + ((line.charAt(index) + code - 'a') % 26)));
				}
				index++;
			}
			if (str.toString().equals("northpole object storage")) {
				return code;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		Day4 day4 = new Day4();
		String input = day4.readFile("/Day4.input");
		System.err.println("sectorId sum:" + day4.runPart1(input));
		input = day4.readFile("/Day4b.input");

		System.err.println("sectorId:" + day4.runPart2(input));

	}

}
