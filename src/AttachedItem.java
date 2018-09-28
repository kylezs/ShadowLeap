import org.newdawn.slick.Image;

public class AttachedItem extends Sprite {

	public AttachedItem(Image image, float x, float y) {
		super(image, x, y);
	}
	
	public void contactPlayer(Player player) {
		System.out.println("Attached item in contact with player");
	}
}
