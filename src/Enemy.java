import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import helper.Constants;

public class Enemy extends Sprite {

  private float speed;
  private boolean movesRight;
  // does it come back rather than loop
  private boolean bounces;

  Enemy(Image image, float x, float y, float speed, boolean movesRight, boolean bounces) {
    super(image, x, y);
    this.speed = speed;
    this.movesRight = movesRight;
    this.bounces = bounces;
  }
  
  	// Sourced from sample solution to Project 1
	private final float getInitialX() {
		return movesRight ? -Constants.TILE_SIZE / 2
						 : Constants.SCREEN_WIDTH + Constants.TILE_SIZE / 2;
	}

  public void update(Input input, int delta) {
    if (this.movesRight) {
      float newPos = this.position.getX() + delta * this.speed;
      this.position.setX(newPos);
    }  else {
    	float newPos = this.position.getX() - delta * this.speed;
    	this.position.setX(newPos);
    }
    
    if (!bounces) {
        // Sourced from Sample solution to Project 1
    	if (this.position.getX() > Constants.SCREEN_WIDTH + Constants.TILE_SIZE / 2 || this.position.getX() < -Constants.TILE_SIZE / 2
    			 || this.position.getY() > Constants.SCREEN_HEIGHT + Constants.TILE_SIZE / 2 || this.position.getY() < -Constants.TILE_SIZE / 2) {
    				this.position.setX(getInitialX());
    			}
    } else {
    	if (position.getX() > 1000 || position.getX() < 24) {
    		this.movesRight = !movesRight;
    	}
    }

    updateBoundingBox(position.getX(), position.getY());
  }
}
