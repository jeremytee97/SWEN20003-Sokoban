package project2;

import org.newdawn.slick.Input;

public class Rogue extends Movable {
	private static boolean playerHasMoved;
	private int dir = DIR_LEFT;
	public static final String TYPE = "Rogue";

	public Rogue(String image_src, float x, float y, String tag) {
		super(image_src, x, y, tag);
		// TODO Auto-generated constructor stub
	}

	public static void setPlayerHasMoved(boolean hasMoved) {
		playerHasMoved = hasMoved;
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

		// update only if player has moved
		if (Rogue.playerHasMoved) {
			// change direction if it is block
			if (this.checkWallCollision(dir) || this.checkPushableSpriteCollision(dir)) {
				if (dir == Sprite.DIR_LEFT) {
					dir = Sprite.DIR_RIGHT;
					Rogue.setPlayerHasMoved(false);
				}

				else if (dir == Sprite.DIR_RIGHT) {
					dir = Sprite.DIR_LEFT;
					Rogue.setPlayerHasMoved(false);
				}

				return;
			}

			if (dir == Sprite.DIR_RIGHT) {
				this.move(+App.TILE_SIZE, 0);
				Rogue.setPlayerHasMoved(false);
			}

			else if (dir == Sprite.DIR_LEFT) {
				this.move(-App.TILE_SIZE, 0);
				Rogue.setPlayerHasMoved(false);
			}

		}

	}

}
