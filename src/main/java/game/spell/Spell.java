package game.spell;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.TreeSet;

import org.apache.commons.text.WordUtils;

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
		this.name = ansi().fgBrightMagenta().a(WordUtils.capitalizeFully(name)).reset().toString();
		this.description = description.substring(0, 1).toUpperCase() + description.substring(1);
	}

	public static Spell valueOf(String name) {
		name = name.replace('_', ' ');
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
		return String.format("%s %s - %s", ansi().fgBrightBlue().a("(" + Integer.toString(cost) + ")").reset().toString(),
				ansi().fgRed().a(name).reset().toString(), description);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Spell)) {
			return false;
		}
		Spell spell = (Spell) obj;
		return cost == spell.cost && name.equals(spell.name) && description.equals(spell.description);
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
