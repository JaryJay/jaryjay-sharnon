package app.state;

import app.SharnonApp;
import command.GameCommand;
import game.actor.Human;
import loader.ActorLoader;

public class MainScreenState extends AppState {

	public MainScreenState(SharnonApp app) {
		super(app);
	}

	@Override
	public void initCommands() {
		addCommand(new GameCommand("start", "<name>", "Start a session with a given player name", 2, args -> {
			Human player = ActorLoader.loadHuman(args[1]);
			if (player == null) {
				System.out.println("No player found with name " + args[1] + ".");
			} else {
				System.out.println("Starting session with " + args[1] + ".");
				transitionState(new PlayState(app, player));
			}
		}));
		addCommand(new GameCommand("create", "<name>", "Create a new player", 2, args -> {
			ActorLoader.saveActor(ActorLoader.createHuman(args[1]));
		}));
	}

}
