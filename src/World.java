import org.newdawn.slick.Graphics;
import java.util.Scanner;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import helper.Constants;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;


public class World {
	
	private static Image grassImage;
	private static Image waterImage;
	private static Image treeImage;

	private Player player;
	private static ArrayList<Interactable> interactables;
	private ArrayList<EnemyArray> allBusRows = new ArrayList<>();


	public World() throws SlickException {
		
		interactables = new ArrayList<>();

		player = new Player(Constants.PLAYERSRC, Constants.START_PLAYER_X, Constants.START_PLAYER_Y);
		
		grassImage = new Image(Constants.GRASSTILESRC);
		waterImage = new Image(Constants.WATERTILESRC);
		treeImage = new Image(Constants.TREETILESRC);

		// init 5 rows of buses, 432, 480, 528, 576, 624
		// TODO: Will be generated dynamically by level info
		//    EnemyArray busArray = busRow(432, 48, Constants.LEFT, (float) 6.5 * Constants.TILE_SIZE);
		//    allBusRows.add(busArray);
		//    busArray = busRow(480, 0, Constants.RIGHT, (float) 5 * Constants.TILE_SIZE);
		//    allBusRows.add(busArray);
		//    busArray = busRow(528, 64, Constants.LEFT, (float) 12 * Constants.TILE_SIZE);
		//    allBusRows.add(busArray);
		//    busArray = busRow(576, 128, Constants.RIGHT, (float) 5 * Constants.TILE_SIZE);
		//    allBusRows.add(busArray);
		//    busArray = busRow(624, 250, Constants.LEFT, (float) 6.5 * Constants.TILE_SIZE);
		//    allBusRows.add(busArray);
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
//		for (int y_coord = Constants.START_WATER; y_coord >= Constants.END_WATER; y_coord -= Constants.TILE_SIZE) {
//			drawRow(waterTile, y_coord);
//		}

		// draw the grass
//		drawRow(grassTile, Constants.START_GRASS);
//		drawRow(grassTile, Constants.END_GRASS);

		readLevel(1);
		// render the buses
		for (EnemyArray busArray : allBusRows) {
			for (Enemy bus : busArray.getBusArray()) {
				bus.render();
			}
		}
		player.render();
	}

	public static void readLevel(int levelNum) {
		String lvlSrc = "assets/levels/" + levelNum + ".lvl";
		try (BufferedReader br =
				new BufferedReader(new FileReader(lvlSrc))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] columns = line.split(",");
				switch (columns[0]) {
				case "water":
					DeathTile waterTile = new DeathTile(waterImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
					interactables.add(waterTile);
					waterTile.render();
					break;
				case "grass":
					Tile grassTile = new Tile(grassImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
					grassTile.render();
					break;
				case "tree":
					SolidTile treeTile = new SolidTile(treeImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
					// TODO: May have to make a "solids" array that checks if solid
					// then can remove treeTile from interactables
					interactables.add(treeTile);
					treeTile.render();
					break;
					// may be same as racecar
				case "bus":
					// bus stuff
					break;
				case "racecar":
					break;
				case "bulldozer":
					break;
					// Below 2 may be the same
				case "log":
					break;
				case "longLog":
					break;
				case "turtle":
					break;
				default:
					System.out.println("Warning::: Not a valid tile");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
