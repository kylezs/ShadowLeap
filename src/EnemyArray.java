import java.util.ArrayList;

public class EnemyArray {
  private ArrayList<Enemy> busArray;
  private float startNextAt;

  public void setBusArray(ArrayList<Enemy> busArray) {
    this.busArray = busArray;
  }

  public ArrayList<Enemy> getBusArray() {
    return busArray;
  }

  public void setStartNextAt(float startNextAt) {
    this.startNextAt = startNextAt;
  }

  public float getStartNextAt() {
    return startNextAt;
  }
}