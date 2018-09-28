import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import helper.Constants;

import java.io.FileReader;
import java.io.BufferedReader;

import java.util.ArrayList;


public class World {
	
	private static Image grassImage;
	private static Image waterImage;
	private static Image treeImage;
	
	private static Image busImage;
	private static Image bikeImage;
	private static Image racecarImage;
	private static Image bulldozerImage;
	private static Image logImage;
	private static Image longLogImage;
	private static Image turtleImage;
	

	private Player player;
	private ArrayList<CollideTile> interactableTiles;
	private ArrayList<SolidTile> solidTiles;
	private ArrayList<Tile> plainTiles;
	private ArrayList<Sprite> npcSprites;
	private ArrayList<SolidEnemy> solidEnemies;
	private ArrayList<CollideTile> winningTiles;

	public World() throws SlickException {
		
		interactableTiles = new ArrayList<>();
		solidTiles = new ArrayList<>();
		plainTiles = new ArrayList<>();
		
		npcSprites = new ArrayList<>();
		solidEnemies = new ArrayList<>();

		player = new Player(Constants.PLAYERSRC, Constants.START_PLAYER_X, Constants.START_PLAYER_Y);
		
		// Get images here, so not loading multiple times from source (too many loads causes an error)
		// tile Images
		grassImage = new Image(Constants.GRASSTILESRC);
		waterImage = new Image(Constants.WATERTILESRC);
		treeImage = new Image(Constants.TREETILESRC);
		
		// sprite Images
		busImage = new Image(Constants.BUSSRC);
		bulldozerImage = new Image(Constants.BULLDOZERSRC);
		racecarImage = new Image(Constants.RACECARSRC);
		bikeImage = new Image(Constants.BIKESRC);
		logImage = new Image(Constants.LOGSRC);
		longLogImage = new Image(Constants.LONGLOGSRC);
		turtleImage = new Image(Constants.TURTLESRC);

		readLevel(1);
	}

	public void update(Input input, int delta) {
		player.update(input, delta, solidTiles, solidEnemies);
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
		
		
		for (Sprite sprite : npcSprites) {
			if (player.isContacting(sprite)) {
				sprite.contactPlayer(player);
			}
			sprite.update(input, delta);
		}
	}

	public void render(Graphics g) {
		
		// Keep separate for modification later potentially
		for (Tile tile : plainTiles) {
			tile.render();
		}
		for (Tile tile : solidTiles) {
			tile.render();
		}
		for (Tile tile : interactableTiles) {
			tile.render();
		}
		
		player.render();
		for (Sprite sprite : npcSprites) {
			sprite.render();
		}
	}

	public void readLevel(int levelNum) {
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
					break;
				case "grass":
					Tile grassTile = new Tile(grassImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
					plainTiles.add(grassTile);
					break;
				case "tree":
					SolidTile treeTile = new SolidTile(treeImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
					solidTiles.add(treeTile);
					break;
				// may be able to use the same structure as racecar
				case "bus":
					Enemy bus = new Enemy(busImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.BUS_SPEED, Boolean.parseBoolean(columns[3]), false);
					npcSprites.add(bus);
					break;
				case "racecar":
					Enemy racecar = new Enemy(racecarImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.RACECAR_SPEED, Boolean.parseBoolean(columns[3]), false);
					npcSprites.add(racecar);
					break;
				case "bulldozer":
					SolidEnemy bulldozer = new SolidEnemy(bulldozerImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.BULLDOZER_SPEED, Boolean.parseBoolean(columns[3]), false);
					solidEnemies.add(bulldozer);
					npcSprites.add(bulldozer);
					break;
				case "bike":
					Enemy bike = new Enemy(bikeImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.BIKE_SPEED, Boolean.parseBoolean(columns[3]), true);
					npcSprites.add(bike);
					break;
				// Below 2 may be the same
				case "log":
					Platform log = new Platform(logImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.LOG_SPEED, Boolean.parseBoolean(columns[3]), false,  false);
					npcSprites.add(log);
					break;
				case "longLog":
					Platform longLog = new Platform(longLogImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.LONGLOG_SPEED, Boolean.parseBoolean(columns[3]), false,  false);
					npcSprites.add(longLog);
					break;
				case "turtle":
					Platform turtles = new Platform(turtleImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.LONGLOG_SPEED, Boolean.parseBoolean(columns[3]), false, true, Constants.TURTLE_SINK_DELAY);
					npcSprites.add(turtles);
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
	
	public ArrayList<SolidTile> getSolidTiles() {
		return solidTiles;
	}

}
