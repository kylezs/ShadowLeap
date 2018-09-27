import org.newdawn.slick.Image;
import utilities.BoundingBox;

public abstract class CollideTile extends Tile implements Interactable {
	
	BoundingBox bb;
	
	CollideTile(Image image, float x, float y) {
		super(image, x, y);
		bb = new BoundingBox(image, x, y);
	}
	
	abstract public void contactPlayer();
}
