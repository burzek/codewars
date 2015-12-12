package sk.mysko.advent.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author boris.brinza
 */
public abstract  class AdventOfCodeBase {

	protected  String readFile(String path) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Day1.class.getResourceAsStream(path)));
		String s = null;
		StringBuilder ret = new StringBuilder();
		try {
			while ((s = reader.readLine()) != null) {
				ret.append(s).append("\n");
			}
		} catch (Exception e) {
			System.err.println("Cannot read file:" + path + ", error:" + e.toString());
			return null;
		}
		return  ret.toString();

	}

	protected abstract long runPart1(String input);
	protected abstract long runPart2(String input);


}
