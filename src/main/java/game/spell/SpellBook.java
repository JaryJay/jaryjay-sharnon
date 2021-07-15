package game.spell;

import java.util.ArrayList;
import java.util.List;

public class SpellBook {

	private List<Spell> spells;

	public SpellBook() {
		spells = new ArrayList<>();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < spells.size(); i++) {
			Spell spell = spells.get(i);
			stringBuilder.append((i > 0 ? "\n" : "") + spell);
		}
		return stringBuilder.toString();
	}

	public int size() {
		return spells.size();
	}

	public boolean isEmpty() {
		return spells.isEmpty();
	}

	public boolean contains(Object o) {
		return spells.contains(o);
	}

	public Object[] toArray() {
		return spells.toArray();
	}

	public boolean add(Spell e) {
		boolean add = spells.add(e);
		spells.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
		return add;
	}

	public boolean remove(Object o) {
		return spells.remove(o);
	}

	public void clear() {
		spells.clear();
	}

	public Spell get(int index) {
		return spells.get(index);
	}

	public Spell remove(int index) {
		return spells.remove(index);
	}

}
