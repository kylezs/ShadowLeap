/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 *
 * Extended by Kyle Zsembery, University of Melbourne
 */

import org.newdawn.slick.*;
import helper.Constants;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {

    private static AppGameContainer app;
    private World world;
    private static int currentLevel;

    public App() {
        super("Shadow Leap");
    }
    
    /**
     * Called by player when the player has filled all the necessary holes
     * Increases the level of the game by 1
     */
    public static void nextLevel() {
    	if (currentLevel < Constants.MAX_LEVEL) {
    		currentLevel++;
    	}
    	
    }
    
    /**
     * 
     * @return currentLevel the currentLevel of the game
     */
    public static int getCurrentLevel() {
    	return currentLevel;
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
    	// Always start on level 0;
        world = new World(currentLevel);
    }
    
    /**
     * @return world
     */
    public World getWorld() {
    	return world;
    }
    
    /**
     * Sets the currentWorld to newWorld
     * @param newWorld
     */
    public void setWorld(World newWorld) {
    	this.world = newWorld;
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        world.update(input, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
        throws SlickException {
      app = new AppGameContainer(new App());
      app.setShowFPS(false);
      app.setDisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
      app.start();
    }

    public static AppGameContainer getApp() {
    	return app;
    }

}