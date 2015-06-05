package sk.mysko.codewars;

import static org.junit.Assert.assertEquals;

/**
 * @author boris.brinza
 */
public class SimpleCardGame {

	public String winner(String[] deckSteve, String[] deckJosh) {
		String cards = "23456789TJQKA";
		int steveWins = 0;
		int joshWins = 0;
		for (int i = 0; i < deckSteve.length; i++) {
			int diff = cards.indexOf(deckSteve[i]) - cards.indexOf(deckJosh[i]);
			if (diff > 0) {
				steveWins++;
			} else if (diff < 0) {
				joshWins++;
			}
		}

		if (steveWins > joshWins) {
			return "Steve wins " + steveWins + " to " + joshWins;
		} else if (steveWins < joshWins) {
			return "Josh wins " + joshWins + " to " + steveWins;
		} else {
			return "Tie";
		}
	}


	public static void main(String[] args) {
		SimpleCardGame game = new SimpleCardGame();
		assertEquals(
				"small deck",
				"Steve wins 2 to 1",
				game.winner(new String[]{"A", "7", "8"}, new String[]{"K", "5", "9"})
		);

		assertEquals(
				"small deck",
				"Tie",
				game.winner(new String[]{"T"}, new String[]{"T"})
		);
	}

}
