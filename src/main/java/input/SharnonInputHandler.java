package input;

import java.util.ArrayList;
import java.util.List;

import input.command.GameCommand;

public class SharnonInputHandler {

	private static final GameCommand ERROR_COMMAND = new GameCommand(null, null, () -> System.out.println("Unrecognized command"));

	private boolean quit;

	private List<GameCommand> commands;

	public SharnonInputHandler() {
		commands = new ArrayList<>();
		commands.add(new GameCommand("help", "Show a list of commands", this::printCommands));
	}

	public void handle(String readLine) {
		GameCommand command = commands.stream().filter(c -> c.getKey().equals(readLine)).findFirst().orElseGet(() -> ERROR_COMMAND);
		command.getAction().run();
	}

	private void printCommands() {
		System.out.println("Commands:\n===========\n");
		for (GameCommand command : commands) {
			System.out.println("\t" + command.getKey() + " - " + command.getDescription());
		}
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

}
