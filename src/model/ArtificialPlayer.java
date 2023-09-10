package model;

/**
 * @author Luca Micarelli 
 * This class extends the class player,
 * and represents artificial players
 */
public class ArtificialPlayer extends Player
{
	/**
	 * @param username a string that represents the player name
	 * @param avatar a relative path of the avatar image
	 */
	public ArtificialPlayer(String username, String avatar)
	{super(username, avatar, false);}	
}
