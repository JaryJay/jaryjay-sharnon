package loader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import game.inventory.Item;

public class ItemLoader {

	private static final String SAVE_LOCATION = "C:\\Users\\Jay\\Documents\\GitHub\\jaryjay-sharnon\\src\\main\\resources\\items\\items.json";

	private ItemLoader() {
	}

	public static TreeSet<Item> loadItems() {
		Gson gson = new GsonBuilder().create();
		try (JsonReader reader = new JsonReader(new FileReader(SAVE_LOCATION))) {
			return gson.fromJson(reader, new TypeToken<TreeSet<Item>>() {
			}.getType());
		} catch (IOException e) {
			return null;
		}
	}

	public static void saveItems(TreeSet<Item> items) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(SAVE_LOCATION)) {
			gson.toJson(items, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
