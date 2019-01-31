package project2;

import java.util.Timer;

import org.newdawn.slick.Input;

public class Skeleton extends Movable {

	private int dir = DIR_UP;
	private int time = 0;
	private int maxTime = 1000;

	public static final String TYPE = "Skeleton";

	public Skeleton(String image_src, float x, float y, String tag) {
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
		time += delta;

		// move every 1 second
		if (time >= maxTime) {
			time = 0;

			// change direction if it is block by wall
			if (this.checkWallCollision(dir)) {
				if (dir == Sprite.DIR_UP) {
					dir = Sprite.DIR_DOWN;
				} else if (dir == Sprite.DIR_DOWN) {
					dir = Sprite.DIR_UP;
				}

				return;
			}

			// change direction if it is block by a stone / ice
			else if (this.checkPushableSpriteCollision(dir)) {
				if (dir == Sprite.DIR_UP) {
					dir = Sprite.DIR_DOWN;
				}

				else if (dir == Sprite.DIR_DOWN) {
					dir = Sprite.DIR_UP;
				}
				return;

			} else {
				if (dir == Sprite.DIR_UP) {
					this.move(0, -App.TILE_SIZE);
				}

				else if (dir == Sprite.DIR_DOWN) {
					this.move(0, +App.TILE_SIZE);
				}

			}
		}
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

}
