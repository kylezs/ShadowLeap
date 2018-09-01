import helper.Constants;
import helper.Position;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Sprite {

  protected Image spriteImage;
  protected Position position;
  private BoundingBox boundingBox;

  public Sprite(String imageSrc, float x, float y) {
    // Why would the constructor need a path to an image, and a coordinate?
    try {
      this.spriteImage = new Image(imageSrc);
    } catch (SlickException e) {
      System.out.println("[Error] The image could not be loaded from: " + imageSrc);
    }

    position = new Position(x, y);
    boundingBox = new BoundingBox(spriteImage, x, y);
  }

  public void update(Input input, int delta) {
    // How can this one method deal with different types of sprites?
    // Answer: overriding
  }

  public void render() {
    // centre the image by moving it left by half its tile size
    this.spriteImage.draw(position.getX(), position.getY());
  }

  public void contactSprite(Sprite other) {
    if (this.boundingBox.intersects(other.boundingBox)) {
      System.out.println("Dead");// Should be called when one sprite makes contact with another.
    }
  }
}
