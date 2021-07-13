package app.state;

import java.util.ArrayList;
import java.util.List;

import app.SharnonApp;
import command.GameCommand;
import game.actor.GameActor;
import game.actor.Human;
import generator.NumberGenerator;

public class PlayState extends AppState {

	private Human player;
	private List<GameActor> actors;

	public PlayState(SharnonApp app, Human player) {
		super(app);
		this.player = player;
		this.actors = new ArrayList<>();
		this.actors.add(player);
	}

	@Override
	public void initCommands() {
		addCommand(new GameCommand("desc", "Describe the player", 1, args -> {
			System.out.println(player);
		}));
		addCommand(new GameCommand("desc", "<name>", "Describe an actor", 2, args -> {
			GameActor actor = getActor(args[1]);
			System.out.println(actor != null ? actor : ("No actor found with name " + args[1]));
		}));
//		addCommand(new GameCommand("set", "<name> <stat> <amount>", "Sets a stat of an actor", 4, args -> {
//			GameActor actor = getActor(args[1]);
//			if (actor == null) {
//				System.out.println("No actor found with name " + args[1]);
//				return;
//			}
//			int amount;
//			try {
//				amount = Integer.parseInt(args[3]);
//			} catch (NumberFormatException e) {
//				System.out.println("Could not parse amount.");
//				return;
//			}
//			boolean add = false;
//			if (args[3].matches("^(+|-)")) {
//				add = true;
//			}
//			switch (args[2]) {
//				case "experience":
//					if (actor instanceof HasLevel) {
//						HasLevel hasLevel = (HasLevel) actor;
//						hasLevel.addExperience(add ? amount : amount - hasLevel.getExperience());
//					}
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//				case "attack":
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//				case "attack":
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//				case "attack":
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//				case "attack":
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//				case "attack":
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//				case "attack":
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//				case "attack":
//					actor.setAttack((add ? actor.getAttack() : 0) + amount);
//					break;
//
//				default:
//					break;
//			}
//		}));
		addCommand(new GameCommand("roll", "[<number of dice>]d<number of faces>", "Roll a number of dice", 2, args -> {
			args[1] = args[1].toLowerCase();
			int numDice = 1;
			int numSides = 0;
			if (args[1].matches("\\d+d\\d+")) {
				int indexOfD = args[1].indexOf("d");
				try {
					numDice = Integer.parseInt(args[1].substring(0, indexOfD));
					numSides = Integer.parseInt(args[1].substring(indexOfD + 1));
				} catch (NumberFormatException e) {
					System.out.println("Could not parse dice");
					return;
				}
				if (numDice > 5000) {
					System.out.println("Number of dice too large");
				} else {
					List<Integer> diceRolls = new ArrayList<>();
					for (int i = 0; i < numDice; i++) {
						diceRolls.add(NumberGenerator.generateNumber(numSides));
					}
					System.out.println("Rolls: " + diceRolls);
					System.out.println("Total: " + diceRolls.stream().mapToInt(i -> i).sum());
				}
			} else if (args[1].matches("d\\d+")) {
				try {
					numSides = Integer.parseInt(args[1].substring(1));
				} catch (NumberFormatException e) {
					System.out.println("Could not parse dice");
					return;
				}
				System.out.println("Roll: " + NumberGenerator.generateNumber(numSides));
			} else {
				System.out.println("Invalid roll");
				return;
			}
		}));
	}

	private GameActor getActor(String name) {
		GameActor actor = actors.stream().filter(a -> a.getName().equalsIgnoreCase(name.replace('_', ' '))).findFirst().orElse(null);
		return actor;
	}

}
