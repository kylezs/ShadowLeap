import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import helper.Constants;

public class AttachedItem extends Sprite {
	
	private boolean toRender;
	private static boolean floating = true;
	// keeping track of initialisation and already initialised
	private boolean hasRendered;
	private static float deltaPos;
	private boolean movesRight = true;
	private static int timeElapsed = 0;
	private static int timeStepElapsed = 0;
	private static int randomTime;
	private static Platform thePlatform;
	private static boolean collected = false;
	
	public boolean getToRender() {
		return toRender;
	}
	
	public void setFloating(boolean floating) {
		AttachedItem.floating = floating;
	}

	public AttachedItem(Image image, float x, float y) {
		super(image, x, y);
		toRender = false;
		randomTime = getRandomTime();
	}
	
	public void contactPlayer(Player player) {
		player.addLife();
		toRender = false;
		timeElapsed = 0;
		collected = true;
		hasRendered = false;
	}
	
	public boolean isCollected() {
		return collected;
	}
	
	public void update(Input input, int delta, ArrayList<Platform> platforms) {
		boolean toSpawn = false;
		// timing of spawn
		timeElapsed += delta;
		timeStepElapsed += delta;
		if (timeElapsed > randomTime && !toRender) {
			// location of spawn
			toSpawn = true;
			toRender = true;
			collected = false;
		} else if (timeElapsed > randomTime + Constants.DESPAWN_LIFE) {
			toSpawn = false;
			toRender = false;
			hasRendered = false;
			timeElapsed = 0;
			thePlatform.removeAttachedItem(this);
		}
		
		if (toSpawn) {
			thePlatform = platforms.get(new Random().nextInt(platforms.size()));
			toSpawn = false;
			thePlatform.addAttachedItem(this);
		}
		if (toRender && !hasRendered) {
			float x = thePlatform.position.getX();
			float y = thePlatform.position.getY();
			this.position.setX(x);
			this.position.setY(y);
			this.updateBoundingBox(x, y);
			hasRendered = true;
			
		} else if (hasRendered) {
			//= thePlatform.getBoundingBox().intersects(this.getBoundingBox());
			float x = thePlatform.position.getX();
			float y = thePlatform.position.getY();
			
			if (timeStepElapsed > Constants.TIME_STEP) {
				//float oldDeltaPos = movesRight ? deltaPos - Constants.TILE_SIZE : deltaPos + Constants.TILE_SIZE;
				deltaPos += movesRight ? Constants.TILE_SIZE : -Constants.TILE_SIZE;
				// if cannot move right (bounding boxes do not intersect, switch to moving left
				this.updateBoundingBox(x + deltaPos, y);
				if (this.getBoundingBox().intersects(thePlatform.getBoundingBox())) {
					this.position.setX(x + deltaPos);
					timeStepElapsed = 0;
				} else {
					movesRight = !movesRight;
					// reset bounding box
					this.updateBoundingBox(this.position.getX(), this.position.getY());
				}
			} else {
				// keep the delta from before
				x = thePlatform.position.getX() + deltaPos;
				y = thePlatform.position.getY();
				this.updateBoundingBox(x, y);
				this.position.setX(x);
				this.position.setY(y);
			}
		}
		
	}
	
	public void render() {
		if (toRender && floating) {
			super.render();
		}
	}
	
	private int getRandomTime() {
		return new Random().nextInt(Constants.RANGE_LIFE_SPAWN) + Constants.MIN_TIME_LIFE_SPAWN;
	}
}
