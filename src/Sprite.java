import helper.Position;
import org.newdawn.slick.*;
import utilities.BoundingBox;

/**
 * The blueprint for any sprites created, the player can interact with sprites
 * @author Kyle
 *
 */
public class Sprite implements Interactable {

  protected Image spriteImage;
  protected Position position;
  private BoundingBox boundingBox;

  public Sprite(Image image, float x, float y) {
    this.spriteImage = image;
    position = new Position(x, y);
    boundingBox = new BoundingBox(spriteImage, x, y);

  }
  
  /**
   * Initialise a sprite with an source to an image and a position (x, y)
   * @param imageSrc
   * @param x
   * @param y
   */
  public Sprite(String imageSrc, float x, float y) {
    try {
		this.spriteImage = new Image(imageSrc);
	} catch (SlickException e) {
		e.printStackTrace();
	}
    position = new Position(x, y);
    boundingBox = new BoundingBox(spriteImage, x, y);

  }

  public void update(Input input, int delta) {};

  /**
   * Update the x and y coordinates of the bounding box
   * @param x
   * @param y
   */
  public void updateBoundingBox(float x, float y) {
    boundingBox.setX(x);
    boundingBox.setY(y);
  }
  
  /**
   * 
   * @return boundingBox of current sprite
   */
  public BoundingBox getBoundingBox() {
	  return this.boundingBox;
  }

  public void render() {
    // centre the image by moving it left by half its tile size
    this.spriteImage.drawCentered(position.getX(), position.getY());
  }

  /**
   * Overriden later by classes with sprites that die
   */
  public void dead() {};

	@Override
	public void contactPlayer(Player player) {};
}
