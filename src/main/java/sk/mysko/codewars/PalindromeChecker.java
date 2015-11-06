package sk.mysko.codewars;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PalindromeChecker {

	private class Triangle {
		public void setArea(double a) {};
		public final int height = 1;
		public final int base = 2;
		private double area;
	}

	public boolean isPalindrome(String str) {

		String arr[] = Stream.of(str.toLowerCase()).map(x -> x.split("")).flatMap(Arrays::stream).
				filter(t -> Character.isLetterOrDigit(t.charAt(0))).toArray(String[]::new);
		return IntStream.range(0, arr.length).allMatch(pos -> arr[pos].charAt(0) == arr[arr.length - pos - 1].charAt(0));

	}

	public void run() {
		System.err.println(isPalindrome("1abc ,cba1"));
	}
}