package helper;

public class Position {
  private float x;
  private float y;

  public Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float newX) {
      this.x = newX;
  }

  public void setY(float newY) {
      this.y = newY;
  }
}
