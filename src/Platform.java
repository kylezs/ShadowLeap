import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Platform extends Enemy {
	

	private boolean doesSink;
	private int sinkTime;
	
	Platform(Image image, float x, float y, float speed, boolean movesRight, boolean bounces, boolean doesSink, int sinkTime) {
		super(image, x, y, speed, movesRight, bounces);
		this.doesSink = doesSink;
		this.sinkTime = sinkTime;
	}
	
	Platform(Image image, float x, float y, float speed, boolean movesRight, boolean bounces, boolean doesSink) {
		super(image, x, y, speed, movesRight, bounces);
		this.doesSink = doesSink;
	}


	public void update(Input input, int delta) {
		// update
		// update the player with the platform.
	}

	
	public void contactPlayer(Player player) {
		// Defined here. Carry the player
	}

}
