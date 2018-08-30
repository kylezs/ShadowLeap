import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class World {
  private Image grassTile;
  private Image waterTile;

  private static final int TILE_SIZE = 48;
  private static final int START_WATER = 336;
  private static final int END_WATER = 48;

  private static final float START_GRASS = 672;
  private static final float END_GRASS = 384;

	public World() throws SlickException {
		// set the background tiles
    grassTile = new Image("assets/grass.png");
    waterTile = new Image("assets/water.png");
	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
	}
	
	public void render(Graphics g) {
    // Draw all of the sprites in the game

    // draw the water
    for (int y_coord = START_WATER; y_coord >= END_WATER; y_coord -= TILE_SIZE) {
      drawRow(waterTile, y_coord);
    }

    // draw the grass
    drawRow(grassTile, START_GRASS);
    drawRow(grassTile, END_GRASS);

  }

  private static void drawEnemyRow(Image spriteImage, float speed, String dir, float theRow, float spread) {
    ;
  }

  private static void drawRow(Image tile, float y_coord) {
    for (int j = 0; j <= (App.SCREEN_WIDTH / TILE_SIZE); j++)
      tile.draw(TILE_SIZE * j, y_coord);
    }
}
