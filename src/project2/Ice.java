package project2;

import org.newdawn.slick.Input;

public class Ice extends Pushable {
	// private Timer timer = new Timer(true);
	private int time = 0;
	private int maxTime = 250;
	public static final String TYPE = "Ice";
	private int dir;
	private boolean isPushed = false;

	public Ice(String image_src, float x, float y, String tag) {
		super(image_src, x, y, tag);
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

		// update every 0.25 second
		if (time >= maxTime) {
			time = 0;

			// if it is pushed
			if (isPushed) {

				// check which direction it is pushed
				if (dir == Sprite.DIR_LEFT) {
					if (!this.checkWallCollision(dir)) {
						if (!this.checkCollisionOfBlocks(dir)) {
							this.move(-App.TILE_SIZE, 0);
							World.isSwitchOpen();
						} else {
							isPushed = false;
						}
					} else {
						isPushed = false;
					}
				} else if (dir == Sprite.DIR_RIGHT) {
					if (!this.checkWallCollision(dir)) {
						if (!this.checkCollisionOfBlocks(dir)) {
							this.move(+App.TILE_SIZE, 0);
							World.isSwitchOpen();
						} else {
							isPushed = false;
						}
					} else {
						isPushed = false;
					}
				} else if (dir == Sprite.DIR_UP) {
					if (!this.checkWallCollision(dir)) {
						if (!this.checkCollisionOfBlocks(dir)) {
							this.move(0, -App.TILE_SIZE);
							World.isSwitchOpen();
						} else {
							isPushed = false;
						}
					} else {
						isPushed = false;
					}
				} else if (dir == Sprite.DIR_DOWN) {
					if (!this.checkWallCollision(dir)) {
						if (!this.checkCollisionOfBlocks(dir)) {
							this.move(0, +App.TILE_SIZE);
							World.isSwitchOpen();
						} else {
							isPushed = false;
						}
					} else {
						isPushed = false;
					}
				}

			}
		}

	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public void setPushed(boolean isPushed) {
		this.isPushed = isPushed;
	}

}
