package game.actor;

import inventory.Inventory;

public class Human extends GameActor implements HasLevel, HasClass {

	private int level;
	private int experience;
	private GameClass gameClass;
	private Inventory inventory;

	public Human(String name, GameClass gameClass, int maxHealth, int maxEnergy, int attack, int defence, int agility) {
		super(name, maxHealth, maxEnergy, attack, defence, agility);
		this.gameClass = gameClass;
		inventory = new Inventory();
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
		return name + " Level." + level + " " + gameClass.toString() + " Exp: " + experience + "/" + (level * 50 + 50)
				+ "\nHealth:  " + health + "/" + maxHealth
				+ "\nEnergy:  " + energy + "/" + maxEnergy
				+ "\nAttack:  " + attack
				+ "\nDefence: " + defence
				+ "\nAgility: " + agility;
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

	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public GameClass getGameClass() {
		return gameClass;
	}

	@Override
	public void setGameClass(GameClass gameClass) {
		this.gameClass = gameClass;
	}

}
