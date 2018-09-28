import helper.Constants;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import utilities.BoundingBox;

public class Player extends Sprite {

  
  private int lives;

  Player(String imageSrc, float x, float y) {
    super(imageSrc, x, y);
    lives = Constants.INITIAL_LIVES;
  }
  
  public void backToStart() {
	  this.position.setX(Constants.START_PLAYER_X);
	  this.position.setY(Constants.START_PLAYER_Y);
  }
  
  public int getLives() {
	  return this.lives;
  }
  
  public void lostLife() {
	  if (lives > 0) {
		  System.out.println(lives);
		  this.lives--;
	  } else {
		  // To do: End game method
		  System.out.println("Game is over");
	  }
  }
  
  public boolean isContacting(Sprite other) {
    return this.getBoundingBox().intersects(other.getBoundingBox());
  }
  
  public boolean isContacting(CollideTile tile) {
	  return this.getBoundingBox().intersects(tile.boundingBox);
  }

  public void update(Input input, int delta, ArrayList<SolidTile> solidTiles) {
	  float newX = position.getX();
	  float newY = position.getY();
	  
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
    if (!intersects) {
    	this.position.setX(newX);
    	this.position.setY(newY);
    	this.updateBoundingBox(newX, newY);
    }
    // update with new possible x and y, check if would be on a solid tile, if yes, don't move, reset BoundingBox
  }
}
