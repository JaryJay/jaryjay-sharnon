package game.inventory;

import java.util.TreeSet;

import loader.ItemLoader;

public class Item implements Comparable<Item> {

	public static TreeSet<Item> values = ItemLoader.loadItems();

	private String name;
	private String description;
	private float value;

	private int maxHealthModifier;
	private int maxEnergyModifier;
	private int attackModifier;
	private int defenceModifier;
	private int agilityModifier;

	public Item(String name, String description, float value) {
		this(name, description, value, 0, 0, 0, 0, 0);
	}

	public Item(String name, String description, float value,
			int maxHealthModifier, int maxEnergyModifier, int attackModifier, int defenceModifier, int agilityModifier) {
		name = name.replace('_', ' ');
		description = description.replace('_', ' ');
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.description = description.substring(0, 1).toUpperCase() + description.substring(1);
		this.value = value;
		this.maxHealthModifier = maxHealthModifier;
		this.maxEnergyModifier = maxEnergyModifier;
		this.attackModifier = attackModifier;
		this.defenceModifier = defenceModifier;
		this.agilityModifier = agilityModifier;
	}

	@Override
	public String toString() {
		return name;
	}

	public static Item valueOf(String name) {
		for (Item item : values) {
			if (name.replace('_', ' ').equalsIgnoreCase(item.getName())) {
				return item;
			}
		}
		return null;
	}

	public static void addItem(Item item) {
		values.add(item);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getValue() {
		return value;
	}

	public int getMaxHealthModifier() {
		return maxHealthModifier;
	}

	public void setMaxHealthModifier(int maxHealthModifier) {
		this.maxHealthModifier = maxHealthModifier;
	}

	public int getMaxEnergyModifier() {
		return maxEnergyModifier;
	}

	public void setMaxEnergyModifier(int maxEnergyModifier) {
		this.maxEnergyModifier = maxEnergyModifier;
	}

	public int getAttackModifier() {
		return attackModifier;
	}

	public void setAttackModifier(int attackModifier) {
		this.attackModifier = attackModifier;
	}

	public int getDefenceModifier() {
		return defenceModifier;
	}

	public void setDefenceModifier(int defenceModifier) {
		this.defenceModifier = defenceModifier;
	}

	public int getAgilityModifier() {
		return agilityModifier;
	}

	public void setAgilityModifier(int agilityModifier) {
		this.agilityModifier = agilityModifier;
	}

	@Override
	public int compareTo(Item o) {
		return this.name.compareTo(o.name);
	}

}
