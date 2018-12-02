package sk.mysko.codility;

/**
 * @author boris.brinza 08-Jun-2018.
 */
public class OddNumber {

	private void flip(int num, int[] array) {
		int pos = num / 32;
		int bit = num % 32;
		int mask = 1 << bit;
		array[pos] ^= mask;
	}

	private int firstSet(int[] array) {
		int pos = 0;
		while (array[pos] == 0) {
			pos++;
		}
		int val = array[pos];
		int bit = 0;
		while (val != 1) {
			val >>= 1;
			bit++;
		}
		return pos * 32 + bit;
	}

	public int solution(int[] A) {
		int[] bits = new int[1_000_000_000 / 32 + 1];
		for (int i = 0; i < A.length; i++) {
			flip(A[i], bits);
		}
		return firstSet(bits);

	}

	public static void main(String[] args) {
		OddNumber o = new OddNumber();
		System.out.println(o.solution(new int[] {9, 3, 9, 3, 9, 7, 9}));

	}
}

