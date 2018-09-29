import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import helper.Constants;

public class AttachedItem extends Sprite {
	
	private boolean toRender;
	private static int timeElapsed = 0;
	private static int randomTime;
	private static Platform thePlatform;
	
	public boolean getToRender() {
		return toRender;
		
	}

	public AttachedItem(Image image, float x, float y) {
		super(image, x, y);
		toRender = false;
		randomTime = getRandomTime();
	}
	
	public void contactPlayer(Player player) {
		System.out.println("Attached item in contact with player");
		player.addLife();
		toRender = false;
		timeElapsed = 0;
	}
	
	public void update(Input input, int delta, ArrayList<Platform> platforms) {
		boolean toSpawn = false;
		// timing of spawn
		timeElapsed += delta;
		if (timeElapsed > 3000 && !toRender) {
			// location of spawn
			toSpawn = true;
			toRender = true;
		} else if (timeElapsed > randomTime + Constants.DESPAWN_LIFE) {
			toSpawn = false;
			toRender = false;
			timeElapsed = 0;
		}
		
		if (toSpawn) {
			thePlatform = platforms.get(new Random().nextInt(platforms.size()));
			toSpawn = false;
		} if (toRender) {
			float x = thePlatform.position.getX();
			float y = thePlatform.position.getY();
			this.position.setX(x);
			this.position.setY(y);
			this.updateBoundingBox(x, y);
		}
		
	}
	
	public void render() {
		if (toRender) {
			super.render();
		}
	}
	
	private int getRandomTime() {
		return new Random().nextInt(Constants.RANGE_LIFE_SPAWN) + Constants.MIN_TIME_LIFE_SPAWN;
	}
}
