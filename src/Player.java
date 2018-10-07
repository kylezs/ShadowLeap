import helper.Constants;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;
/**
 * Class to handle the player
 * @author Kyle
 *
 */
public class Player extends Sprite {


	private int lives = Constants.INITIAL_LIVES;
	private static boolean onScreen = true;
	private int winningHolesFilled = 0;

	/**
	 * Initialise a player object with the src to the image and a position (x, y)
	 * @param imageSrc
	 * @param x
	 * @param y
	 */
	Player(String imageSrc, float x, float y) {
		super(imageSrc, x, y);
	}

	/**
	 * 
	 * @return onScreen (boolean)
	 */
	public boolean getOnScreen() {
		return onScreen;
	}

	/**
	 * Send player back to the start position
	 */
	public void backToStart() {
		this.position.setX(Constants.START_PLAYER_X);
		this.position.setY(Constants.START_PLAYER_Y);
		onScreen = true;
	}

	/**
	 * If a hole has been filled update the total filled holes
	 * If all a filled, progress to next level
	 * Otherwise, send the 
	 */
	public void filledHole() {
		this.winningHolesFilled++;
		if (this.winningHolesFilled == Constants.HOLES_TO_FILL) {
			App.nextLevel();
			if (App.getCurrentLevel() < Constants.MAX_LEVEL) {
				try {
					// Reinitialise the game on the new level
					App.getApp().reinit();
				} catch (SlickException e) {
					e.printStackTrace();
				}
			} else {
				gameOver();
			}
		}
	}

	/**
	 * 
	 * @return winningHolesFilled
	 */
	public int getWinningHolesFilled() {
		return winningHolesFilled;
	}

	/**
	 * Called when player collects a new life object
	 * Increments the number of lives a player has if it's less than MAX_LIVES
	 */
	public void addLife() {
		if (this.lives < Constants.MAX_LIVES) {
			this.lives++;
		}
	}

	/**
	 * Returns the number of lives the player has left
	 * @return number of lives
	 */
	public int getLives() {
		return this.lives;
	}

	/**
	 * Ends the game by closing the window
	 */
	public void gameOver() {
		App.getApp().exit();
	}

	/**
	 * Called when player has interacted with an object that kills it, returns player to start
	 * If has already lost all lives, end the game.
	 */
	public void dead() {
		if (lives > 0) {
			this.lives--;
			backToStart();
		} else {
			gameOver();
		}
	}

	/**
	 * Checks if the player is contacting another Sprite
	 * @param other
	 * @return true if the player is intersecting other's bounding box, false otherwise
	 */
	public boolean isContacting(Sprite other) {
		return this.getBoundingBox().intersects(other.getBoundingBox());
	}

	/**
	 * Checks if player is contacting a tile
	 * @param tile
	 * @return true if player's bounding box is intersecting a tile's bounding box
	 */
	public boolean isContacting(CollideTile tile) {
		return this.getBoundingBox().intersects(tile.boundingBox);
	}
	
	@Override
	public void update(Input input, int delta) {
		// Never called
	}

	/**
	 * Before updating the players movement, ensure it's not going to be on a solid tile or solid enemy
	 * If it is, don't update the movement
	 * Handle the up, down, right, left arrow keys as movement in that direction by one tile
	 * Player cannot jump off screen
	 * @param input
	 * @param delta
	 * @param solidTiles
	 * @param solidEnemies
	 */
	public void update(Input input, int delta, ArrayList<SolidTile> solidTiles, ArrayList<SolidEnemy> solidEnemies) {
		float newX = position.getX();
		float newY = position.getY();

		// player has to be all the way over
		if (newX > Constants.SCREEN_WIDTH + Constants.TILE_SIZE / 2 || newX < -Constants.TILE_SIZE/2 || newY < -Constants.TILE_SIZE/2 || newY > Constants.SCREEN_HEIGHT + Constants.TILE_SIZE/2) {
			onScreen = false;
		}

		// move the player one tile in whatever direction key is pressed
		if (input.isKeyPressed(Input.KEY_UP)) {
			float newPos = position.getY() - Constants.TILE_SIZE;
			if (!(newPos <= 0 - Constants.TILE_SIZE)) {
				newY = newPos;
			}
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			float newPos = position.getY() + Constants.TILE_SIZE;
			if (!(newPos >= Constants.SCREEN_HEIGHT)) {
				newY = newPos;
			}
		}

		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			float newPos = position.getX() + Constants.TILE_SIZE;
			if (!(newPos >= Constants.SCREEN_WIDTH - Constants.TILE_OFFSET)) {
				newX = newPos;
			}
		}

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			float newPos = position.getX() - Constants.TILE_SIZE;
			if (!(newPos <= 0 - Constants.TILE_SIZE + Constants.TILE_OFFSET)) {
				newX = newPos;
			}
		}
		// if there are no intersecting solid tiles or solid enemies, move the player
		BoundingBox afterMoveBB = new BoundingBox(this.spriteImage, newX, newY);
		boolean intersects = false;
		for (SolidTile tile : solidTiles) {

			if (afterMoveBB.intersects(tile.boundingBox)) {
				intersects = true;
			}        
		}

		for (SolidEnemy enemy : solidEnemies) {
			if (afterMoveBB.intersects(enemy.getBoundingBox())) {
				intersects = true;
			}  
		}
		
		if (!intersects) {
			this.position.setX(newX);
			this.position.setY(newY);
			this.updateBoundingBox(newX, newY);
		}
	}

	@Override
	public void contactPlayer(Player player) {
		System.out.println("Error::: Calling player against itself");
		App.getApp().exit();
	}


}
