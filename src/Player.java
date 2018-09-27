import helper.Constants;
import org.newdawn.slick.Input;

public class Player extends Sprite {

  private static final float TILE_OFFSET = 8;

  Player(String imageSrc, float x, float y) {
    super(imageSrc, x, y);
  }

  // A comment in the player class
  public void update(Input input, int delta) {

    // move the player one tile in whatever direction key is pressed
    if (input.isKeyPressed(Input.KEY_UP)) {
      float newPos = position.getY() - Constants.TILE_SIZE;
      if (!(newPos <= 0 - Constants.TILE_SIZE)) {
        position.setY(newPos);
      }
    }

    if (input.isKeyPressed(Input.KEY_DOWN)) {
      float newPos = position.getY() + Constants.TILE_SIZE;
      if (!(newPos >= Constants.SCREEN_HEIGHT)) {
        position.setY(newPos);
      }
    }

    if (input.isKeyPressed(Input.KEY_RIGHT)) {
      float newPos = position.getX() + Constants.TILE_SIZE;
      if (!(newPos >= Constants.SCREEN_WIDTH - TILE_OFFSET)) {
        position.setX(newPos);
      }
    }

    if (input.isKeyPressed(Input.KEY_LEFT)) {
      float newPos = position.getX() - Constants.TILE_SIZE;
      if (!(newPos <= 0 - Constants.TILE_SIZE + TILE_OFFSET)) {
        position.setX(newPos);
      }
    }
//    // if the player enters the water
//    if (position.getY() <= Constants.START_WATER && position.getY() >= Constants.END_WATER) {
//      dead();
//    }
    updateBoundingBox(position.getX(), position.getY());
  }
}
