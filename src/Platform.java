import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Image;

import helper.Constants;
/**
 * Class for the Platform objects (logs, turtles)
 * @author Kyle
 *
 */
public class Platform extends Enemy {
	
	// Turtles do sink and can be either floating or not at a particular time
	private boolean doesSink;
	private boolean floating;
	// Hold any new life items
	private ArrayList<AttachedItem> attachedItems = new ArrayList<>();
	
	/**
	 * Create a new Platform with an image, position (x, y), speed, direction (movesRight), movement type (bounces), and doesSink flag
	 * @param image
	 * @param x
	 * @param y
	 * @param speed
	 * @param movesRight
	 * @param bounces
	 * @param doesSink
	 */
	Platform(Image image, float x, float y, float speed, boolean movesRight, boolean bounces, boolean doesSink) {
		super(image, x, y, speed, movesRight, bounces);
		this.doesSink = doesSink;
		this.floating = true;
	}
	
	/**
	 * Add an item to the platform. This is called from AttachedItem when the time is right
	 * @param item
	 */
	public void addAttachedItem(AttachedItem item) {
		attachedItems.add(item);
	}
	
	/**
	 * Remove an item after it's despawned or been collected
	 * @param removeItem
	 */
	public void removeAttachedItem(AttachedItem removeItem) {
		Iterator<AttachedItem> iterator = attachedItems.iterator();
		while (iterator.hasNext()) {
			AttachedItem item = iterator.next();
			if (item == removeItem) {
				iterator.remove();
			}
		}
	}

	public void contactPlayer(Player player) {}
	
	/**
	 * Moves the platform and the player, at the same speed as the platform
	 * @param player
	 * @param delta
	 */
	public void contactPlayer(Player player, int delta) {
		// find the distance moved per frame of the update method of enemy, and move the player by the same amount along x
		boolean movePlayer = !((player.position.getX() > Constants.SCREEN_WIDTH - Constants.TILE_SIZE/2) || (player.position.getX() < Constants.TILE_SIZE/2 - Constants.TILE_OFFSET));
		if (this.getMovesRight()) {
			if (movePlayer) {
				player.position.setX(player.position.getX() + (delta * this.getSpeed()));
			}
		} else {
			if (movePlayer) {
				player.position.setX(player.position.getX() - (delta * this.getSpeed()));	
			}
		}
		
	}
	
	/**
	 * 
	 * @return doesSink (boolean)
	 */
	public boolean getDoesSink() {
		return this.doesSink;
	}
	
	/**
	 * 
	 * @return floating (boolean)
	 */
	public boolean getFloating() {
		return this.floating;
	}
	
	/**
	 * Any items on this platform are to have same floating classification as the platform itself 
	 */
	public void setFloatingAttachedItems() {
		for (AttachedItem item : attachedItems) {
			item.setFloating(this.floating);
		}
	}
	
	/**
	 * 
	 * @param floating
	 */
	public void setFloating(boolean floating) {
		this.floating = floating;
	}

	/**
	 * Render the platform if it's floating
	 */
	public void render() {
		if (floating) {
			super.render();
		}
	}

}
