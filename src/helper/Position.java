package helper;

/**
 * Class to hold x and y position of an object
 * @author Kyle
 *
 */
public class Position {
  private float x;
  private float y;

  /**
   * Initialise a position
   * @param x
   * @param y
   */
  public Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * 
   * @return x
   */
  public float getX() {
    return x;
  }

  /**
   * 
   * @return y
   */
  public float getY() {
    return y;
  }

  /**
   * 
   * @param newX
   */
  public void setX(float newX) {
      this.x = newX;
  }

  /**
   * 
   * @param newY
   */
  public void setY(float newY) {
      this.y = newY;
  }
}
