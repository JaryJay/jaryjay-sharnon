package game.inventory;

import java.util.TreeSet;

import loader.ItemLoader;

public class Item implements Comparable<Item> {

	public static TreeSet<Item> values;

	static {
		if ((values = ItemLoader.loadItems()) == null) {
			System.out.println("Could not load items.");
			values = new TreeSet<>();
			addItem(new Item("Gold Coin", "A shiny golden coin", 1));
			addItem(new Item("Ration", "A fat ration containing potatoes, meat, and carrots", 1));
			addItem(new Item("Tent", "A cloth tent", 10));
			addItem(new Item("Amulet of Resurrection", "A magical amulet that prevents the wearer from dying once.", 3000));
			addItem(new Item("Health Potion", "A potion of crimson liquid that restores 15 health to the drinker.", 12));
			addItem(new Item("Potion Belt", "A belt for holding potions.", 3));
			addItem(new Item("Throwing Knife", "A razor sharp knife with a ring on the end, suitable for throwing.", 0.2f));
			addItem(new Item("Map of Sharnon", "A large parchment map of Sharnon.", 5));
			addItem(new Item("Communicator Artifact", "A heavy steel artifact used to contact other similar artifacts.", 35));
		}
	}

	private String name;
	private String description;
	private float value;

	public Item(String name, String description, float value) {
		this.name = name;
		this.description = description;
		this.value = value;
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

	@Override
	public int compareTo(Item o) {
		return this.name.compareTo(o.name);
	}

}
