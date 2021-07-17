package app.state;

import java.util.ArrayList;
import java.util.List;

import command.GameCommand;
import game.actor.*;
import game.gameclass.GameClass;
import game.inventory.Item;
import game.spell.Spell;
import generator.CharacterNameGenerator;
import generator.NumberGenerator;
import loader.ActorLoader;

public class PlayStateCommandAdder {

	private PlayState playState;

	public PlayStateCommandAdder(PlayState playState) {
		this.playState = playState;
	}

	public void addActorCommands() {
		playState.addCommand(new GameCommand("desc", "Describe the player", 1, args -> {
			System.out.println("\n" + playState.player + "\n");
		}));
		playState.addCommand(new GameCommand("desc", "<actor>", "Describe an actor", 2, args -> {
			GameActor actor = getActor(args[1]);
			System.out.println(actor != null ? ("\n" + actor) : ("Actor " + args[1] + " does not exist."));
		}));
		playState.addCommand(new GameCommand("actors", "Describes all of the current actors", 1, args -> {
			for (int i = 0; i < playState.getActors().size(); i++) {
				GameActor actor = playState.getActors().get(i);
				System.out.println("\n" + actor + "\n");
			}
		}));
		playState.addCommand(new GameCommand("set", "<actor> <attr> <amount>", "Sets an attribute of an actor", 4, args -> {
			GameActor actor = getActor(args[1]);
			if (actor == null) {
				System.out.println("Actor " + args[1] + " does not exist.");
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
					System.out.println("Attribute" + args[2] + " does not exist.");
					return;
			}
			System.out.println("Update success.");
		}));
		playState.addCommand(new GameCommand("add", "<actor>", "Add an actor", 2, args -> {
			if (getActor(args[1]) != null) {
				System.out.println("Actor " + getActor(args[1]).getName() + " already exists.");
			} else {
				GameActor actor = ActorLoader.loadActor(args[1], Human.class);
				if (actor != null) {
					playState.getActors().add(actor);
					System.out.println("Succesfully added actor " + actor.getName() + ".");
				} else {
					System.out.println("Actor " + args[1] + " does not exist.");
				}
			}
		}));
		playState.addCommand(new GameCommand("remove", "<actor>", "Remove an actor", 2, args -> {
			GameActor actor = getActor(args[1]);
			if (actor != null) {
				playState.getActors().remove(actor);
				System.out.println("Removed actor " + args[1] + ".");
			} else {
				System.out.println("Actor " + args[1] + " does not exist.");
			}
		}));
		playState.addCommand(new GameCommand("create_actor", "<actor>", "Create an actor", 2, args -> {
			ActorLoader.saveActor(ActorLoader.createHuman(args[1]));
			System.out.println("Created actor " + args[1] + ".");
		}));
	}

	public void addItemCommands() {
		playState.addCommand(new GameCommand("item", "<actor> <item> <amount>", "Add or remove items from an inventory", 4, args -> {
			GameActor actor = getActor(args[1]);
			if (actor == null) {
				System.out.println("Actor " + args[1] + " does not exist.");
				return;
			} else if (!(actor instanceof HasInventory)) {
				System.out.println("Actor " + actor.getName() + " does not have an inventory.");
				return;
			}
			Item item = Item.valueOf(args[2]);
			if (item == null) {
				System.out.println("Item " + args[2] + " does not exist.");
				return;
			}
			boolean add = args[3].matches("^(\\+|-)\\d+$");
			HasInventory hasInventory = (HasInventory) actor;
			int amount = parseInt(args[3]);
			if (add) {
				hasInventory.getInventory().add(item, amount);
				System.out.println("Added " + amount + "x " + item + " to " + actor.getName() + "'s inventory.");
			} else {
				hasInventory.getInventory().put(item, amount);
				System.out.println("Set amount of " + item + " to " + amount + " in " + actor.getName() + "'s inventory.");
			}
		}));
		playState.addCommand(new GameCommand("inspect_item", "<item>", "Describe an item", 2, args -> {
			Item item = Item.valueOf(args[1]);
			if (item == null) {
				System.out.println("Item " + args[1] + " does not exist.");
			} else {
				System.out.println(item.getName() + "\n" + item.getDescription() + "\nValue: " + item.getValue() + " Gold Coin(s)");
			}
		}));
		playState.addCommand(new GameCommand("create_item", "<item> <description> <value>", "Create an item", 4, args -> {
			Item item = Item.valueOf(args[1]);
			if (item != null) {
				System.out.println("Item " + item.getName() + " already exists.");
			}
			Float value = parseFloat(args[3]);
			if (value == null) {
				return;
			}
			item = new Item(args[1], args[2], value);
			Item.addItem(item);
			System.out.println("Successfully added item " + item.getName());
		}));
	}

	public void addEquipmentCommands() {
		playState.addCommand(new GameCommand("equip", "<actor> <position> <item>", "Equip an item onto an actor", 4, args -> {
			GameActor actor = getActor(args[1]);
			if (actor == null) {
				System.out.println("Actor " + args[1] + " does not exist.");
				return;
			}
			if (!(actor instanceof HasEquipment)) {
				System.out.println("Actor " + actor.getName() + " does not have equipment.");
				return;
			}
			Item item = null;
			String itemName = "None";
			if (!args[3].equalsIgnoreCase("none") && !args[3].equalsIgnoreCase("null")) {
				item = Item.valueOf(args[3]);
				if (item == null) {
					System.out.println("Item " + args[3] + " does not exist.");
					return;
				}
				itemName = item.getName();
			}
			HasEquipment hasEquipment = (HasEquipment) actor;
			if (hasEquipment.getInventory().get(item) == 0) {
				System.out.println("Actor " + actor.getName() + " does not have the item " + itemName + ".");
				return;
			}
			args[2] = args[2].toLowerCase();
			if (hasEquipment.set(args[2], item)) {
				System.out.println("Successfully set " + actor.getName() + "'s " + args[2] + " to " + itemName + ".");
			} else {
				System.out.println("Unknown attribute " + args[2]);
			}
		}));
	}

	public void addSpellCommands() {
		playState.addCommand(new GameCommand("cast", "<actor> <spell>", "Make an actor cast a spell", 3, args -> {
			GameActor actor = getActor(args[1]);
			if (actor == null) {
				System.out.println("Actor " + args[1] + " does not exist.");
				return;
			} else if (!(actor instanceof HasSpells)) {
				System.out.println("Actor " + actor.getName() + " does not have spells.");
				return;
			}
			Spell spell = Spell.valueOf(args[2]);
			if (spell == null) {
				System.out.println("Spell " + args[2] + " does not exist.");
			}
			HasSpells hasSpells = (HasSpells) actor;
			if (hasSpells.getSpellBook().contains(spell)) {
				actor.setEnergy(actor.getEnergy() - spell.getCost());
				System.out.println(actor.getName() + " casted spell " + spell.getName() + " for " + spell.getCost() + " energy.");
				System.out.println(spell);
				System.out.println(actor.getName() + " has " + actor.getEnergy() + "/" + actor.getMaxEnergy() + " energy left.");
			} else {
				System.out.println(actor.getName() + " does not know " + spell.getName() + ".");
			}
		}));
		playState.addCommand(new GameCommand("learn", "<actor> <spell>", "Make an actor learn a spell", 3, args -> {
			GameActor actor = getActor(args[1]);
			if (actor == null) {
				System.out.println("Actor " + args[1] + " does not exist.");
				return;
			}
			Spell spell = Spell.valueOf(args[2]);
			if (spell == null) {
				System.out.println("Spell " + args[2] + " does not exist.");
				return;
			}
			if (!(actor instanceof HasSpells)) {
				System.out.println("Actor " + actor.getName() + " can not have spells.");
				return;
			}
			HasSpells hasSpells = (HasSpells) actor;
			if (hasSpells.getSpellBook().contains(spell)) {
				System.out.println("Actor " + actor.getName() + " already knows spell " + spell.getName() + ".");
				return;
			}
			hasSpells.getSpellBook().add(spell);
			System.out.println("Actor " + actor.getName() + " succesfully learned " + spell.getName());
		}));
		playState.addCommand(new GameCommand("create_spell", "<cost> <spell> <description>", "Create a spell with a cost, name, and description.", 4, args -> {
			int cost = parseInt(args[1]);
			args[2] = args[2].replace('_', ' ');
			args[3] = args[3].replace('_', ' ');
			Spell preExisting = Spell.valueOf(args[2]);
			if (preExisting != null) {
				System.out.println("Spell " + preExisting.getName() + " already exists.");
			} else {
				Spell spell = new Spell(cost, args[2], args[3]);
				Spell.addSpell(spell);
				System.out.println("Successfully created spell " + spell);
			}
		}));
	}

	public void addMiscCommands() {
		playState.addCommand(new GameCommand("names", "Generate 10 names", 1, args -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(CharacterNameGenerator.generateCharacterName());
			}
		}));
		playState.addCommand(new GameCommand("roll", "[<number of dice>]d<number of faces>", "Roll a number of dice", 2, args -> {
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
					numSides = parseInt(args[1].substring(1));
				} catch (RuntimeException e) {
					return;
				}
				System.out.println("Roll: " + NumberGenerator.generateNumber(numSides));
			} else {
				System.out.println("Invalid roll");
			}
		}));
		playState.addCommand(new GameCommand("save", "Saves the game", 1, args -> playState.save()));
	}

	private Integer parseInt(String value) {
		try {
			int i = Integer.parseInt(value);
			return i;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Parse int failure. Autosaving...");
			playState.save();
			return null;
		}
	}

	private Float parseFloat(String value) {
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Parse float failure. Autosaving...");
			playState.save();
			return null;
		}
	}

	private GameActor getActor(String name) {
		GameActor actor = playState.getActors().stream().filter(a -> a.getName().equalsIgnoreCase(name.replace('_', ' '))).findFirst().orElse(null);
		return actor;
	}
}
