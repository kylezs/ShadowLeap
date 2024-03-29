import org.newdawn.slick.Image;

/**
 * Class for tiles that cannot be jumped on
 * @author Kyle
 *
 */
public class SolidTile extends CollideTile {

	SolidTile(Image image, float x, float y) {
		super(image, x, y);
	}

	@Override
	public void contactPlayer(Player player) {
		// Do nothing. Maybe one day, solid tiles will do something else...
    // Like make a sound on contact... one day.
	}

}
