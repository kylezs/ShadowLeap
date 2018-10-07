import helper.Constants;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Player extends Sprite {

  
  private int lives = Constants.INITIAL_LIVES;
  private static boolean onScreen = true;
  private int winningHolesFilled = 0;

  Player(String imageSrc, float x, float y) {
    super(imageSrc, x, y);
  }
  
  public boolean getOnScreen() {
	  return onScreen;
  }
  
  public void backToStart() {
	  this.position.setX(Constants.START_PLAYER_X);
	  this.position.setY(Constants.START_PLAYER_Y);
	  onScreen = true;
  }
  
  public void filledHole() {
	  this.winningHolesFilled++;
	  if (this.winningHolesFilled == Constants.HOLES_TO_FILL) {
		  App.nextLevel();
		  if (App.getCurrentLevel() < Constants.MAX_LEVEL) {
			  try {
					App.getApp().reinit();
				} catch (SlickException e) {
					e.printStackTrace();
				}
		  } else {
			  gameOver();
		  }
	  }
  }
  
  public int getWinningHolesFilled() {
	  return winningHolesFilled;
  }
  
  /**
   * Called when player collects a new life object
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
   * Called when player has interacted with an object that kills it
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
      if (!(newPos >= Constants.SCREEN_WIDTH - Constants.TILE_OFFSET - Constants.TILE_SIZE)) {
        newX = newPos;
      }
    }

    if (input.isKeyPressed(Input.KEY_LEFT)) {
      float newPos = position.getX() - Constants.TILE_SIZE;
      if (!(newPos <= 0 - Constants.TILE_SIZE + Constants.TILE_OFFSET)) {
        newX = newPos;
      }
    }
  
    BoundingBox afterMoveBB = new BoundingBox(this.spriteImage, newX, newY);
    boolean intersects = false;
    for (SolidTile tile : solidTiles) {
    	// if there are no intersecting tiles, move the player
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
    // update with new possible x and y, check if would be on a solid tile, if yes, don't move, reset BoundingBox
  }

	@Override
	public void contactPlayer(Player player) {
		System.out.println("Error::: Calling player against itself");
		App.getApp().exit();
		}
}
