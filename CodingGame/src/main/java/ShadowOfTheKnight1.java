import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * @author boris.brinza
 */
public class ShadowOfTheKnight1 extends CodingGameBase {

	public ShadowOfTheKnight1(String inputFile) {
		super(inputFile);
	}

	@Override
	protected void doTask(String input) {
		Scanner in = new Scanner(new ByteArrayInputStream(input.getBytes()));
		int W = in.nextInt(); // width of the building.
		int H = in.nextInt(); // height of the building.
		int N = in.nextInt(); // maximum number of turns before game over.
		int X0 = in.nextInt();
		int Y0 = in.nextInt();

		// game loop
		while (true) {
			String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

			int newX = X0;
			int newY = Y0;

			switch (bombDir) {
				case "U":
				case "UR":
				case "UL":
					newY = Y0 / 2;
				case "D":
				case "DR":
				case "DL":

			}


			// the location of the next window Batman should jump to.
			System.out.println("0 0");
		}
	}


	public static void main(String args[]) {
		new ShadowOfTheKnight1("ShadowOfTheKnight1.txt").run();
	}
}
