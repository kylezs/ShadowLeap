import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DeathTile extends CollideTile implements Interactable {
	
	DeathTile(Image image, float x, float y) {
		super(image, x, y);
	}
	
	@Override
	public void contactPlayer(Player player) {
		player.lostLife();
		System.out.println("Player contacted: Now has lives: " + player.getLives());
		player.backToStart();
		
	}
}
