import org.newdawn.slick.Image;
import utilities.BoundingBox;

public abstract class CollideTile extends Tile implements Interactable {
	
	BoundingBox boundingBox;
	
	CollideTile(Image image, float x, float y) {
		super(image, x, y);
		boundingBox = new BoundingBox(image, x, y);
	}
	
	abstract public void contactPlayer(Player player);
}
