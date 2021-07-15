package game.actor;

import game.gameclass.GameClass;
import game.inventory.Inventory;
import game.spell.SpellBook;

public class Human extends GameActor implements HasLevel, HasClass, HasSpells, HasInventory {

	private int level;
	private int experience;
	private GameClass gameClass;
	private Inventory inventory;
	private SpellBook spellBook;

	public Human(String name, GameClass gameClass, int maxHealth, int maxEnergy, int attack, int defence, int agility) {
		super(name, maxHealth, maxEnergy, attack, defence, agility);
		this.gameClass = gameClass;
		inventory = new Inventory();
		spellBook = new SpellBook();
	}

	@Override
	public int addExperience(int experience) {
		int numTimesleveledUp = 0;
		this.experience += experience;
		while (this.experience >= level * 50 + 50) {
			this.experience -= level * 50 + 50;
			level++;
			numTimesleveledUp++;
		}
		return numTimesleveledUp;
	}

	@Override
	public String toString() {
		return name + " Level." + level + " " + gameClass.toString()
				+ "\nExp: " + experience + "/" + (level * 50 + 50)
				+ "\n\nStats:"
				+ "\nHealth:  " + health + "/" + maxHealth
				+ "\nEnergy:  " + energy + "/" + maxEnergy
				+ "\nAttack:  " + attack
				+ "\nDefence: " + defence
				+ "\nAgility: " + agility
				+ "\n\nInventory:" + inventory
				+ "\n\nSpell Book:\n" + spellBook;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getExperience() {
		return experience;
	}

	@Override
	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public GameClass getGameClass() {
		return gameClass;
	}

	@Override
	public void setGameClass(GameClass gameClass) {
		this.gameClass = gameClass;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public SpellBook getSpellBook() {
		return spellBook;
	}

}
