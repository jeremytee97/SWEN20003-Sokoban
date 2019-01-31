package project2;

import org.newdawn.slick.Input;

public class Tnt extends Pushable {
	public static final String TYPE = "Tnt";

	private static boolean onCracked = false;
	private int time = 0;
	private int maxTime = 400;

	public Tnt(String image_src, float x, float y, String tag) {
		super(image_src, x, y, tag);
		// TODO Auto-generated constructor stub
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

		// update only if its on top of cracked wall
		if (onCracked) {
			World.tntOnCracked();
			Explosion.setTimeStart(true);
		}

	}

	public static void setCracked(boolean b) {
		onCracked = b;
	}

}
