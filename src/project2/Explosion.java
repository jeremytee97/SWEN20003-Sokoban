package project2;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Explosion extends Sprite {
	public static final String TYPE = "Explosion";
	private int time = 0;
	private int maxTime = 400;
	private static boolean timeStart = false;
	private static boolean destroyed = false;

	public static void setTimeStart(boolean timeStart) {
		Explosion.timeStart = timeStart;
	}

	public Explosion(String image_src, float x, float y, String tag) {
		super(image_src, x, y, tag);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Update the game state for a frame.
	 * 
	 * @param input
	 *            The Slick game input
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) {

		// count the time and after 0.4 sec set it to true
		if (timeStart) {
			time += delta;
		}

		if (time >= maxTime) {
			destroyed = true;
		}

	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */

	public void render(Graphics g) {
		// if true do not render image
		if (destroyed) {
		}

		else {
			this.getImage().drawCentered(this.getX(), this.getY());
		}
	}

	public static void setDestroyed(boolean destroyed) {
		Explosion.destroyed = destroyed;
	}

}
