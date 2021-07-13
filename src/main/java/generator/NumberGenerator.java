package generator;

import java.util.Random;

public class NumberGenerator {

	private static final int DEFAULT_DICE_SIDES = 20;
	private static Random random = new Random();

	public static int generateNumber() {
		return generateNumber(DEFAULT_DICE_SIDES);
	}

	public static int generateNumber(int max) {
		return generateNumber(1, max);
	}

	public static int generateNumber(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

}
