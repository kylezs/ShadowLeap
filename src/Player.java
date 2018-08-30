import org.newdawn.slick.Input;

public class Player extends Sprite {

  Player(String imageSrc, float x, float y) {
    super(imageSrc, x, y);
  }

  public void update(Input input, int delta) {

    if (input.isKeyPressed(Input.KEY_UP)) {
      position.setY(position.getY() - App.TILE_SIZE);
    }

    if (input.isKeyPressed(Input.KEY_DOWN)) {
      position.setY(position.getY() + App.TILE_SIZE);
    }

    if (input.isKeyPressed(Input.KEY_RIGHT)) {
      position.setX(position.getX() + App.TILE_SIZE);
    }

    if (input.isKeyPressed(Input.KEY_LEFT)) {
      position.setX(position.getX() - App.TILE_SIZE);
    }
  }
}
