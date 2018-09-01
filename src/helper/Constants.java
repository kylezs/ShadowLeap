package helper;

public class Constants {
  /** directions */
  public static final String RIGHT = "right";
  public static final String LEFT = "left";

  /** screen width, in pixels */
  public static final int SCREEN_WIDTH = 1024;
  /** screen height, in pixels */
  public static final int SCREEN_HEIGHT = 768;
  /** tile size + length/height of each jump */
  public static final float TILE_SIZE = 48;

  public static final float START_PLAYER_X = SCREEN_WIDTH/2 - TILE_SIZE/2;
  public static final float START_PLAYER_Y = 720;

  public static final int START_WATER = 336;
  public static final int END_WATER = 48;

  public static final float START_GRASS = 672;
  public static final float END_GRASS = 384;
}