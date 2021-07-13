package inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Inventory {

	private List<Item> items = new ArrayList<>();

	public void forEach(Consumer<? super Item> action) {
		items.forEach(action);
	}

	public int size() {
		return items.size();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public boolean contains(Object o) {
		return items.contains(o);
	}

	public boolean add(Item e) {
		return items.add(e);
	}

	public boolean remove(Object o) {
		return items.remove(o);
	}

	public void clear() {
		items.clear();
	}

	@Override
	public boolean equals(Object o) {
		return items.equals(o);
	}

	public Item get(int index) {
		return items.get(index);
	}

	public void add(int index, Item element) {
		items.add(index, element);
	}

	public Item remove(int index) {
		return items.remove(index);
	}

	public int indexOf(Object o) {
		return items.indexOf(o);
	}

	public Stream<Item> stream() {
		return items.stream();
	}

}
