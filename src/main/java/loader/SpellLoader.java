package loader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import game.spell.Spell;

public class SpellLoader {

	private static final String SAVE_LOCATION = "C:\\Users\\Jay\\Documents\\GitHub\\jaryjay-sharnon\\src\\main\\resources\\spells\\spells.json";

	private SpellLoader() {
	}

	public static TreeSet<Spell> loadSpells() {
		Gson gson = new GsonBuilder().create();
		try (JsonReader reader = new JsonReader(new FileReader(SAVE_LOCATION))) {
			return gson.fromJson(reader, new TypeToken<TreeSet<Spell>>() {
			}.getType());
		} catch (IOException e) {
			return null;
		}
	}

	public static void saveSpells(TreeSet<Spell> spells) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(SAVE_LOCATION)) {
			gson.toJson(spells, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
