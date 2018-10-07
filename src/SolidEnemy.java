import org.newdawn.slick.Image;

/**
 * Class for enemies that do not kill the player, but push him,
 * These enemies are also not possible to be jumped into
 * @author Kyle
 *
 */
public class SolidEnemy extends Enemy {

	SolidEnemy(Image image, float x, float y, float speed, boolean movesRight, boolean bounces) {
		super(image, x, y, speed, movesRight, bounces);
	}
	
	/** 
	 * Push the player in if in contact
	 */
	public void contactPlayer(Player player) {
		if (this.getMovesRight()) {
			player.position.setX(this.position.getX() + player.spriteImage.getWidth());
		} else {
			player.position.setX(this.position.getX() - player.spriteImage.getWidth());
		}
		
	}

}
