package app.state;

import java.util.ArrayList;
import java.util.List;

import app.SharnonApp;
import command.GameCommand;
import game.actor.GameActor;
import game.actor.GameClass;
import game.actor.HasClass;
import game.actor.HasLevel;
import game.actor.Human;
import generator.CharacterNameGenerator;
import generator.NumberGenerator;
import loader.ActorLoader;

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
		addCommand(new GameCommand("actors", "Describes all of the current actors", 1, args -> {
			for (int i = 0; i < actors.size(); i++) {
				GameActor actor = actors.get(i);
				System.out.println("\n" + actor);
			}
		}));
		addCommand(new GameCommand("set", "<name> <attr> <amount>", "Sets an attribute of an actor", 4, args -> {
			GameActor actor = getActor(args[1]);
			if (actor == null) {
				System.out.println("No actor found with name " + args[1]);
				return;
			}
			String value = args[3];
			boolean add = false;
			if (args[3].matches("^(\\+|-)\\d+$")) {
				add = true;
			}
			switch (args[2]) {
				case "level":
					if (actor instanceof HasLevel) {
						HasLevel hasLevel = (HasLevel) actor;
						int amount = parseInt(value);
						hasLevel.setLevel(add ? hasLevel.getLevel() + amount : amount);
					}
					break;
				case "exp":
				case "xp":
				case "experience":
					if (actor instanceof HasLevel) {
						HasLevel hasLevel = (HasLevel) actor;
						int amount = parseInt(value);
						hasLevel.addExperience(add ? amount : amount - hasLevel.getExperience());
					}
					break;
				case "class":
					if (actor instanceof HasClass) {
						HasClass hasClass = (HasClass) actor;
						hasClass.setGameClass(GameClass.getValueOf(value));
					}
					break;
				case "name":
					actor.setName(value);
					break;
				case "max_health":
					actor.setMaxHealth(add ? actor.getMaxHealth() + parseInt(value) : parseInt(value));
					break;
				case "max_energy":
					actor.setMaxEnergy(add ? actor.getMaxEnergy() + parseInt(value) : parseInt(value));
					break;
				case "health":
					actor.setHealth(add ? actor.getHealth() + parseInt(value) : parseInt(value));
					break;
				case "energy":
					actor.setEnergy(add ? actor.getEnergy() + parseInt(value) : parseInt(value));
					break;
				case "attack":
					actor.setAttack(add ? actor.getAttack() + parseInt(value) : parseInt(value));
					break;
				case "defence":
					actor.setDefence(add ? actor.getDefence() + parseInt(value) : parseInt(value));
					break;
				case "agility":
					actor.setAgility(add ? actor.getAgility() + parseInt(value) : parseInt(value));
					break;
				default:
					System.out.println("No attribute foud.");
					return;
			}
			System.out.println("Update success.");
		}));
		addCommand(new GameCommand("names", "Generate 10 names", 1, args -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(CharacterNameGenerator.generateCharacterName());
			}
		}));
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
		addCommand(new GameCommand("add", "<name>", "Add an actor", 2, args -> {
			if (getActor(args[1]) != null) {
				System.out.println("Actor " + args[1] + " already exists.");
			} else {
				Human loadHuman = ActorLoader.loadHuman(args[1]);
				if (loadHuman != null) {
					actors.add(loadHuman);
					System.out.println("Succesfully added actor " + args[1] + ".");
				} else {
					System.out.println("Actor " + args[1] + " doesn't exist.");
				}
			}
		}));
		addCommand(new GameCommand("remove", "<name>", "Remove an actor", 2, args -> {
			GameActor actor = getActor(args[1]);
			if (actor != null) {
				actors.remove(actor);
				System.out.println("Removed actor " + args[1] + ".");
			} else {
				System.out.println("Actor " + args[1] + " does not exist.");
			}
		}));
		addCommand(new GameCommand("create", "<name>", "Create an actor", 2, args -> {
			ActorLoader.saveActor(ActorLoader.createHuman(args[1]));
			System.out.println("Created actor " + args[1] + ".");
		}));
		addCommand(new GameCommand("save", "Saves the actors", 1, args -> saveActors()));
	}

	private int parseInt(String value) {
		try {
			int i = Integer.parseInt(value);
			return i;
		} catch (NumberFormatException e) {
			saveActors();
			throw new RuntimeException("Could not parse value " + value + ".");
		}
	}

	private void saveActors() {
		for (GameActor actor : actors) {
			ActorLoader.saveActor(actor);
		}
		System.out.println("Save success.");
	}

	private GameActor getActor(String name) {
		GameActor actor = actors.stream().filter(a -> a.getName().equalsIgnoreCase(name.replace('_', ' '))).findFirst().orElse(null);
		return actor;
	}

}
