package game.gameclass;

public enum GameClass {

	// Basic
	ARCHER,
	WARRIOR,
	ASSASSIN,
	MAGE,
	PRIEST,

	// Second level archetype
	NINJA,
	RANGER,
	KNIGHT,
	BERSERKER,
	SPY,
	POISONER,
	ORACLE,
	WARMAGE,
	CLERIC,
	WARLOCK,

	// Third level archetype
	SAMURAI,
	DRUID,
	PALADIN,
	WEAPON_MASTER,
	ILLUSIOINIST,
	SEER,
	ARCHMAGUS,
	HEALER,
	SHADOW_CLERIC,
	NECROMANCER;

	public static GameClass getValueOf(String gameClass) {
		return valueOf(gameClass.toUpperCase());
	}

	@Override
	public String toString() {
		String string = super.toString();
		return string.substring(0, 1) + string.substring(1).toLowerCase();
	}

}
