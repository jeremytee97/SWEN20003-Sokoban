package project2;

public class Movable extends Sprite {

	private boolean onTarget;

	public Movable(String image_src, float x, float y, String tag) {
		super(image_src, x, y, tag);
		// TODO Auto-generated constructor stub
	}

	/**
	 * check if the pushableSprite has a collision with another sprite or a wall
	 * 
	 * idea retrieved from:
	 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
	 * 
	 * @param dir
	 *            - direction
	 * @return boolean if it is pushable at that direction
	 */
	public boolean checkPushableSpriteCollision(int dir) {

		Movable character = null;
		for (Sprite s : World.getCharacterSprites()) {
			if (this.getTag().equals(s.getTag())) {
				character = (Movable) s;
			}
		}

		if (dir == DIR_LEFT) {

			for (int i = 0; i < World.getPushableSprites().size(); i++) {
				Movable pushableSprite = (Movable) World.getPushableSprites().get(i);
				if (character.isLeftCollision(pushableSprite)) {
					for (int j = 0; j < World.getPushableSprites().size(); j++) {
						Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
						if (!pushableSprite.equals(pushableSprite2)) {
							if (pushableSprite.isLeftCollision(pushableSprite2)) {
								return true;
							}
						}
						// check if there is a wall
						if (!pushableSprite.getTag().equals("Tnt") && pushableSprite.checkWallCollision(dir)) {
							return true;
						}
					}

					// only player and rogue can push objects
					if (character.getTag().equals(Player.TYPE) || character.getTag().equals(Rogue.TYPE)) {

						// if pushableSprite is an ice
						if (pushableSprite.getTag().equals("Ice")) {
							Ice ice = (Ice) pushableSprite;
							ice.setDir(dir);
							ice.setPushed(true);
						}
						// if pushableSprite is tnt
						else if (pushableSprite.getTag().equals("Tnt")) {
							Cracked cracked = null;
							for (Sprite sprite : World.getNonMovableSprites()) {
								if (sprite.getTag().equals("Cracked")) {
									cracked = (Cracked) sprite;
								}
							}

							// if its on crackedWall
							if ((pushableSprite.getX() - App.TILE_SIZE) == cracked.getX()
									&& pushableSprite.getY() == cracked.getY()) {
								Tnt.setCracked(true);
							} else {
								pushableSprite.move(-App.TILE_SIZE, 0);
							}
						} else {
							pushableSprite.move(-App.TILE_SIZE, 0);
						}
					}
					World.isSwitchOpen();
				}
			}
			return false;

		} else if (dir == DIR_RIGHT) {

			for (int i = 0; i < World.getPushableSprites().size(); i++) {
				Movable pushableSprite = (Movable) World.getPushableSprites().get(i);
				if (character.isRightCollision(pushableSprite)) {
					for (int j = 0; j < World.getPushableSprites().size(); j++) {
						Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
						if (!pushableSprite.equals(pushableSprite2)) {
							if (pushableSprite.isRightCollision(pushableSprite2)) {
								return true;
							}
						}
						// check if there is a wall
						if (!pushableSprite.getTag().equals("Tnt") && pushableSprite.checkWallCollision(dir)) {
							return true;
						}
					}

					// only player and rogue can push blocks
					if (character.getTag().equals(Player.TYPE) || character.getTag().equals(Rogue.TYPE)) {

						// if pushableSprite is an ice
						if (pushableSprite.getTag().equals("Ice")) {
							Ice ice = (Ice) pushableSprite;
							ice.setDir(dir);
							ice.setPushed(true);
						}
						// if pushableSprite is a tnt
						else if (pushableSprite.getTag().equals("Tnt")) {
							Cracked cracked = null;
							for (Sprite sprite : World.getNonMovableSprites()) {
								if (sprite.getTag().equals("Cracked")) {
									cracked = (Cracked) sprite;
								}
							}
							// if its on crackedWall
							if ((pushableSprite.getX() + App.TILE_SIZE) == cracked.getX()
									&& pushableSprite.getY() == cracked.getY()) {
								Tnt.setCracked(true);
							} else {
								pushableSprite.move(+App.TILE_SIZE, 0);
							}

						} else {
							pushableSprite.move(+App.TILE_SIZE, 0);
						}
					}
					World.isSwitchOpen();
				}
			}
			return false;

		} else if (dir == DIR_UP) {

			for (int i = 0; i < World.getPushableSprites().size(); i++) {
				Movable pushableSprite = (Movable) World.getPushableSprites().get(i);
				// if there is collision at the top of the character
				if (character.isTopCollision(pushableSprite)) {
					// skeleton character will be block by blocks
					if (character.getTag().equals("Skeleton")) {
						return true;
					}
					for (int j = 0; j < World.getPushableSprites().size(); j++) {
						Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
						if (!pushableSprite.equals(pushableSprite2)) {
							if (pushableSprite.isTopCollision(pushableSprite2)) {
								return true;
							}
						}

						// check if it is block by a wall
						if (!pushableSprite.getTag().equals("Tnt") && pushableSprite.checkWallCollision(dir)) {
							return true;
						}
					}

					// only player and rogue can push blocks
					if (character.getTag().equals(Player.TYPE) || character.getTag().equals(Rogue.TYPE)) {

						// if pushableSprite is ice
						if (pushableSprite.getTag().equals("Ice")) {
							Ice ice = (Ice) pushableSprite;
							ice.setDir(dir);
							ice.setPushed(true);
						}

						// if pushableSprite is tnt
						else if (pushableSprite.getTag().equals("Tnt")) {

							Cracked cracked = null;
							for (Sprite sprite : World.getNonMovableSprites()) {
								if (sprite.getTag().equals("Cracked")) {
									cracked = (Cracked) sprite;
								}
							}
							// if its on crackedWall
							if (pushableSprite.getX() == cracked.getX()
									&& (pushableSprite.getY() - App.TILE_SIZE) == cracked.getY()) {
								Tnt.setCracked(true);
							} else {
								pushableSprite.move(0, -App.TILE_SIZE);
							}
						} else {
							pushableSprite.move(0, -App.TILE_SIZE);
						}

					}
					World.isSwitchOpen();
				}
			}
			return false;

		} else if (dir == DIR_DOWN) {

			for (int i = 0; i < World.getPushableSprites().size(); i++) {
				Movable pushableSprite = (Movable) World.getPushableSprites().get(i);

				// if there is collision at the bottom of the player
				if (character.isBottomCollision(pushableSprite)) {
					// skeleton will be block by blocks
					if (character.getTag().equals("Skeleton")) {
						return true;
					}
					for (int j = 0; j < World.getPushableSprites().size(); j++) {
						Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
						if (!pushableSprite.equals(pushableSprite2)) {
							if (pushableSprite.isBottomCollision(pushableSprite2)) {
								return true;
							}
						}
						// check if it is block by a wall
						if (!pushableSprite.getTag().equals("Tnt") && pushableSprite.checkWallCollision(dir)) {
							return true;
						}
					}

					// only player and rogue can push blocks
					if (character.getTag().equals(Player.TYPE) || character.getTag().equals(Rogue.TYPE)) {

						// if pushableSprite is an Ice
						if (pushableSprite.getTag().equals("Ice")) {
							Ice ice = (Ice) pushableSprite;
							ice.setDir(dir);
							ice.setPushed(true);

						}

						// if pushableSprite is a tnt
						else if (pushableSprite.getTag().equals("Tnt")) {

							Cracked cracked = null;
							for (Sprite sprite : World.getNonMovableSprites()) {
								if (sprite.getTag().equals("Cracked")) {
									cracked = (Cracked) sprite;
								}
							}

							// if tnt on crackedWall
							if (pushableSprite.getX() == cracked.getX()
									&& (pushableSprite.getY() + App.TILE_SIZE) == cracked.getY()) {
								Tnt.setCracked(true);
							} else {
								pushableSprite.move(0, +App.TILE_SIZE);
							}
						} else {
							pushableSprite.move(0, +App.TILE_SIZE);
						}
					}
					World.isSwitchOpen();
				}
			}
		}
		return false;
	}

	/**
	 * check if there is a wall collision at the direction it is heading to
	 * 
	 * * idea retrieved from:
	 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
	 * 
	 * @param dir
	 *            direction
	 * @return boolean (if there is a wall collision )
	 */
	public boolean checkWallCollision(int dir) {
		if (dir == DIR_LEFT) {
			for (int i = 0; i < World.getNonMovableSprites().size(); i++) {
				if (World.getNonMovableSprites().get(i).getTag().equals("Wall")
						|| World.getNonMovableSprites().get(i).getTag().equals("Door")
						|| World.getNonMovableSprites().get(i).getTag().equals("Cracked")) {
					Sprite block = World.getNonMovableSprites().get(i);
					if (this.isLeftCollision(block)) {
						return true;
					}
				}
			}
			return false;
		}

		else if (dir == DIR_RIGHT) {
			for (int i = 0; i < World.getNonMovableSprites().size(); i++) {
				if (World.getNonMovableSprites().get(i).getTag().equals("Wall")
						|| World.getNonMovableSprites().get(i).getTag().equals("Door")
						|| World.getNonMovableSprites().get(i).getTag().equals("Cracked")) {
					Sprite block = World.getNonMovableSprites().get(i);
					if (this.isRightCollision(block)) {
						return true;
					}
				}
			}
			return false;
		}

		else if (dir == DIR_UP) {
			for (int i = 0; i < World.getNonMovableSprites().size(); i++) {
				if (World.getNonMovableSprites().get(i).getTag().equals("Wall")
						|| World.getNonMovableSprites().get(i).getTag().equals("Door")
						|| World.getNonMovableSprites().get(i).getTag().equals("Cracked")) {
					Sprite block = World.getNonMovableSprites().get(i);
					if (this.isTopCollision(block)) {
						return true;
					}
				}
			}

			return false;
		}

		else if (dir == DIR_DOWN) {

			for (int i = 0; i < World.getNonMovableSprites().size(); i++) {
				if (World.getNonMovableSprites().get(i).getTag().equals("Wall")
						|| World.getNonMovableSprites().get(i).getTag().equals("Door")
						|| World.getNonMovableSprites().get(i).getTag().equals("Cracked")) {
					Sprite block = World.getNonMovableSprites().get(i);
					if (this.isBottomCollision(block)) {
						return true;
					}
				}
			}
			return false;
		}

		return false;
	}

	/**
	 * 
	 * @param dir
	 * @return if collision of block at that direction
	 */
	public boolean checkCollisionOfBlocks(int dir) {
		if (dir == DIR_LEFT) {
			for (int j = 0; j < World.getPushableSprites().size(); j++) {
				Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
				if (!this.equals(pushableSprite2)) {
					if (this.isLeftCollision(pushableSprite2)) {
						return true;
					}
				}
				if (this.checkWallCollision(dir)) {
					return true;
				}
			}
		}

		else if (dir == DIR_RIGHT) {
			for (int j = 0; j < World.getPushableSprites().size(); j++) {
				Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
				if (!this.equals(pushableSprite2)) {
					if (this.isRightCollision(pushableSprite2)) {
						return true;
					}
				}
				if (this.checkWallCollision(dir)) {
					return true;
				}
			}
		}

		else if (dir == DIR_UP) {
			for (int j = 0; j < World.getPushableSprites().size(); j++) {
				Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
				if (!this.equals(pushableSprite2)) {
					if (this.isTopCollision(pushableSprite2)) {
						return true;
					}
				}
				if (this.checkWallCollision(dir)) {
					return true;
				}
			}
		}

		else if (dir == DIR_DOWN) {
			for (int j = 0; j < World.getPushableSprites().size(); j++) {
				Movable pushableSprite2 = (Movable) World.getPushableSprites().get(j);
				if (!this.equals(pushableSprite2)) {
					if (this.isBottomCollision(pushableSprite2)) {
						return true;
					}
				}
				if (this.checkWallCollision(dir)) {
					return true;
				}
			}
		}

		return false;

	}

	public void setOnTarget(boolean onTarget) {
		this.onTarget = onTarget;
	}

	public boolean isOnTarget() {
		// TODO Auto-generated method stub
		return onTarget;
	}

}
