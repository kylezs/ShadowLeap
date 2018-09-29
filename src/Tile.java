import org.newdawn.slick.Image;

import helper.Position;

public class Tile {
	private Image image;
	private Position position;
	
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
