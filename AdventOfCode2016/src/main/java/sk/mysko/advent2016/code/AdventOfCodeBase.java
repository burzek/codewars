package sk.mysko.advent2016.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author boris.brinza
 */
public abstract  class AdventOfCodeBase<T> {

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

	protected abstract T runPart1(String input);
	protected abstract T runPart2(String input);


}
