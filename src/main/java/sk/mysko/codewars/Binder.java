package sk.mysko.codewars;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;




public class Binder<T, I> {
	public List<I> bind(List<T> list, Function<T, List<I>> func) {
		return list.stream().map(x -> func.apply(x)).flatMap(c -> c.stream()).collect(Collectors.toList());

	}


	public static void main(String[] args) {
		Binder binder = new Binder();
		List<Integer> list = Arrays.asList(1, 2, 3);
		assertEquals(binder.bind(list, i -> Arrays.asList(i)), Arrays.asList(1,2,3));

		list = Arrays.asList(7,8,9);
		assertEquals(binder.bind(list, i -> Arrays.asList(Arrays.asList(i))),
				Arrays.asList(Arrays.asList(7), Arrays.asList(8), Arrays.asList(9)));

		list = Arrays.asList(3,4,5);
		assertEquals(binder.bind(list, i -> Arrays.asList(Arrays.asList(i, (int)i * -1))),
				Arrays.asList(Arrays.asList(3, -3), Arrays.asList(4, -4), Arrays.asList(5, -5)));

		list = Arrays.asList(5,6,7);
		assertEquals(binder.bind(list, i -> Arrays.asList(i.toString())), Arrays.asList("5", "6", "7"));
	}
}
