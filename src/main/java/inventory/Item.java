package inventory;

public enum Item {

	GOLD_COIN("A shiny golden coin", 1),
	RATION("A fat ration containing potatoes, meat, and carrots", 1);

	private String description;
	private int value;

	private Item(String description, int value) {
		this.description = description;
		this.value = value;
	}

	@Override
	public String toString() {
		String string = super.toString().replace('_', ' ');
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public String getDescription() {
		return description;
	}

	public int getValue() {
		return value;
	}

}
