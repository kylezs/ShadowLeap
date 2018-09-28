import org.newdawn.slick.Image;

import helper.Constants;

public class SolidEnemy extends Enemy {

	SolidEnemy(Image image, float x, float y, float speed, boolean movesRight, boolean bounces) {
		super(image, x, y, speed, movesRight, bounces);
	}
	
	/** Push the player until totally off the screen, dead if that happens */
	public void contactPlayer(Player player) {
		if (this.getMovesRight()) {
			player.position.setX(this.position.getX() + player.spriteImage.getWidth());
			if (player.getBoundingBox().getLeft() > Constants.SCREEN_WIDTH) {
				player.dead();
			}
		} else {
			player.position.setX(this.position.getX() - player.spriteImage.getWidth());
			if (player.getBoundingBox().getRight() < 0) {
				player.dead();
			}
		}
		
	}

}
