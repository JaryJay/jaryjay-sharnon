package game.inventory;

import java.util.TreeSet;

import org.apache.commons.text.WordUtils;
import org.fusesource.jansi.Ansi;

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
		this.name = Ansi.ansi().fgCyan().a(WordUtils.capitalizeFully(name.replace('_', ' '))).reset().toString();
		description = description.replace('_', ' ');
		this.description = description.substring(0, 1).toUpperCase() + description.substring(1);
		this.value = value;
		this.maxHealthModifier = maxHealthModifier;
		this.maxEnergyModifier = maxEnergyModifier;
		this.attackModifier = attackModifier;
		this.defenceModifier = defenceModifier;
		this.agilityModifier = agilityModifier;
	}

	public Integer get(String attribute) {
		switch (attribute.toLowerCase()) {
			case "maxhealthmodifier":
			case "max_health_modifier":
			case "max_health":
			case "maxhealth":
				return maxHealthModifier;
			case "maxenergymodifier":
			case "max_energy_modifier":
			case "max_energy":
			case "maxenergy":
				return maxEnergyModifier;
			case "attack":
			case "attack_modifier":
			case "attackmodifier":
				return attackModifier;
			case "defence":
			case "defence_modifier":
			case "defencemodifier":
				return defenceModifier;
			case "agility":
			case "agility_modifier":
			case "agilitymodifier":
				return agilityModifier;
			default:
				return null;
		}
	}

	public boolean set(String attribute, int value) {
		switch (attribute.toLowerCase()) {
			case "maxhealthmodifier":
			case "max_health_modifier":
			case "max_health":
			case "maxhealth":
				maxHealthModifier = value;
				return true;
			case "maxenergymodifier":
			case "max_energy_modifier":
			case "max_energy":
			case "maxenergy":
				maxEnergyModifier = value;
				return true;
			case "attack":
			case "attack_modifier":
			case "attackmodifier":
				attackModifier = value;
				return true;
			case "defence":
			case "defence_modifier":
			case "defencemodifier":
				defenceModifier = value;
				return true;
			case "agility":
			case "agility_modifier":
			case "agilitymodifier":
				agilityModifier = value;
				return true;
			default:
				return false;
		}
	}

	public boolean add(String attribute, int value) {
		Integer attributeValue = get(attribute);
		return attributeValue == null ? false : set(attribute, attributeValue + value);
	}

	@Override
	public String toString() {
		return name;
	}

	public String getFullDescription() {
		return name + "\n" + description + "\nValue: " + value + " Gold Coins"
				+ "\nMax Health Modifier: " + maxHealthModifier
				+ "\nMax Energy Modifier: " + maxEnergyModifier
				+ "\nAttack Modifier:     " + attackModifier
				+ "\nDefence Modifier:    " + defenceModifier
				+ "\nAgility Modifier:    " + agilityModifier;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Item) {
			Item item = (Item) obj;
			return name.equals(item.name) && description.equals(item.description) && value == item.value
					&& maxHealthModifier == item.maxHealthModifier && maxEnergyModifier == item.maxEnergyModifier
					&& attackModifier == item.attackModifier && defenceModifier == item.defenceModifier
					&& agilityModifier == item.agilityModifier;
		}
		return false;
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
