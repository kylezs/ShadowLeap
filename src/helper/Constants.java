package helper;

/**
 * Class to hold all used constants in the program
 * @author Kyle
 *
 */
public class Constants {
	
	// Screen (in pixels)
	public static final int SCREEN_WIDTH = 1024;
	public static final int SCREEN_HEIGHT = 768;
	
	// Tile (in pixels)
	public static final float TILE_SIZE = 48;
	public static final float TILE_OFFSET = 8;
	
	// Level
	public static final String LEVEL_SRC = "assets/levels/";
	public static final String LEVEL_EXT = ".lvl";
	
	// Tiles
	public static final String GRASSTILESRC = "assets/grass.png";
	public static final String WATERTILESRC = "assets/water.png";
	public static final String TREETILESRC = "assets/tree.png";
	
	// Sprites
	public static final String BUSSRC = "assets/bus.png";
	public static final String BULLDOZERSRC = "assets/bulldozer.png";
	public static final String RACECARSRC = "assets/racecar.png";
	public static final String BIKESRC = "assets/bike.png";
	public static final String LOGSRC = "assets/log.png";
	public static final String LONGLOGSRC = "assets/longlog.png";
	public static final String TURTLESRC = "assets/turtles.png";
	

	//Player
	public static final String PLAYERSRC = "assets/frog.png";
	public static final String LIFESRC = "assets/lives.png";
	public static final float START_PLAYER_X = SCREEN_WIDTH/2 - TILE_SIZE/2 - TILE_OFFSET;
	public static final float START_PLAYER_Y = 720;
	public static final int INIT_LIVES_X = 24;
	public static final int INIT_LIVES_Y = 744;
	public static final int LIVES_PADDING = 32;
	public static final int INITIAL_LIVES = 3;
	public static final int MAX_LIVES = 5;
	public static final int HOLES_TO_FILL = 5;
	
	// New life
	public static final String NEWLIFESRC = "assets/extralife.png";
	// in ms
	public static final int TIME_STEP = 2000;
	public static final int MIN_TIME_LIFE_SPAWN = 25000;
	public static final int RANGE_LIFE_SPAWN = 10000;
	public static final int DESPAWN_LIFE = 14000;
	
	// Level
	public static final int MAX_LEVEL = 2;

	// Enemy speeds
	public static final float BUS_SPEED = 0.15f;
	public static final float RACECAR_SPEED =  0.5f;
	public static final float BULLDOZER_SPEED = 0.05f;
	public static final float BIKE_SPEED = 0.2f;
	
	// Platform speeds
	public static final float LOG_SPEED = 0.1f;
	public static final float LONGLOG_SPEED = 0.07f;
	public static final float TURTLE_SPEED = 0.85f;
	
	// Turtle sinking times (in ms)
	public static final int TURTLE_SINK_DELAY = 7000;
	public static final int TURTLE_RESURFACE_DELAY = 2000;
	
}
