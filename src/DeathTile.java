import org.newdawn.slick.Image;

/**
 * The class for the tile type that kills the player when touched
 * @author Kyle
 *
 */
public class DeathTile extends CollideTile implements Interactable {
	
	/**
	 * Initialize a death tile at location (x,y)
	 * @param image
	 * @param x
	 * @param y
	 */
	DeathTile(Image image, float x, float y) {
		super(image, x, y);
	}
	
	/**
	 * Kills player if the player comes in contact
	 */
	@Override
	public void contactPlayer(Player player) {
		player.dead();
	}
}
