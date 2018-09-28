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
  
  public void contactPlayer(Player player) {
	  player.dead();
  }
  
  	// Sourced (then extended) from sample solution to Project 1
	private final float getInitialX() {
		return movesRight ? -this.getBoundingBox().getWidth() / 2
						 : Constants.SCREEN_WIDTH + this.getBoundingBox().getWidth() / 2;
	}
	
	public boolean getMovesRight() {
		return movesRight;
	}
	

  public void update(Input input, int delta) {
    if (this.movesRight) {
      float newPos = this.position.getX() + delta * this.speed;
      this.position.setX(newPos);
    }  else {
    	float newPos = this.position.getX() - delta * this.speed;
    	this.position.setX(newPos);
    }
    
    updateBoundingBox(position.getX(), position.getY());
    if (!bounces) {
        // Sourced from Sample solution to Project 1
    	if (this.getBoundingBox().getLeft() > Constants.SCREEN_WIDTH || this.getBoundingBox().getRight() < 0
    			 || this.position.getY() > Constants.SCREEN_HEIGHT + Constants.TILE_SIZE / 2 || this.position.getY() < -Constants.TILE_SIZE / 2) {
    				this.position.setX(getInitialX());
    			}
    } else {
    	// Do NOT simplify as !this.movesRight. It can glitch and get stuck switching on and off.
    	if (position.getX() > 1000) {
    		this.movesRight = false;
    	}
    	else if (position.getX() < 24) {
    		this.movesRight = true;
    	}
    }
    
  }
}
