package sk.mysko.aoc2018;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author boris.brinza
 */
public abstract class AdventOfCodeBase<T> {

	protected  String readFile(String path) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(AdventOfCodeBase.class.getResourceAsStream(path)));
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
		//remove last /n

		return  ret.toString().substring(0, ret.length() - 1);

	}



}
