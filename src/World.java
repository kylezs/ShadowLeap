
import com.sun.tools.internal.jxc.ap.Const;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import helper.Constants;

import java.util.ArrayList;


public class World {

  private Player player;
  private static final float START_PLAYER_X = Constants.SCREEN_WIDTH/2 - Constants.TILE_SIZE/2;
  private static final float START_PLAYER_Y = 720;

  private Image grassTile;
  private Image waterTile;

  private static final int START_WATER = 336;
  private static final int END_WATER = 48;

  private static final float START_GRASS = 672;
  private static final float END_GRASS = 384;

  private ArrayList<EnemyArray> allBusRows = new ArrayList<>();


	public World() throws SlickException {
		// set the background tiles
    grassTile = new Image("assets/grass.png");
    waterTile = new Image("assets/water.png");

    player = new Player("assets/frog.png", START_PLAYER_X, START_PLAYER_Y);

    // init 5 rows of buses, 432, 480, 528, 576, 624
    EnemyArray busArray = busRow(432, 48, Constants.LEFT, (float) 6.5 * Constants.TILE_SIZE);
    allBusRows.add(busArray);

    busArray = busRow(480, 0, Constants.RIGHT, (float) 5 * Constants.TILE_SIZE);
    allBusRows.add(busArray);
    busArray = busRow(528, 64, Constants.LEFT, (float) 12 * Constants.TILE_SIZE);
    allBusRows.add(busArray);
    busArray = busRow(576, 128, Constants.RIGHT, (float) 5 * Constants.TILE_SIZE);
    allBusRows.add(busArray);
    busArray = busRow(624, 250, Constants.LEFT, (float) 6.5 * Constants.TILE_SIZE);
    allBusRows.add(busArray);

	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
    player.update(input, delta);
    for (EnemyArray busArray : allBusRows) {
      for (Enemy bus : busArray.getBusArray()) {
        bus.update(input, delta, busArray.getStartNextAt());
        bus.contactSprite(player);
      }
    }
	}
	
	public void render(Graphics g) {
    // Draw all of the sprites in the game

    // draw the water
    for (int y_coord = START_WATER; y_coord >= END_WATER; y_coord -= Constants.TILE_SIZE) {
      drawRow(waterTile, y_coord);
    }

    // draw the grass
    drawRow(grassTile, START_GRASS);
    drawRow(grassTile, END_GRASS);

    // render the buses
    for (EnemyArray busArray : allBusRows) {
      for (Enemy bus : busArray.getBusArray()) {
        bus.render();
      }
    }
    player.render();
  }

  private static void drawRow(Image tile, float y_coord) {
    for (int j = 0; j <= (Constants.SCREEN_WIDTH / Constants.TILE_SIZE); j++)
      tile.draw(Constants.TILE_SIZE * j, y_coord);
	}

	private EnemyArray busRow(float y, float offset, String dir, float separationDist) {
	  EnemyArray newEnemyArray = new EnemyArray();
	  ArrayList<Enemy>busArray = new ArrayList<>();
	  int i = 0;
    while (i * separationDist < Constants.SCREEN_WIDTH + separationDist) {
      Enemy a_bus = new Enemy("assets/bus.png", offset + i * separationDist, y, (float)0.15, dir, separationDist);
      busArray.add(a_bus);
      i++;
    }
    float startNextAt = Constants.SCREEN_WIDTH - (separationDist * (i-1));

    System.out.println("sepdist: " + separationDist + " StartAtNext: " + startNextAt);
    System.out.println(i);
    newEnemyArray.setBusArray(busArray);
    newEnemyArray.setStartNextAt(startNextAt);
    return newEnemyArray;
  }

}
