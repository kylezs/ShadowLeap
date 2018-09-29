import org.newdawn.slick.Graphics;
import java.util.Random;
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
	
	private static Image lifeImage;
	
	private static Image newLifeImage;
	private static AttachedItem newLife;
	

	private Player player;
	private ArrayList<CollideTile> interactableTiles;
	private ArrayList<SolidTile> solidTiles;
	private ArrayList<Tile> plainTiles;
	private ArrayList<Sprite> npcSprites;
	private ArrayList<SolidEnemy> solidEnemies;
	private ArrayList<Platform> platforms;
	private ArrayList<WinningTile> winningTiles;
	
	private static int floatTimeElapsed = 0;

	public World(int currentLevel) throws SlickException {
		
		interactableTiles = new ArrayList<>();
		solidTiles = new ArrayList<>();
		plainTiles = new ArrayList<>();
		winningTiles = new ArrayList<>();
		
		npcSprites = new ArrayList<>();
		solidEnemies = new ArrayList<>();
		platforms = new ArrayList<>();

		player = new Player(Constants.PLAYERSRC, Constants.START_PLAYER_X, Constants.START_PLAYER_Y);
		player.backToStart();
		
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
		
		// little frog image
		lifeImage = new Image(Constants.LIFESRC);
		
		// Don't render until time comes
		newLifeImage = new Image(Constants.NEWLIFESRC);
		newLife = new AttachedItem(newLifeImage, 0, 0);
		

		readLevel(currentLevel);
	}

	public void update(Input input, int delta) {
		floatTimeElapsed += delta;
		
		player.update(input, delta, solidTiles, solidEnemies);
		
		for (Sprite sprite : npcSprites) {
			if (player.isContacting(sprite)) {
				sprite.contactPlayer(player);
			}
			sprite.update(input, delta);
		}
		
		boolean localFloating = true;
		if (floatTimeElapsed > Constants.TURTLE_RESURFACE_DELAY + Constants.TURTLE_SINK_DELAY) {
			localFloating = true;
			floatTimeElapsed = 0;
		} else if (floatTimeElapsed > Constants.TURTLE_SINK_DELAY) {
			localFloating = false;
		}
		boolean onPlatform = false;
		for (Platform platform : platforms) {
			if (player.isContacting(platform)) {
				onPlatform = true;
				platform.contactPlayer(player, delta);
			}
			if (platform.getDoesSink()) {
				if (!localFloating && player.isContacting(platform)) {
					onPlatform = false;
				}
				platform.setFloating(localFloating);
				platform.setFloatingAttachedItems();
			}
			platform.update(input, delta);
		}
		if (!onPlatform) {
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
		}
		
		for (WinningTile winningTile : winningTiles) {
			if (player.getBoundingBox().intersects(winningTile)) {
				winningTile.contactPlayer(player);
				Image playerImage = null;
				try {
					playerImage = new Image(Constants.PLAYERSRC);
				} catch (SlickException e) {
					e.printStackTrace();
				}
				DeathTile playerTile = new DeathTile(playerImage, winningTile.getCentreX() + Constants.TILE_SIZE / 2, winningTile.getCentreY());
				interactableTiles.add(playerTile);
			}
		}
		
		
		if (player.isContacting(newLife) && !newLife.isCollected()) {
			newLife.contactPlayer(player);
		}
		
		newLife.update(input, delta, platforms);
		
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
		
		for (Sprite sprite : npcSprites) {
			sprite.render();
		}
		
		// platforms are separate because need timing in update
		for (Platform platform : platforms) {
			platform.render();
		}
		
		// rendered at end so it appears on top of other sprites/tiles
		player.render();
		
		for (int i = 0; i < player.getLives(); i++) {
			lifeImage.draw(Constants.INIT_LIVES_X + i * Constants.LIVES_PADDING, Constants.INIT_LIVES_Y);
		}
		
		// render the newlife after the player
		newLife.render();
		
	}

	public void readLevel(int levelNum) {
		String lvlSrc = "assets/levels/" + levelNum + ".lvl";
		int lastTreeTileX = 0;
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
					// if there's a gap betweeen trees, fill it with WinningTiles
					if (Integer.parseInt(columns[1]) - lastTreeTileX > Constants.TILE_SIZE) {
						WinningTile winningTile = new WinningTile(lastTreeTileX + Constants.TILE_SIZE, Integer.parseInt(columns[2]), 2 * Constants.TILE_SIZE, Constants.TILE_SIZE);
						winningTiles.add(winningTile);
					}
					lastTreeTileX = Integer.parseInt(columns[1]);
					solidTiles.add(treeTile);
					break;
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
				case "log":
					Platform log = new Platform(logImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.LOG_SPEED, Boolean.parseBoolean(columns[3]), false,  false);
					platforms.add(log);
					break;
				case "longLog":
					Platform longLog = new Platform(longLogImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.LONGLOG_SPEED, Boolean.parseBoolean(columns[3]), false,  false);
					platforms.add(longLog);
					break;
				case "turtle":
					Platform turtles = new Platform(turtleImage, Integer.parseInt(columns[1]), Integer.parseInt(columns[2]), Constants.LONGLOG_SPEED, Boolean.parseBoolean(columns[3]), false, true);
					platforms.add(turtles);
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
