package project2;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Sprite {

	// Used to decide what direction an object is moving
	// Look up enums to find a more elegant solution!
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	public static final String TYPE = "Sprite";

	private Image image = null;
	private float x;
	private float y;
	private String tag;

	public Sprite(String image_src, float x, float y, String tag) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		this.x = x;
		this.y = y;
		this.tag = tag;
		snapToGrid();
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

	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            - The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) {
		image.drawCentered(x, y);
	}

	public Image getImage() {
		return image;
	}

	/**
	 * Forces this sprite to align to the grid
	 */
	public void snapToGrid() {

		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		x = Math.round(x);
		y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}

	/**
	 * check if this object's left direction is the sprite object
	 * 
	 * * idea retrieved from:
	 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
	 * 
	 * @param sprite
	 * @return isLeftCollision (boolean)
	 */
	public boolean isLeftCollision(Sprite sprite) {
		if (((this.getX() - App.TILE_SIZE) == sprite.getX()) && (this.getY() == sprite.getY())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check if this object's right direction is the sprite object
	 * 
	 * * idea retrieved from:
	 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
	 * 
	 * @param sprite
	 * @return isRightCollision (boolean)
	 */
	public boolean isRightCollision(Sprite sprite) {
		if (((this.getX() + App.TILE_SIZE) == sprite.getX()) && (this.getY() == sprite.getY())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check if this object's up direction is the sprite object
	 * 
	 * * idea retrieved from:
	 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
	 * 
	 * @param sprite
	 * @return isTopCollision (boolean)
	 */
	public boolean isTopCollision(Sprite sprite) {
		if (((this.getY() - App.TILE_SIZE) == sprite.getY()) && (this.getX() == sprite.getX())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check if this object's down direction is the sprite object
	 * 
	 * * idea retrieved from:
	 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
	 * 
	 * 
	 * @param sprite
	 * @return isBottomCollision (boolean)
	 */
	public boolean isBottomCollision(Sprite sprite) {
		if (((this.getY() + App.TILE_SIZE) == sprite.getY()) && (this.getX() == sprite.getX())) {
			return true;
		} else {
			return false;
		}
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getTag() {
		return tag;
	}

	public void move(float x, float y) {
		this.setX(this.getX() + x);
		this.setY(this.getY() + y);
	}

}
