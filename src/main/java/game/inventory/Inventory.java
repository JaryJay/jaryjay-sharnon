package game.inventory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class Inventory {

	private LinkedHashMap<String, Integer> itemsToAmounts;

	public Inventory() {
		itemsToAmounts = new LinkedHashMap<>();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		itemsToAmounts.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(new Consumer<Entry<String, Integer>>() {
			@Override
			public void accept(Entry<String, Integer> entry) {
				String item = entry.getKey();
				int amount = entry.getValue();
				stringBuilder.append("\n" + amount + "x " + item);
			}
		});
		return stringBuilder.toString();
	}

	public int size() {
		return itemsToAmounts.size();
	}

	public int add(Item item, int amount) {
		return put(item, get(item) + amount);
	}

	public int put(Item item, int amount) {
		Integer put = itemsToAmounts.put(item.getName(), amount);
		return put == null ? 0 : put.intValue();
	}

	public boolean isEmpty() {
		return itemsToAmounts.isEmpty();
	}

	public int get(Item item) {
		Integer amount = itemsToAmounts.get(item.getName());
		return amount == null ? 0 : amount.intValue();
	}

	public int remove(Item item) {
		Integer remove = itemsToAmounts.remove(item.getName());
		return remove == null ? 0 : remove.intValue();
	}

	public void clear() {
		itemsToAmounts.clear();
	}

}
