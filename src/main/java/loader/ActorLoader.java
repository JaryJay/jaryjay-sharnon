package loader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import game.actor.GameActor;
import game.actor.Human;
import game.gameclass.GameClass;

public class ActorLoader {

	private static final String SAVE_LOCATION = "C:\\Users\\Jay\\Documents\\GitHub\\jaryjay-sharnon\\src\\main\\resources\\%s.json";

	public static Human createHuman(String name) {
		return new Human((name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()).replace('_', ' '),
				GameClass.ARCHER, 10, 10, 5, 6, 0);
	}

	public static Human loadHuman(String name) {
		Gson gson = new GsonBuilder().create();
		try (JsonReader reader = new JsonReader(new FileReader(String.format(SAVE_LOCATION, name.replace('_', ' '))))) {
			return gson.fromJson(reader, Human.class);
		} catch (IOException e) {
			return null;
		}
	}

	public static GameActor loadActor(String name, Class<? extends GameActor> actorClass) {
		Gson gson = new GsonBuilder().create();
		try (JsonReader reader = new JsonReader(new FileReader(String.format(SAVE_LOCATION, name.replace('_', ' '))))) {
			return gson.fromJson(reader, actorClass);
		} catch (IOException e) {
			return null;
		}
	}

	public static void saveActor(GameActor actor) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(String.format(SAVE_LOCATION, actor.getName().replace(' ', '_')))) {
			gson.toJson(actor, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
