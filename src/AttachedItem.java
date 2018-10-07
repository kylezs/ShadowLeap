import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import helper.Constants;

/**
 * Class for AttachedItems, these items appear on logs or turtles
 * This is always a newlife object as it stands
 * @author Kyle
 *
 */
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
	
	/**
	 * 
	 * @return whether to render the object
	 */
	public boolean getToRender() {
		return toRender;
	}
	
	/**
	 * set the object's floating attribute to param 'floating'
	 * @param floating
	 */
	public void setFloating(boolean floating) {
		AttachedItem.floating = floating;
	}

	/**
	 * Initialize an attached item
	 * @param image
	 * @param x
	 * @param y
	 */
	public AttachedItem(Image image, float x, float y) {
		super(image, x, y);
		toRender = false;
		randomTime = getRandomTime();
	}
	
	/**
	 * Controls what happens if the item is in contact with the player
	 * Adds a life to the player, removes new life from screen (toRender = false, hasRendered = false)
	 * Resets the spawn timer, sets collected to true, so as not to call it again
	 */
	public void contactPlayer(Player player) {
		player.addLife();
		toRender = false;
		timeElapsed = 0;
		collected = true;
		hasRendered = false;
	}
	
	/**
	 * 
	 * @return whether or not the AttachedItem has been collected
	 */
	public boolean isCollected() {
		return collected;
	}
	
	/**
	 * Spawns/despawns a newlife if the time is right, controls movement across the selected platform (up and back)
	 * @param input
	 * @param delta
	 * @param platforms
	 */
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
			deltaPos = 0;
			timeElapsed = 0;
			thePlatform.removeAttachedItem(this);
		}
		if (toSpawn) {
			// If something's been despawned, need to select the platform again
			thePlatform = platforms.get(new Random().nextInt(platforms.size()));
			thePlatform.addAttachedItem(this);
			floating = thePlatform.getFloating();
			toSpawn = false;
		}
		// Object's first render
		if (toRender && !hasRendered) {
			float x = thePlatform.position.getX();
			float y = thePlatform.position.getY();
			this.position.setX(x);
			this.position.setY(y);
			this.updateBoundingBox(x, y);
			hasRendered = true;
		// Object has already been rendered this controls the updating render(s)
		} else if (hasRendered) {
			float x = thePlatform.position.getX();
			float y = thePlatform.position.getY();
			
			if (timeStepElapsed > Constants.TIME_STEP) {
				//float oldDeltaPos = movesRight ? deltaPos - Constants.TILE_SIZE : deltaPos + Constants.TILE_SIZE;
				deltaPos += movesRight ? Constants.TILE_SIZE : -Constants.TILE_SIZE;
				// if cannot move right (bounding boxes do not intersect, switch to moving left
				this.updateBoundingBox(x + deltaPos, y);
				if (this.getBoundingBox().intersects(thePlatform.getBoundingBox())) {
					this.position.setX(x + deltaPos);
					// reset timestep
					timeStepElapsed = 0;
				} else {
					movesRight = !movesRight;
					// reset bounding box
					this.updateBoundingBox(this.position.getX(), this.position.getY());
				}
			} else {
				// keep the delta from before, but need to update because platform moved
				x = thePlatform.position.getX() + deltaPos;
				y = thePlatform.position.getY();
				this.updateBoundingBox(x, y);
				this.position.setX(x);
				this.position.setY(y);
			}
		}
		
	}
	
	/**
	 * Draw the new life on the screen only if the platform it's on is floating and it's meant to be rendered
	 */
	public void render() {
		if (toRender && floating) {
			super.render();
		}
	}
	
	/**
	 * 
	 * @return a random time (in ms) between in range [MIN_TIME_LIFE_SPAWN, MIN_TIME_LIFE_SPAWN + RANGE_LIFE_SPAWN]
	 */
	private int getRandomTime() {
		return new Random().nextInt(Constants.RANGE_LIFE_SPAWN) + Constants.MIN_TIME_LIFE_SPAWN;
	}
}
