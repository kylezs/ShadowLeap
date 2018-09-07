import org.newdawn.slick.Input;
import helper.Constants;

public class Enemy extends Sprite {

  private float speed;
  private String direction;
  private float separationDistance;

  Enemy(String imageSrc, float x, float y, float speed, String dir, float separationDistance) {
    super(imageSrc, x, y);
    this.speed = speed;
    this.direction = dir;
    this.separationDistance = separationDistance;
  }

  public void update(Input input, int delta, float startNextAt) {
    if (this.direction.equals(Constants.RIGHT)) {
      float newPos = this.position.getX() + delta * this.speed;
      if (newPos > Constants.SCREEN_WIDTH - startNextAt + separationDistance - Constants.TILE_SIZE) {
        this.position.setX(-Constants.TILE_SIZE);
      } else {
        this.position.setX(newPos);
      }
    } else if (this.direction.equals(Constants.LEFT)) {
      float newPos = this.position.getX() - delta * this.speed;
      if (newPos < startNextAt) {
        this.position.setX(Constants.SCREEN_WIDTH);
      } else {
        this.position.setX(newPos);
      }
    }
    updateBoundingBox(position.getX(), position.getY());
  }
}
