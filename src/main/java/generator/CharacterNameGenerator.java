package generator;

import java.util.Random;

public class CharacterNameGenerator {

	private static Random random = new Random();

	private CharacterNameGenerator() {
	}

	private static final String[] CONSONANTS = {
			"b", "b", "bl", "br",
			"c", "c", "ch", "cl", "cr",
			"d", "d", "dr",
			"f", "fl", "fr",
			"g", "gl", "gr",
			"h", "h",
			"j",
			"k", "k", "kl", "kr",
			"l", "l", "ll", "lr",
			"m", "m", "mm",
			"n", "n", "nn",
			"p", "p", "ph", "pl", "pr",
			"q", "qu",
			"r", "r", "rh", "rd",
			"s", "s", "sc", "sh", "sk", "sl", "sm", "sn", "sp", "st", "str", "sw",
			"t", "t", "th", "tr",
			"v", "v", "vr",
			"w", "wh", "wr",
			"x",
			"y", "y",
			"z", "z", "zh"
	};

	private static final String[] VOWELS = {
			"a", "a", "a", "aa", "ae", "ai", "ao", "au", "ay",
			"e", "e", "e", "e", "ea", "ee", "ei", "eo",
			"i", "i", "ia", "ie", "io",
			"o", "o", "o", "oa", "oi", "oo", "ou",
			"u", "ua", "ue",
			"y",
			"'"
	};

	private static final String[] CONSONANT_SUFFIXES = {
			"b",
			"c", "ch", "ck",
			"d",
			"f",
			"g",
			"h",
			"j",
			"k",
			"l", "ll",
			"m", "m", "mm",
			"n", "n", "ne",
			"p", "ph",
			"q",
			"r", "rh",
			"s", "sc", "sh", "sk", "sp", "st",
			"t", "th",
			"v",
			"w",
			"x",
			"y", "y",
			"z"
	};

	public static String generateCharacterName() {
		return generateCharacterName(1 + random.nextInt(4));
	}

	public static String generateCharacterName(int minSyllables, int maxSyllables) {
		return generateCharacterName(minSyllables + random.nextInt(maxSyllables - minSyllables + 1));
	}

	public static String generateCharacterName(int numOfSyllables) {
		boolean startWithVowel = random.nextBoolean();
		StringBuilder name = new StringBuilder();
		if (startWithVowel) {
			name.append(VOWELS[random.nextInt(VOWELS.length)]);
			numOfSyllables--;
		}
		for (int i = 0; i < numOfSyllables; i++) {
			name.append(CONSONANTS[random.nextInt(VOWELS.length)]);
			name.append(VOWELS[random.nextInt(VOWELS.length)]);
		}

		boolean endWithConsonant = random.nextBoolean();
		if (endWithConsonant) {
			name.append(CONSONANT_SUFFIXES[random.nextInt(CONSONANT_SUFFIXES.length)]);
		}
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(generateCharacterName(2, 3));
		}
	}

}
