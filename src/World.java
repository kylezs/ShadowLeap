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
	private static ArrayList<CollideTile> interactableTiles;
	private static ArrayList<SolidTile> solidTiles;
	private static ArrayList<Sprite> npcSprites;

	public World() throws SlickException {
		
		interactableTiles = new ArrayList<>();
		solidTiles = new ArrayList<>();

		player = new Player(Constants.PLAYERSRC, Constants.START_PLAYER_X, Constants.START_PLAYER_Y);
		
		grassImage = new Image(Constants.GRASSTILESRC);
		waterImage = new Image(Constants.WATERTILESRC);
		treeImage = new Image(Constants.TREETILESRC);
	}

	public void update(Input input, int delta) {
		player.update(input, delta);
		boolean hasContacted = false;
		CollideTile collisionTile = null;
		// loop through Tiles
		for (CollideTile tile : interactableTiles) {
			if (player.isContacting(tile)) {
				hasContacted = true;
				collisionTile = tile;
			}
		}
		if (hasContacted) {
			collisionTile.contactPlayer(player);
		}
		
		// same for sprites
	}

	public void render(Graphics g) {
		readLevel(2);
		
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
					interactableTiles.add(waterTile);
					waterTile.render();
					break;
				case "grass":
					Tile grassTile = new Tile(grassImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
					grassTile.render();
					break;
				case "tree":
					SolidTile treeTile = new SolidTile(treeImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
					solidTiles.add(treeTile);
					treeTile.render();
					break;
				// may be able to use the same structure as racecar
				case "bus":
					break;
				case "racecar":
					break;
				case "bulldozer":
					break;
				case "bike":
					break;
					
				// Below 2 may be the same
				case "log":
					break;
				case "longLog":
					break;
				case "turtle":
					break;
				default:
					System.out.println("Warning::: Not a valid tile: " + columns[0]);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<SolidTile> getSolidTiles() {
		return solidTiles;
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
