import org.newdawn.slick.Image;

public class SolidTile extends CollideTile {

	SolidTile(Image image, float x, float y) {
		super(image, x, y);
	}

	@Override
	public void contactPlayer() {
		System.out.println("Solid Tile interaction");
	}

}
