package app.state;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.SharnonApp;
import command.GameCommand;

public abstract class AppState {

	protected SharnonApp app;
	private List<GameCommand> commands;
	private boolean quit;

	public AppState(SharnonApp app) {
		this.app = app;
	}

	public final void init() {
		commands = new ArrayList<>();
		addCommand(new GameCommand("help", "Show a list of commands", 1, args -> printCommands()));
		addCommand(new GameCommand("quit", "Exit Sharnon", 1, args -> quit()));
		addCommand(new GameCommand("clear", "Clears the console", 1, args -> System.out.println(ansi().eraseScreen().reset())));
		initCommands();
		doInit();
	}

	protected void doInit() {
	}

	public final void handle(String[] split) {
		Optional<GameCommand> optionalCommand = commands.stream().filter(c -> c.getKey().equals(split[0]) && c.getCommandLength() == split.length).findFirst();
		System.out.println();
		if (optionalCommand.isPresent()) {
			optionalCommand.get().getAction().accept(split);
		} else {
			optionalCommand = commands.stream().filter(c -> c.getKey().equals(split[0])).findFirst();
			if (optionalCommand.isPresent()) {
				GameCommand command = optionalCommand.get();
				System.out.println("Expected " + command.getCommandLength() + " arguments but got " + split.length);
			} else {
				System.out.println("Unrecognized command");
			}
		}
		doHandle(split);
	}

	public void doHandle(String[] split) {
	}

	public abstract void initCommands();

	public final void transitionState(AppState state) {
		app.setState(state);
		state.init();
	}

	public final void addCommand(GameCommand command) {
		commands.add(command);
	}

	public final boolean isQuit() {
		return quit;
	}

	public final void quit() {
		onQuit();
		this.quit = true;
	}

	public void onQuit() {
	}

	private void printCommands() {
		System.out.println("Commands:");
		for (int i = 0; i < commands.size(); i++) {
			GameCommand command = commands.get(i);
			if (command.getArgs() == null) {
				System.out.println("\t" + command.getKey() + " - " + command.getDescription());
			} else {
				System.out.println("\t" + command.getKey() + " " + command.getArgs() + " - " + command.getDescription());
			}
		}
	}

}
