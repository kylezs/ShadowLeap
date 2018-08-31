import helper.Constants;
import org.newdawn.slick.Input;

public class Player extends Sprite {

  Player(String imageSrc, float x, float y) {
    super(imageSrc, x, y);
  }

  public void update(Input input, int delta) {
    if (input.isKeyPressed(Input.KEY_UP)) {
      position.setY(position.getY() - Constants.TILE_SIZE);
    }

    if (input.isKeyPressed(Input.KEY_DOWN)) {
      position.setY(position.getY() + Constants.TILE_SIZE);
    }

    if (input.isKeyPressed(Input.KEY_RIGHT)) {
      float newPos = position.getX() + Constants.TILE_SIZE;
      if (!(newPos >= Constants.SCREEN_WIDTH - 8)) {
        position.setX(newPos);
      }
    }

    if (input.isKeyPressed(Input.KEY_LEFT)) {
      float newPos = position.getX() - Constants.TILE_SIZE;
      if (!(newPos <= 0 - Constants.TILE_SIZE + 8)) {
        position.setX(newPos);
      }
    }
  }
}
