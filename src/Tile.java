import org.newdawn.slick.Image;

import helper.Position;

public class Tile {
	private Image image;
	private Position position;
	
	
	// TODO: Takes in the image, and only once is the image used.
	// the rest are duplicate images, but of a different tile object
	Tile(Image image, float x, float y) {
		this.image = image;
		position = new Position(x, y);
	}
	
	public void render() {
		image.drawCentered(position.getX(), position.getY());
	}
	
	public String toString() {
		return image.toString();
	}
}
