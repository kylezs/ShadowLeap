import java.util.ArrayList;

import org.newdawn.slick.Image;

public class Platform extends Enemy {
	
	private boolean doesSink;
	private boolean floating;
	private ArrayList<AttachedItem> attachedItems = new ArrayList<>();
	
	Platform(Image image, float x, float y, float speed, boolean movesRight, boolean bounces, boolean doesSink) {
		super(image, x, y, speed, movesRight, bounces);
		this.doesSink = doesSink;
		this.floating = true;
	}
	
	public void addAttachedItem(AttachedItem item) {
		attachedItems.add(item);
	}

	public void contactPlayer(Player player) {}
	
	public void contactPlayer(Player player, int delta) {
		// find the distance moved per frame of the update method of enemy, and move the player by the same amount along x
		if (this.getMovesRight()) {
			player.position.setX(player.position.getX() + (delta * this.getSpeed()));
		} else {
			player.position.setX(player.position.getX() - (delta * this.getSpeed()));
		}
		
	}
	
	public boolean getDoesSink() {
		return this.doesSink;
	}
	
	public boolean getFloating() {
		return this.floating;
	}
	
	public void setFloatingAttachedItems() {
		System.out.println("Attached items setting floating to: " + this.floating);
		for (AttachedItem item : attachedItems) {
			item.setFloating(this.floating);
		}
	}
	
	public void setFloating(boolean floating) {
		this.floating = floating;
	}

	public void render() {
		if (floating) {
			super.render();
		}
	}

}
