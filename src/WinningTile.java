import utilities.BoundingBox;

/**
 * Class for a winning tile (hole where the player must get)
 * @author Kyle
 *
 */
public class WinningTile extends BoundingBox implements Interactable {

	/**
	 * Is just a bounding box but one that can interact with a player
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public WinningTile(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	/**
	 * If player contacts the tile, fill the hole and send player back to start
	 * @param player
	 */
	public void contactPlayer(Player player) {
		player.filledHole();
		player.backToStart();
	}
	
	/**
	 * 
	 * @return the x-centre of the tile
	 */
	public float getCentreX() {
		return this.getLeft() + (this.getWidth() / 2);
	}
	
	/**
	 * 
	 * @return the y-centre of the tile
	 */
	public float getCentreY() {
		return this.getTop() + (this.getHeight() / 2);
	}
	
}
