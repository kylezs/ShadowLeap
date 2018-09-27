import org.newdawn.slick.Image;

public class DeathTile extends CollideTile implements Interactable {
	
	DeathTile(Image image, float x, float y) {
		super(image, x, y);
	}
	
	@Override
	public void contactPlayer() {
		System.out.println("Death Tile interaction");
	}
}
