package project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
	private static String[][] types;

	private static int world_width;
	private static int world_height;
	private static int offset_x;
	private static int offset_y;

	/**
	 * Loads the sprites from a given file.
	 * 
	 * @param filename
	 */
	public static void loadFile(String filename) {
		// ArrayList<Sprite> list = new ArrayList<>();

		// Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;

			// Find the world size
			line = reader.readLine();
			String[] parts = line.split(",");
			world_width = Integer.parseInt(parts[0]);
			world_height = Integer.parseInt(parts[1]);

			// Create the array of object types
			types = new String[world_width][world_height];

			// Calculate the top left of the tiles so that the level is
			// centred
			offset_x = (App.SCREEN_WIDTH - world_width * App.TILE_SIZE) / 2;
			offset_y = (App.SCREEN_HEIGHT - world_height * App.TILE_SIZE) / 2;

			// Loop over every line of the file
			while ((line = reader.readLine()) != null) {
				String name;
				float x, y;

				// Split the line into parts
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				types[(int) x][(int) y] = name;

				// Adjust for the grid
				x = offset_x + x * App.TILE_SIZE;
				y = offset_y + y * App.TILE_SIZE;

				World.createSprite(name, x, y);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getOffset_x() {
		return offset_x;
	}

	public static int getOffset_y() {
		return offset_y;
	}

	public static String[][] getTypes() {
		return types;
	}

}
