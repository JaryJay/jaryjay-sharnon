package game.actor;

import static org.fusesource.jansi.Ansi.ansi;

import game.gameclass.GameClass;
import game.inventory.Inventory;
import game.inventory.Item;
import game.spell.SpellBook;

public class Human extends GameActor implements HasLevel, HasClass, HasSpells, HasInventory, HasEquipment {

	private int level;
	private int experience;
	private GameClass gameClass;
	private Inventory inventory;
	private SpellBook spellBook;

	private Item primaryWeapon;
	private Item secondaryWeapon;
	private Item armour;
	private Item helmet;
	private Item footwear;
	private Item[] accessories;

	public Human(String name, GameClass gameClass, int maxHealth, int maxEnergy, int attack, int defence, int agility) {
		super(name, maxHealth, maxEnergy, attack, defence, agility);
		this.gameClass = gameClass;
		inventory = new Inventory();
		spellBook = new SpellBook();
		accessories = new Item[3];
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
		int maxHealthModifier = getMaxHealthModifier();
		int maxEnergyModifier = getMaxEnergyModifier();
		int attackModifier = getAttackModifier();
		int defenceModifier = getDefenceModifier();
		int agilityModifier = getAgilityModifier();
		String healthText = "Health:  " + health + "/" + (baseMaxHealth + maxHealthModifier)
				+ " (" + (maxHealthModifier < 0 ? "" : "+") + maxHealthModifier + ")";
		String energyText = "Energy:  " + energy + "/" + (baseMaxEnergy + maxEnergyModifier)
				+ " (" + (maxEnergyModifier < 0 ? "" : "+") + maxEnergyModifier + ")";
		String attackText = "Attack: " + (baseAttack + attackModifier) + " (" + (attackModifier < 0 ? "" : "+") + attackModifier + ")";
		String defenceText = "Defence: " + (baseDefence + defenceModifier) + " (" + (defenceModifier < 0 ? "" : "+") + defenceModifier + ")";
		String agilityText = "Agility: " + (baseAgility + agilityModifier) + " (" + (agilityModifier < 0 ? "" : "+") + agilityModifier + ")";
		return ansi().fgBrightCyan().a(name).reset() + " Level." + level + " " + gameClass.toString()
				+ "\nExp: " + experience + "/" + (level * 50 + 50)
				+ "\n\nStats:"
				+ "\n" + healthText
				+ "\n" + energyText
				+ "\n" + attackText
				+ "\n" + defenceText
				+ "\n" + agilityText
				+ "\n\n" + ansi().fgRed().a("Inventory:").reset() + inventory
				+ "\n\n" + ansi().fgBrightMagenta().a("Spell Book:").reset() + "\n" + spellBook;
	}

	@Override
	public Human copy(String name) {
		Human copy = new Human(name, gameClass, baseMaxHealth, baseMaxEnergy, baseAttack, baseDefence, baseAgility);
		copy.setLevel(level);
		copy.setExperience(experience);
		copy.inventory = inventory.copy();
		return null;
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

	@Override
	public Item getPrimaryWeapon() {
		return primaryWeapon;
	}

	@Override
	public void setPrimaryWeapon(Item primaryWeapon) {
		this.primaryWeapon = primaryWeapon;
	}

	@Override
	public Item getSecondaryWeapon() {
		return secondaryWeapon;
	}

	@Override
	public void setSecondaryWeapon(Item secondaryWeapon) {
		this.secondaryWeapon = secondaryWeapon;
	}

	@Override
	public Item getArmour() {
		return armour;
	}

	@Override
	public void setArmour(Item armour) {
		this.armour = armour;
	}

	@Override
	public Item getHelmet() {
		return helmet;
	}

	@Override
	public void setHelmet(Item helmet) {
		this.helmet = helmet;
	}

	@Override
	public Item getFootwear() {
		return footwear;
	}

	@Override
	public void setFootwear(Item footwear) {
		this.footwear = footwear;
	}

	@Override
	public Item[] getAccessories() {
		accessories = accessories == null ? new Item[3] : accessories;
		return accessories;
	}

	@Override
	public int getAttack() {
		return baseAttack + getAttackModifier();
	}

	@Override
	public int getDefence() {
		return baseDefence + getDefenceModifier();
	}

	@Override
	public int getAgility() {
		return baseAgility + getAgilityModifier();
	}

}
