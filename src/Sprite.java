import helper.Position;
import org.newdawn.slick.*;
import utilities.BoundingBox;

public abstract class Sprite implements Interactable {

  protected Image spriteImage;
  protected Position position;
  private BoundingBox boundingBox;

  public Sprite(Image image, float x, float y) {
    this.spriteImage = image;
    position = new Position(x, y);
    boundingBox = new BoundingBox(spriteImage, x, y);

  }
  
  public Sprite(String imageSrc, float x, float y) {
    try {
		this.spriteImage = new Image(imageSrc);
	} catch (SlickException e) {
		e.printStackTrace();
	}
    position = new Position(x, y);
    boundingBox = new BoundingBox(spriteImage, x, y);

  }

  public void update(Input input, int delta) {
    // How can this one method deal with different types of sprites?
    // Answer: overriding
  }

  public void updateBoundingBox(float x, float y) {
    boundingBox.setX(x);
    boundingBox.setY(y);
  }
  
  public BoundingBox getBoundingBox() {
	  return this.boundingBox;
  }

  public void render() {
    // centre the image by moving it left by half its tile size
    this.spriteImage.drawCentered(position.getX(), position.getY());
  }

  public void dead() {
    try {
      App.getApp().reinit();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }
}
