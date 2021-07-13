package inventory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

	private Map<Item, Integer> items;

	public Inventory() {
		items = new HashMap<>();
	}

	@Override
	public String toString() {
		String string = "";
		items.keySet().stream().sorted((i1, i2) -> i1.getDescription().compareTo(i2.getDescription())).forEach(i -> {

		});
		return string;
	}

	public int size() {
		return items.size();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public Integer get(Object key) {
		return items.get(key);
	}

	public Integer put(Item key, Integer value) {
		return items.put(key, value);
	}

	public Integer remove(Object key) {
		return items.remove(key);
	}

	public void clear() {
		items.clear();
	}

	public Collection<Integer> values() {
		return items.values();
	}

	public boolean remove(Object key, Object value) {
		return items.remove(key, value);
	}

}
