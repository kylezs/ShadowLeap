package helper;

public class Constants {
	
	public static final String GRASSTILESRC = "assets/grass.png";
	public static final String WATERTILESRC = "assets/water.png";
	public static final String TREETILESRC = "assets/tree.png";
	
	public static final String BUSSRC = "assets/bus.png";
	public static final String BULLDOZERSRC = "assets/bulldozer.png";
	public static final String RACECARSRC = "assets/racecar.png";
	public static final String BIKESRC = "assets/bike.png";
	public static final String LOGSRC = "assets/log.png";
	public static final String LONGLOGSRC = "assets/longlog.png";
	public static final String TURTLESRC = "assets/turtles.png";
	

	
	public static final String PLAYERSRC = "assets/frog.png";
	public static final String LIFESRC = "assets/lives.png";
	public static final int INIT_LIVES_X = 24;
	public static final int INIT_LIVES_Y = 744;
	public static final int LIVES_PADDING = 32;
	public static final int INITIAL_LIVES = 3;
	public static final int MAX_LIVES = 5;
	public static final int HOLES_TO_FILL = 5;
	
	public static final String NEWLIFESRC = "assets/extralife.png";
	public static final int TIME_STEP = 2000;
	
	// in ms
	public static final int MIN_TIME_LIFE_SPAWN = 25000;
	public static final int RANGE_LIFE_SPAWN = 10000;
	public static final int DESPAWN_LIFE = 14000;
	
	public static final int MAX_LEVEL = 2;
	

	/** directions */
	public static final String RIGHT = "right";
	public static final String LEFT = "left";

	/** screen width, in pixels */
	public static final int SCREEN_WIDTH = 1024;
	/** screen height, in pixels */
	public static final int SCREEN_HEIGHT = 768;
	/** tile size + length/height of each jump */
	public static final float TILE_SIZE = 48;
	public static final float TILE_OFFSET = 8;

	public static final float START_PLAYER_X = SCREEN_WIDTH/2 - TILE_SIZE/2 - TILE_OFFSET;
	public static final float START_PLAYER_Y = 720;

	public static final int START_WATER = 336;
	public static final int END_WATER = 48;

	public static final float START_GRASS = 672;
	public static final float END_GRASS = 384;

	public static final float BUS_SPEED = 0.15f;
	public static final float RACECAR_SPEED =  0.5f;
	public static final float BULLDOZER_SPEED = 0.05f;
	public static final float BIKE_SPEED = 0.2f;
	
	public static final float LOG_SPEED = 0.1f;
	public static final float LONGLOG_SPEED = 0.07f;
	public static final float TURTLE_SPEED = 0.85f;
	
	// in ms
	public static final int TURTLE_SINK_DELAY = 7000;
	public static final int TURTLE_RESURFACE_DELAY = 2000;
	
}
