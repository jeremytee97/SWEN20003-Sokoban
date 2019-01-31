package project2;

import org.newdawn.slick.Input;

public class Player extends Movable {

	private static int nMoves = 0;
	private int dir;
	public static final String TYPE = "Player";

	public Player(String image_src, float x, float y, String tag) {
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
		int dir = DIR_NONE;

		// restart level with key r
		if (input.isKeyPressed(Input.KEY_R)) {
			World.restartLevel();
		}

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			Rogue.setPlayerHasMoved(true);
			Mage.setPlayerHasMoved(true);
			dir = DIR_LEFT;
			nMoves++;
			if (this.checkWallCollision(dir)) {
				return;
			}

			if (this.checkPushableSpriteCollision(dir)) {
				return;
			}

			this.move(-App.TILE_SIZE, 0);

		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			Rogue.setPlayerHasMoved(true);
			Mage.setPlayerHasMoved(true);
			dir = DIR_RIGHT;
			nMoves++;
			if (this.checkWallCollision(dir)) {
				return;
			}

			if (this.checkPushableSpriteCollision(dir)) {
				return;
			}

			this.move(+App.TILE_SIZE, 0);

		} else if (input.isKeyPressed(Input.KEY_UP)) {
			Rogue.setPlayerHasMoved(true);
			Mage.setPlayerHasMoved(true);
			dir = DIR_UP;
			nMoves++;
			if (this.checkWallCollision(dir)) {
				return;
			}

			if (this.checkPushableSpriteCollision(dir)) {
				return;
			}

			this.move(0, -App.TILE_SIZE);

		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			Rogue.setPlayerHasMoved(true);
			Mage.setPlayerHasMoved(true);
			dir = DIR_DOWN;
			nMoves++;
			if (this.checkWallCollision(dir)) {
				return;
			}

			if (this.checkPushableSpriteCollision(dir)) {
				return;
			}

			this.move(0, +App.TILE_SIZE);

		}

		// check if player is dead
		World.checkDeath(this.getX(), this.getY());

		// check if level is completed
		World.isCompleted();
	}

	public static int getNMoves() {
		return nMoves;
	}

	public static void setNMoves(int moves) {
		nMoves = moves;
	}

}
