import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite {

  private Image spriteImage;
  private float x, y;

  public Sprite(String imageSrc, float x, float y) {
    // Why would the constructor need a path to an image, and a coordinate?
    try {
      this.spriteImage = new Image(imageSrc);
    } catch (SlickException e) {
      System.out.println("[Error] The image could not be loaded from: " + imageSrc);
    }

    this.x = x;
    this.y = y;
  }

  public void update(Input input, int delta) {
    // How can this one method deal with different types of sprites?
  }

  public void render() {
    // centre the image by moving it left by half its tile size
    this.spriteImage.draw(this.x - (App.TILE_SIZE/2), this.y);
  }

  public void contactSprite(Sprite other) {
    // Should be called when one sprite makes contact with another.
  }
}
