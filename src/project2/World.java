package project2;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {

	private static ArrayList<Sprite> pushableSprites = new ArrayList<>();
	private static ArrayList<Sprite> nonMovableSprites = new ArrayList<>();
	private static ArrayList<Sprite> characterSprites = new ArrayList<>();
	private static ArrayList<Sprite> destroyedSprites = new ArrayList<>();
	private static int currentLevel = 0;
	private static String currentFile = "res/Level/0.lvl";

	public World() {

		Loader.loadFile(currentFile);

	}

	/**
	 * removes the sprite from the arrayList and stores it in the
	 * destroyedSprite arrayList
	 * 
	 * @param name
	 *            - tag of a sprite
	 * 
	 */
	public static void destroySprite(String name) {
		for (int i = 0; i < nonMovableSprites.size(); i++) {
			if (nonMovableSprites.get(i).getTag().equals(name)) {
				Sprite destroySprite = nonMovableSprites.get(i);
				destroyedSprites.add(destroySprite);
				nonMovableSprites.remove(i);

			}

		}

		for (int i = 0; i < pushableSprites.size(); i++) {
			if (pushableSprites.get(i).getTag().equals(name)) {
				Sprite destroySprite = pushableSprites.get(i);
				destroyedSprites.add(destroySprite);
				pushableSprites.remove(i);

			}

		}
	}

	/**
	 * create sprite and stores it in its respective arrayList
	 * 
	 * @param name
	 *            of the tag
	 * @param x
	 *            coordinate (float)
	 * @param y
	 *            coordinate (float)
	 */
	public static void createSprite(String name, float x, float y) {
		switch (name) {
		case "wall":
			getNonMovableSprites().add(new Wall("res/wall.png", x, y, "Wall"));
			break;
		case "floor":
			getNonMovableSprites().add(new Floor("res/floor.png", x, y, "Floor"));
			break;
		case "stone":
			getPushableSprites().add(new Stone("res/stone.png", x, y, "Stone"));
			break;
		case "target":
			getNonMovableSprites().add(new Target("res/target.png", x, y, "Target"));
			break;
		case "player":
			getCharacterSprites().add(new Player("res/player_left.png", x, y, "Player"));
			break;
		case "cracked":
			getNonMovableSprites().add(new Cracked("res/cracked_wall.png", x, y, "Cracked"));
			break;
		case "tnt":
			getPushableSprites().add(new Tnt("res/tnt.png", x, y, "Tnt"));
			break;
		case "mage":
			getCharacterSprites().add(new Mage("res/mage.png", x, y, "Mage"));
			break;
		case "rogue":
			getCharacterSprites().add(new Rogue("res/rogue.png", x, y, "Rogue"));
			break;
		case "skeleton":
			getCharacterSprites().add(new Skeleton("res/skull.png", x, y, "Skeleton"));
			break;
		case "ice":
			getPushableSprites().add(new Ice("res/ice.png", x, y, "Ice"));
			break;
		case "door":
			getNonMovableSprites().add(new Door("res/door.png", x, y, "Door"));
			break;
		case "switch":
			getNonMovableSprites().add(new Switch("res/switch.png", x, y, "Switch"));
			break;
		case "explosion":
			getNonMovableSprites().add(new Explosion("res/explosion.png", x, y, "Explosion"));

		}
	}

	/**
	 * Update the game state for a frame.
	 * 
	 * @param input
	 *            - The Slick game input
	 * @param delta
	 *            - Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) {

		for (int i = 0; i < characterSprites.size(); i++) {
			if (characterSprites.get(i) != null) {
				characterSprites.get(i).update(input, delta);
			}
		}

		for (Sprite sprite : nonMovableSprites) {
			if (sprite != null) {
				sprite.update(input, delta);
			}
		}

		for (Sprite sprite : pushableSprites) {
			if (sprite != null) {
				sprite.update(input, delta);
			}
		}

	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            - The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) {

		for (Sprite sprite : nonMovableSprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}

		for (Sprite sprite : pushableSprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}

		for (Sprite sprite : characterSprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}

		g.drawString("Moves: " + Player.getNMoves(), 10, 25);
	}

	/**
	 * check whether all the targets are occupied by pushableSprites
	 */
	public static void isCompleted() {

		int numberOfTargets = 0;
		for (int j = 0; j < nonMovableSprites.size(); j++) {
			if (nonMovableSprites.get(j).getTag().equals("Target")) {
				numberOfTargets++;
			}
		}

		int onTarget = 0;

		for (int i = 0; i < pushableSprites.size(); i++) {
			Pushable pushableSprite = (Pushable) pushableSprites.get(i);
			for (int j = 0; j < nonMovableSprites.size(); j++) {
				if (nonMovableSprites.get(j).getTag().equals("Target")) {
					Target target = (Target) nonMovableSprites.get(j);
					if (pushableSprite.getX() == target.getX() && pushableSprite.getY() == target.getY()) {
						onTarget++;
					}
				}
			}
		}

		if (numberOfTargets == onTarget) {
			currentLevel++;
			World.nextLevel();
		}

		numberOfTargets = 0;

	}

	/**
	 * check if the switch is open
	 * 
	 * @return boolean value
	 */
	public static boolean isSwitchOpen() {
		int numberOfPushableSprites = pushableSprites.size();

		for (int i = 0; i < numberOfPushableSprites; i++) {
			Pushable pushableSprite = (Pushable) pushableSprites.get(i);
			for (int j = 0; j < nonMovableSprites.size(); j++) {
				if (nonMovableSprites.get(j).getTag().equals("Switch")) {
					Switch target = (Switch) nonMovableSprites.get(j);
					if (pushableSprite.getX() == target.getX() && pushableSprite.getY() == target.getY()) {
						Switch.setSwitchOpen(true);
						destroySprite("Door");
						return true;
					}
				}
			}
		}

		if (destroyedSprites.size() > 0) {
			for (int i = 0; i < destroyedSprites.size(); i++) {
				if (destroyedSprites.get(i).getTag().equals("Door")) {
					Sprite createSprite = destroyedSprites.get(i);
					nonMovableSprites.add(createSprite);
					destroyedSprites.remove(i);
				}
			}

		}
		Switch.setSwitchOpen(false);
		return false;

	}

	/**
	 * check if the tnt is pushed to a cracked wall if yes - create explosion
	 * sprite
	 */
	public static void tntOnCracked() {

		for (int j = 0; j < nonMovableSprites.size(); j++) {
			if (nonMovableSprites.get(j).getTag().equals("Cracked")) {
				Cracked target = (Cracked) nonMovableSprites.get(j);
				float x = target.getX();
				float y = target.getY();

				destroySprite("Tnt");
				destroySprite("Cracked");
				createSprite("explosion", x, y);
			}
		}
	}

	public static ArrayList<Sprite> getCharacterSprites() {
		return characterSprites;
	}

	public static ArrayList<Sprite> getNonMovableSprites() {
		return nonMovableSprites;
	}

	public static ArrayList<Sprite> getPushableSprites() {
		return pushableSprites;
	}

	/**
	 * restart the current level
	 */
	public static void restartLevel() {
		pushableSprites.clear();
		nonMovableSprites.clear();
		characterSprites.clear();
		destroyedSprites.clear();
		Tnt.setCracked(false);
		Explosion.setDestroyed(false);
		Player.setNMoves(0);
		Loader.loadFile(currentFile);

	}

	/**
	 * load the next level when the current level is completed
	 */
	public static void nextLevel() {
		pushableSprites.clear();
		nonMovableSprites.clear();
		characterSprites.clear();
		destroyedSprites.clear();
		Player.setNMoves(0);
		switch (currentLevel) {

		case 1:
			currentFile = "res/Level/1.lvl";
			break;

		case 2:
			currentFile = "res/Level/2.lvl";
			break;

		case 3:
			currentFile = "res/Level/3.lvl";
			break;

		case 4:
			currentFile = "res/Level/4.lvl";
			break;

		case 5:
			currentFile = "res/Level/5.lvl";
			break;
		}

		Loader.loadFile(currentFile);
	}

	/**
	 * check if the character is dead (comes in contact with enemies)
	 * 
	 * @param x
	 *            - player x coordinate
	 * @param y
	 *            - player y coordinate
	 */
	public static void checkDeath(float x, float y) {
		for (int i = 0; i < characterSprites.size(); i++) {
			if (characterSprites.get(i).getTag().equals("Rogue") && characterSprites.get(i).getX() == x
					&& characterSprites.get(i).getY() == y) {
				restartLevel();
			}

			if (characterSprites.get(i).getTag().equals("Skeleton") && characterSprites.get(i).getX() == x
					&& characterSprites.get(i).getY() == y) {
				restartLevel();
			}

			if (characterSprites.get(i).getTag().equals("Mage") && characterSprites.get(i).getX() == x
					&& characterSprites.get(i).getY() == y) {
				restartLevel();
			}
		}
	}
}
