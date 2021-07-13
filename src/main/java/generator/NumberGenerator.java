package generator;

import java.util.Random;

public class NumberGenerator {

	private static final int DEFAULT_DICE_SIDES = 20;
	private static Random random = new Random();

	public static int rollDie() {
		return rollDie(DEFAULT_DICE_SIDES);
	}

	public static int rollDie(int sides) {
		return random.nextInt(sides) + 1;
	}

}
