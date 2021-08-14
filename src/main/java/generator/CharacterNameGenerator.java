package generator;

import java.util.Random;

public class CharacterNameGenerator {

	private static Random random = new Random();

	private CharacterNameGenerator() {
	}

	private static final String[] SYLLABLES = {
			"a", "al", "an", "ar", "as", "ay",
			"ba", "bar", "be", "bir", "bo", "bor", "bu", "bri", "bru",
			"ca", "cau", "ce", "cha", "che", "cher", "chi", "cho", "chu", "ci", "co", "cra", "crau", "cre", "cri", "cro", "cru", "cu",
			"da", "de", "di", "do", "du", "dra", "dre", "dri", "dro", "dru", "dry", "dy",
			"e", "el", "en", "eo", "es",
			"fa", "fe", "fi", "fo", "fur", "fra", "fre", "fri", "fro", "fru", "fy",
			"ga", "ge", "gi", "gna", "gne", "gno", "gri", "go", "gu", "gy",
			"ha", "har", "he", "hi", "ho", "hu", "hy",
			"i", "is",
			"ja", "je", "ji", "jo",
			"ka", "ke", "ki", "ko", "kra", "kre", "kre", "kri", "kro", "kru", "kry", "ku",
			"la", "lar", "le", "ler", "li", "lo", "lor", "lu",
			"mar", "mau", "me", "mo", "mu", "mau", "maw",
			"na", "nar", "nau", "ne", "ner", "ni", "no", "nor", "nu",
			"o", "oi", "ou", "or",
			"pa", "pe", "pha", "phe", "phi", "pho", "phu", "pi", "po", "pu", "py",
			"qua", "qua", "que", "qui", "quo",
			"ra", "re", "res", "rho", "ri", "ro", "ru",
			"sa", "sau", "se", "sha", "she", "shi", "sho", "shu", "si", "sio", "so", "soi", "sta", "ste", "sti", "sto", "stu", "su",
			"tha", "the", "thi", "tho", "thu", "thra", "tra", "tre", "tri", "tro", "tru", "try", "tsa", "tso",
			"ur",
			"va", "vau", "ve", "vi", "vo", "vu", "vy",
			"wa", "wal", "war", "we", "wi", "wo", "wu", "wur", "wra", "wre", "wri", "wro", "wru", "wry", "wy",
			"xe", "xi",
			"ya", "ye", "yi", "yo", "yu",
			"za", "zar", "ze", "zi", "zo", "zor", "zu", "zur",
			"'"
	};

	private static final String[] SUFFIXES = {
			"a", "ar", "arm", "ay",
			"b",
			"c", "ce", "ck",
			"d", "de",
			"e", "ell", "erm", "ey",
			"f", "ff",
			"g", "gh",
			"h",
			"i", "in", "ion",
			"k", "ke",
			"l", "ll", "le",
			"m",
			"n", "ne", "ng",
			"ol", "on", "or",
			"p", "ph",
			"r", "rn", "ry",
			"s", "ss",
			"t", "te", "th",
			"v",
			"w",
			"x", "xt",
			"y",
			"z",
			""
	};

	public static String generateCharacterName() {
		return generateCharacterName(random.nextInt(2) + random.nextInt(2));
	}

	public static String generateCharacterName(int minSyllables, int maxSyllables) {
		return generateCharacterName(minSyllables + random.nextInt(maxSyllables - minSyllables + 1));
	}

	public static String generateCharacterName(int numOfSyllables) {
		String name = SYLLABLES[random.nextInt(SYLLABLES.length - 1)];
		for (int i = 1; i < numOfSyllables; i++) {
			name += SYLLABLES[random.nextInt(SYLLABLES.length)];
		}
		name += SUFFIXES[random.nextInt(SUFFIXES.length)];
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}

}
