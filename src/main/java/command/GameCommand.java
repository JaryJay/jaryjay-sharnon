package command;

import java.util.function.Consumer;

public class GameCommand {

	private String key;
	private String args;
	private String description;
	private int commandLength;
	private Consumer<String[]> action;

	public GameCommand(String key, String description, int commandLength, Consumer<String[]> action) {
		this(key, null, description, commandLength, action);
	}

	public GameCommand(String key, String args, String description, int commandLength, Consumer<String[]> action) {
		this.key = key;
		this.args = args;
		this.description = description;
		this.commandLength = commandLength;
		this.action = action;
	}

	public String getKey() {
		return key;
	}

	public String getArgs() {
		return args;
	}

	public String getDescription() {
		return description;
	}

	public int getCommandLength() {
		return commandLength;
	}

	public Consumer<String[]> getAction() {
		return action;
	}

}
