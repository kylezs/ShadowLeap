import org.newdawn.slick.Image;

public class SolidEnemy extends Enemy {

	SolidEnemy(Image image, float x, float y, float speed, boolean movesRight, boolean bounces) {
		super(image, x, y, speed, movesRight, bounces);
	}
	
	public void contactPlayer(Player player) {
		// move with the player
	}

}
