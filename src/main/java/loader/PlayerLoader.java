package loader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import game.actor.GameClass;
import game.actor.Human;

public class PlayerLoader {

	private static final String SAVE_LOCATION = "C:\\Users\\Jay\\Documents\\GitHub\\jaryjay-sharnon\\src\\main\\resources\\%s.json";

	public static Human createPlayer(String name) {
		return new Human(name, GameClass.ARCHER, 10, 10, 5, 6, 0);
	}

	public static Human loadPlayer(String name) {
		Gson gson = new GsonBuilder().create();
		try (JsonReader reader = new JsonReader(new FileReader(String.format(SAVE_LOCATION, name)))) {
			return gson.fromJson(reader, Human.class);
		} catch (IOException e) {
			return null;
		}
	}

	public static void savePlayer(Human player) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(String.format(SAVE_LOCATION, player.getName()))) {
			gson.toJson(player, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
