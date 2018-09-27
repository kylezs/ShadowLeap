import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import helper.Constants;

import java.util.ArrayList;


public class World {

  private Player player;


  private Image grassTile;
  private Image waterTile;
  private ArrayList<EnemyArray> allBusRows = new ArrayList<>();


	public World() throws SlickException {
	// set the background tiles
    grassTile = new Image("assets/grass.png");
    waterTile = new Image("assets/water.png");

    player = new Player("assets/frog.png", Constants.START_PLAYER_X, Constants.START_PLAYER_Y);

    // init 5 rows of buses, 432, 480, 528, 576, 624
    // TODO: Will be generated dynamically by level info
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
    for (int y_coord = Constants.START_WATER; y_coord >= Constants.END_WATER; y_coord -= Constants.TILE_SIZE) {
      drawRow(waterTile, y_coord);
    }

    // draw the grass
    drawRow(grassTile, Constants.START_GRASS);
    drawRow(grassTile, Constants.END_GRASS);

    // render the buses
    for (EnemyArray busArray : allBusRows) {
      for (Enemy bus : busArray.getBusArray()) {
        bus.render();
      }
    }
    player.render();
  }
	
	public static void readLevel(int levelNum) {
		String lvlSrc = "assets/" + levelNum + ".csv";
	}
	

	
	

  // Create tiles for however many tiles that fit on the screen
  private static void drawRow(Image tile, float y_coord) {
    for (int j = 0; j <= (Constants.SCREEN_WIDTH / Constants.TILE_SIZE); j++)
      tile.draw(Constants.TILE_SIZE * j, y_coord);
	}

	private EnemyArray busRow(float y, float offset, String dir, float separationDist) {
	  EnemyArray newEnemyArray = new EnemyArray();
	  ArrayList<Enemy>busArray = new ArrayList<>();

	  // create however many buses required per row
	  int i = 0;
    while (i * separationDist < Constants.SCREEN_WIDTH + separationDist) {
      Enemy a_bus = new Enemy("assets/bus.png", offset + i * separationDist, y, Constants.BUS_SPEED, dir, separationDist);
      busArray.add(a_bus);
      i++;
    }
    // store a startNextAt which acts as an offset value when uniformly separating buses
    float startNextAt = Constants.SCREEN_WIDTH - (separationDist * (i-1));
    newEnemyArray.setBusArray(busArray);
    newEnemyArray.setStartNextAt(startNextAt);
    return newEnemyArray;
  }

}
