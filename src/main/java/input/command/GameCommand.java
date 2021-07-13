package input.command;

public class GameCommand {

	private String key;
	private String description;
	private Runnable action;

	public GameCommand(String key, String description, Runnable action) {
		this.key = key;
		this.description = description;
		this.action = action;
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}

	public Runnable getAction() {
		return action;
	}

}
