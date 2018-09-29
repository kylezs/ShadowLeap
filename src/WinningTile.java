import utilities.BoundingBox;

public class WinningTile extends BoundingBox implements Interactable {

	public WinningTile(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void contactPlayer(Player player) {
		player.filledHole();
		player.backToStart();
	}
	
	public float getCentreX() {
		return this.getLeft() + (this.getWidth() / 2);
	}
	
	public float getCentreY() {
		return this.getTop() + (this.getHeight() / 2);
	}
	
}
