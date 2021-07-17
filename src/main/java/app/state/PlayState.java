package app.state;

import java.util.ArrayList;
import java.util.List;

import app.SharnonApp;
import game.actor.GameActor;
import game.actor.Human;
import game.inventory.Item;
import game.spell.Spell;
import loader.ActorLoader;
import loader.ItemLoader;
import loader.SpellLoader;

public class PlayState extends AppState {

	Human player;
	private List<GameActor> actors;

	public PlayState(SharnonApp app, Human player) {
		super(app);
		this.player = player;
		this.actors = new ArrayList<>();
		this.getActors().add(player);
	}

	@Override
	public void initCommands() {
		PlayStateCommandAdder commandAdder = new PlayStateCommandAdder(this);
		commandAdder.addActorCommands();
		commandAdder.addItemCommands();
		commandAdder.addEquipmentCommands();
		commandAdder.addSpellCommands();
		commandAdder.addMiscCommands();
	}

	@Override
	public void onQuit() {
		save();
	}

	void save() {
		ItemLoader.saveItems(Item.values);
		SpellLoader.saveSpells(Spell.values);
		for (GameActor actor : getActors()) {
			ActorLoader.saveActor(actor);
		}
		System.out.println("Save success.");
	}

	public List<GameActor> getActors() {
		return actors;
	}

}
