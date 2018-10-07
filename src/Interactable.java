/**
 * Interface for objects that have actions if the player's bounding box intersects the implementing object's
 * bounding box
 */
public interface Interactable {
	
	/**
	 * Action taken if the implementing object intersects with the player
	 * @param player
	 */
	public void contactPlayer(Player player);
}
