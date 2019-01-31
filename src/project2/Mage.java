package project2;

import org.newdawn.slick.Input;

public class Mage extends Movable {

	private int dir;
	private float playerX;
	private float playerY;
	private static boolean playerHasMoved = false;
	private int sign;

	public static final String TYPE = "Mage";

	public Mage(String image_src, float x, float y, String tag) {
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
		if (playerHasMoved) {

			playerHasMoved = false;
			// find player and get x and y coordinate
			for (Sprite sprite : World.getCharacterSprites()) {
				if (sprite.getTag().equals("Player")){
					playerX = sprite.getX();
					playerY = sprite.getY();
				}
			}

			// Mage algorithm
			float diffX = playerX - this.getX();
			float diffY = playerY - this.getY();
			sign = 1;

			if (diffX <= 0) {
				sign = -1;
			}

			if (Math.abs(diffX) > Math.abs(diffY)) {
				if (sign == -1) {
					dir = Sprite.DIR_LEFT;

					if (!this.checkWallCollision(dir) && !this.checkCollisionOfBlocks(dir)) {
						this.move(-App.TILE_SIZE, 0);
					}
				} else {
					dir = Sprite.DIR_RIGHT;
					if (!this.checkWallCollision(dir) && !this.checkCollisionOfBlocks(dir)) {
						this.move(+App.TILE_SIZE, 0);
					}
				}
			} else {
				if (sign == -1) {
					dir = Sprite.DIR_UP;
					if (!this.checkWallCollision(dir) && !this.checkCollisionOfBlocks(dir)) {
						this.move(0, -App.TILE_SIZE);
					}
				} else {
					dir = Sprite.DIR_DOWN;
					if (!this.checkWallCollision(dir) && !this.checkCollisionOfBlocks(dir)) {
						this.move(0, +App.TILE_SIZE);
					}
				}
			}
		}

	}

	public static void setPlayerHasMoved(boolean hasMoved) {
		playerHasMoved = hasMoved;
	}

}
