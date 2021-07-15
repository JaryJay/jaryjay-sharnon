package game.spell;

import java.util.TreeSet;

import loader.SpellLoader;

public class Spell implements Comparable<Spell> {

	public static TreeSet<Spell> values;

	static {
		if ((values = SpellLoader.loadSpells()) == null) {
			values = new TreeSet<>();
			addSpell(new Spell(4, "Fireball", "Deal 8 damage disregarding defence to a target."));
		}
	}

	private int cost;
	private String name;
	private String description;

	public Spell(int cost, String name, String description) {
		this.cost = cost;
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.description = description.substring(0, 1).toUpperCase() + description.substring(1);
	}

	public static Spell valueOf(String name) {
		for (Spell spell : values) {
			if (spell.getName().equalsIgnoreCase(name)) {
				return spell;
			}
		}
		return null;
	}

	public static void addSpell(Spell spell) {
		values.add(spell);
	}

	@Override
	public String toString() {
		return String.format("(%d) %s - %s", cost, name, description);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Spell o) {
		return name.compareTo(o.getName());
	}

}
