import org.newdawn.slick.Image;

import helper.Position;

/**
 * Class for a generic tile. Player can jump on this tile without an action occurring
 * @author Kyle
 *
 */
public class Tile {
	private Image image;
	private Position position;
	
	/**
	 * Initialise a Tile with an image and position (x, y)
	 * @param image
	 * @param x
	 * @param y
	 */
	Tile(Image image, float x, float y) {
		this.image = image;
		position = new Position(x, y);
	}
	
	/**
	 * Draw the tile from the center of it's position
	 */
	public void render() {
		image.drawCentered(position.getX(), position.getY());
	}
}
