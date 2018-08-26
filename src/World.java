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

    // render the water
    for (int y_coord = START_WATER; y_coord >= END_WATER; y_coord -= TILE_SIZE) {
      draw_row(waterTile, y_coord);
    }

    // render the grass
    draw_row(grassTile, 672);
    draw_row(grassTile, 384);
  }

  private static void draw_row(Image tile, int y_coord) {
    for (int j = 0; j <= (App.SCREEN_WIDTH / TILE_SIZE); j++)
      tile.draw(TILE_SIZE * j, y_coord);
    }
}
