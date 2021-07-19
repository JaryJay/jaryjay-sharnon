package game.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private List<Item> items;
	private List<Integer> amounts;

	public Inventory() {
		items = new ArrayList<>();
		amounts = new ArrayList<>();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int j = 0; j < items.size(); j++) {
			stringBuilder.append("\n" + amounts.get(j) + "x " + items.get(j));
		}
		return stringBuilder.toString();
	}

	public int size() {
		return items.size();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int get(Object key) {
		int index = items.indexOf(key);
		return index >= 0 ? amounts.get(index) : 0;
	}

	public void add(Item key, Integer value) {
		int index = items.indexOf(key);
		if (index >= 0) {
			put(key, get(key) + value);
		} else {
			put(key, value);
		}

	}

	public int put(Item key, Integer value) {
		int index = items.indexOf(key);
		if (index >= 0) {
			Integer original = amounts.get(index);
			if (value == 0) {
				items.remove(index);
				amounts.remove(index);
			} else {
				amounts.set(index, value);
			}
			return original;
		} else {
			items.add(key);
			amounts.add(value);
			return -1;
		}
	}

	public int remove(Object key) {
		int index = items.indexOf(key);
		if (index >= 0) {
			items.remove(index);
			int original = amounts.get(index);
			amounts.remove(index);
			return original;
		}
		return -1;
	}

	public void clear() {
		items.clear();
		amounts.clear();
	}

	public void sort() {
		amounts.sort((x, y) -> Integer.compare(x, y));
	}

	public List<Item> getItems() {
		return items;
	}

	public List<Integer> getAmounts() {
		return amounts;
	}

}
