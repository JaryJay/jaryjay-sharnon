package loader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import game.actor.Player;

public class PlayerLoader {

	private static final String SAVE_LOCATION = "C:\\Users\\Jay\\Documents\\GitHub\\jaryjay-sharnon\\src\\main\\resources\\%s.json";

	public static Player loadPlayer(String name) {
		Gson gson = new GsonBuilder().create();
		try (JsonReader reader = new JsonReader(new FileReader(String.format(SAVE_LOCATION, name)))) {
			return gson.fromJson(reader, Player.class);
		} catch (IOException e) {
			return new Player(10, 10, 10, 10, 10, "New player");
		}
	}

	public static void savePlayer(Player player) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(String.format(SAVE_LOCATION, player.getName()))) {
			gson.toJson(player, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Player player = new Player(1, 2, 3, 4, 5, "Wei");
		savePlayer(player);
		System.out.println(loadPlayer("Wei"));
	}

}
