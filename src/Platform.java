import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import helper.Constants;

import java.util.Timer;
import java.util.TimerTask;

public class Platform extends Enemy {
	

	private boolean doesSink;
	private int sinkTime;
	private static float timeElapsed = 0;
	private boolean floating;
	
	Platform(Image image, float x, float y, float speed, boolean movesRight, boolean bounces, boolean doesSink, int sinkTime) {
		super(image, x, y, speed, movesRight, bounces);
		this.doesSink = doesSink;
		this.sinkTime = sinkTime;
		this.floating = true;
	}
	
	Platform(Image image, float x, float y, float speed, boolean movesRight, boolean bounces, boolean doesSink) {
		super(image, x, y, speed, movesRight, bounces);
		this.doesSink = doesSink;
		this.floating = true;
	}


	public void contactPlayer(Player player) {
		// Set the x to the movement of the platform
		player.position.setX(20);
		
	}
	
	
	public void update(Input input, int delta) {
		super.update(input, delta);
		// may need to copy over parent implementation logic
		if (doesSink) {
			// sinkTime delay
			System.out.println("Does Sink");
			timeElapsed += delta;
			if (timeElapsed > Constants.TURTLE_RESURFACE_DELAY + Constants.TURTLE_SINK_DELAY) {
				// Resurface the turtle
				floating = true;
				timeElapsed = 0;
			} else if (timeElapsed > Constants.TURTLE_SINK_DELAY) {
				// sink the turtle
				floating = false;
			}
			System.out.println("Time elapsed: " + timeElapsed);
		}
		// update the player with the movement (both turtles and logs behave the same here)
	}

	public void render() {
		if (floating) {
			super.render();
		}
	}

}
