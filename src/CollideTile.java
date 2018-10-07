import org.newdawn.slick.Image;
import utilities.BoundingBox;

/**
 * The class for a tile that has some action occur when in contact with a player
 * @author Kyle
 *
 */
public abstract class CollideTile extends Tile implements Interactable {

	BoundingBox boundingBox;
	
	/**
	 * Initialize a collide tile at location (x, y)
	 * @param image
	 * @param x
	 * @param y
	 */
	CollideTile(Image image, float x, float y) {
		super(image, x, y);
		boundingBox = new BoundingBox(image, x, y);
	}
	
	/**
	 * Controls what happens if the tile is in contact with the player
	 */
	abstract public void contactPlayer(Player player);
}
